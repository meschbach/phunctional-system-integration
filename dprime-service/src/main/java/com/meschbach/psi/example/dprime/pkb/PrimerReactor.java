/*
 *  Copyright 2011 Mark Eschbach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/pkb/PrimerReactor.java $
 * $Id: PrimerReactor.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.pkb;

import com.meschbach.cise.event.EventPump;
import com.meschbach.psi.example.dprime.mcp.PrimalityWork;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprime.work.AbstractWork;
import com.meschbach.psi.example.dprime.work.InternalWork;
import com.meschbach.psi.example.dprime.work.event.WorkListenerAdapter;
import java.math.BigDecimal;

/**
 * The <code>PrimerReactor</code> encapsulates an algorithm to crawl all real
 * odd prime numbers, expanding the knowledge base as we do so.  This allows
 * for the demultiplexed expansion of the knowledge of prime numbers.
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrimerReactor extends AbstractWork<BigDecimal> {

    private PrimeKB pkb;
    private WorkQueue<DistributedWork> distributedWork;
    private WorkQueue<InternalWork> internalWork;
    private BigDecimal current;
    private BigDecimal target;
    private EventPump<PrimeReactorListener> listeners;
    boolean isRunning;

    /**
     * Constructs a new PrimerReactory with the given set of known prime
     * numbers, a distributed work queue, and a local work queue.
     *
     * @param pkb is the set of known primes to utilize
     * @param dqueue is a queue to distribute work too
     * @param iqueue is the queue to record internal subtasks
     */
    public PrimerReactor(PrimeKB pkb, WorkQueue<DistributedWork> dqueue, WorkQueue<InternalWork> iqueue) {
        this.pkb = pkb;
        this.distributedWork = dqueue;
        this.internalWork = iqueue;

        target = pkb.getLargestPrime();
        current = target;
        isRunning = false;
        listeners = new EventPump<PrimeReactorListener>();
    }

    /**
     * @return the current target we are attempting to discover if prime
     */
    public synchronized BigDecimal getTarget() {
        return target;
    }

    public synchronized void start() {
        /*
         * Ensure we are destroying our current state
         */
        if (isRunning()) {
            throw new IllegalStateException("Already started");
        }
        /*
         * Set our state
         */
        dispatchCalculating();
        /*
         * Do our next number if relevent
         */
        performNextNumber();
    }

    protected synchronized void performNextNumber() {
        /*
         * Ensure we have not yet passwed our target number
         */
        if (target.compareTo(current) <= 0) {
            /*
             * We have reached our end, stop
             */
            done();
        } else {
            /*
             * No, continue processing
             */
            PrimalityWork pw = new PrimalityWork(pkb, this, distributedWork, target);
            pw.addStateListener(new WorkListenerAdapter<PrimalityWork>() {

                @Override
                public void workCompleted(PrimalityWork work) {
                    foundPrimeResult(work);
                }
            });
            internalWork.submit(pw);
        }
    }

    protected synchronized void foundPrimeResult(PrimalityWork work) {
        /*
         * Dipstach our result
         */
        fireCheckCompleted(work.getMaybePrime(), work.getResult());
        current = work.getMaybePrime();
        /*
         * Perform next operation
         */
        performNextNumber();
    }

    protected synchronized void done() {
        listeners.dispatch(new CompletedChecksDispatcher(this));
        dispatchAvailable();
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized void startIfNotRunning() {
        if (!isRunning()) {
            start();
        }
    }

    /**
     * Notifies the reactor we need to know if up to <code>aTarget</code> is
     * prime, atomically registering <code>listener</code> for event
     * notifications.  If <code>aTarget</code> has already been passed then
     * the <code>listener</code> will be registered as a listener and sent an
     * additional <code>numberCheckCompleted</code> with the results of
     * the given target.
     * 
     * @param aTarget is the target number we are searching for
     * @param listener is the listener for the results
     */
    public synchronized void aquireLargestTarget(BigDecimal aTarget, PrimeReactorListener listener) {
        /*
         * Attach the listener
         */
        addListener(listener);
        /*
         * Determine if we have a new target
         */
        if (target.compareTo(aTarget) < 0) {
            /*
             * Yes
             * Setup our new target
             */
            target = aTarget;
            /*
             * Are we currently processing?
             */
            startIfNotRunning();
        } else {
            /*
             * No, ensure we have not yet surpassed the requested target
             */
            if (current.compareTo(aTarget) >= 0) {
                /*
                 * We have, then notify the listener
                 */
                listener.numberCheckCompleted(this, aTarget, pkb.isPrime(aTarget));
            }
        }
    }

    protected synchronized void submit(DistributedWork w) {
        distributedWork.submit(w);
    }

    public synchronized void addListener(PrimeReactorListener listener) {
        listeners.addListener(listener);
    }

    public synchronized void removeListener(PrimeReactorListener listener) {
        listeners.removeListener(listener);
    }

    protected synchronized void fireCheckCompleted(BigDecimal number, boolean isPrime) {
        listeners.dispatch(new NumberCompletedDispatcher(this, number, isPrime));
    }

    protected synchronized void fireCompleted() {
        listeners.dispatch(new CompletedChecksDispatcher(this));
    }

    public BigDecimal getResult() {
        throw new UnsupportedOperationException("By design this is sisyphean; oh poor Sisyphus!  Instances of this class feel your pain");
    }

    public String getDescription() {
        return "Reactor to coordinate the calculation of the next prime number";
    }
}
