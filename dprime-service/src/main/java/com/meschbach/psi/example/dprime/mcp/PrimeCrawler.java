/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/mcp/PrimeCrawler.java $
 * $Id: PrimeCrawler.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.mcp;

import com.meschbach.psi.example.dprime.pkb.PrimeKB;
import com.meschbach.psi.example.dprime.pkb.PrimeReactorListener;
import com.meschbach.psi.example.dprime.pkb.PrimerReactor;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprimecore.prime.CompletionClosure;
import java.math.BigDecimal;

/**
 * A <code>PrimeCrawler</code> is charged with the acquisition and disbursement
 * of known prime numbers.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimeCrawler extends WorkAction {

    BigDecimal target;
    CompletionClosure result;
    BigDecimal lastPrime;
    PrimeKB pkb;
    PrimerReactor reactor;

    public PrimeCrawler(BigDecimal target, PrimeKB pkb, PrimerReactor reactor) {
        this.target = target;
        this.pkb = pkb;
        this.reactor = reactor;
    }

    public CompletionClosure getResult() {
        return result;
    }

    public synchronized void setResult(CompletionClosure result) {
        this.result = result;
    }

    @Override
    public synchronized void doWork(WorkQueue<DistributedWork> queue) {
        /*
         * Locate the next prime number to use
         */
        BigDecimal nextPrime;
        if (lastPrime == null) {
            /*
             * We haven't tried any primes yet, start with the smallest
             */
            nextPrime = pkb.getSmallestPrime();
        } else {
            /*
             * Retrieve the next prime
             */
            nextPrime = pkb.getNextPrime(lastPrime);
        }
        /*
         * Do we know the next prime?
         */
        if (nextPrime != null) {
            /*
             * Yes, we are done
             */
            lastPrime = nextPrime;
            result.completedResult(nextPrime);
        } else {
            /*
             * No, ask the prime reactor
             */
            reactor.aquireLargestTarget(target, new PrimeReactorListener() {

                public void numberCheckCompleted(PrimerReactor r, BigDecimal number, boolean isPrime) {
                    r.removeListener(this);
                    result.completedResult(number);
                }

                public void completedPrimeChecks(PrimerReactor r) {
                    throw new IllegalStateException("This should never happen");
                }
            });
        }
    }
}
