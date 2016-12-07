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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/DPrimeService.java $
 * $Id: DPrimeService.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime;

import com.meschbach.psi.example.dprime.pkb.PrimeKB;
import com.meschbach.psi.example.dprime.pkb.PrimerReactor;
import com.meschbach.psi.example.dprime.mcp.PrimalityWork;
import com.meschbach.psi.example.dprime.work.InternalWork;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprime.work.WorkQueueMonitor;
import java.math.BigDecimal;

/**
 * The <code>DPrimeService</code> is a service to provide coordination for the
 * distributed calculation of prime numbers against a number of numbers.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DPrimeService {

    PrimeKB knownPrimes;
    WorkQueue<DistributedWork> distributedQueue;
    WorkQueue<InternalWork> internalQueue;
    WorkQueueMonitor<InternalWork> internalMonitor;
    PrimerReactor reactor;

    public DPrimeService(PrimeKB knownPrimes, WorkQueue<DistributedWork> queue) {
        this.knownPrimes = knownPrimes;
        this.distributedQueue = queue;

        MasterControlProgram mcp = new MasterControlProgram();
        internalQueue = mcp;
        internalMonitor = mcp;

        reactor = new PrimerReactor(knownPrimes, distributedQueue, internalQueue);
    }

    public InternalWork<Boolean> findIfPrime(BigDecimal target) {
        /*
         * Construct our prime calculator
         */
        PrimalityWork pw = new PrimalityWork(knownPrimes, reactor, distributedQueue, target);
        internalQueue.submit(pw);
        return pw;
    }

    public PrimeKB getPrimeKB() {
        return knownPrimes;
    }

    public WorkQueueMonitor<InternalWork> getInternalMonitor() {
        return internalMonitor;
    }
}
