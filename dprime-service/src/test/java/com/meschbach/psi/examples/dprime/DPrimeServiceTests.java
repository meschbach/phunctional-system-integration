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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/test/java/com/meschbach/psi/examples/dprime/DPrimeServiceTests.java $
 * $Id: DPrimeServiceTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime;

import com.meschbach.psi.example.dprime.DPrimeService;
import com.meschbach.psi.example.dprime.pkb.MemoryPrimeKB;
import com.meschbach.psi.example.dprime.work.InternalWork;
import com.meschbach.psi.example.dprime.work.dqueue.SingleThreadedWorkQueue;
import com.meschbach.psi.example.dprime.work.event.WorkListenerAdapter;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @author 1.0.0
 */
public class DPrimeServiceTests {

    @Test(timeOut = 10 * 1000)
    public void testSingleThreadedTwo() {
        DPrimeService dps = new DPrimeService(new MemoryPrimeKB(), new SingleThreadedWorkQueue());
        testPrimeIfPrime(dps, BigDecimal.valueOf(2), true);
    }

    @Test(timeOut = 10 * 1000)
    public void testSingleThreadedSeven() {
        DPrimeService dps = new DPrimeService(new MemoryPrimeKB(), new SingleThreadedWorkQueue());
        testPrimeIfPrime(dps, BigDecimal.valueOf(7), true);
    }

    protected void testPrimeIfPrime(DPrimeService dps, BigDecimal maybePrime, boolean isPrime) {
        InternalWork<Boolean> result = dps.findIfPrime(maybePrime);
        FindPrimeResult fpr = new FindPrimeResult(isPrime);
        result.addStateListener(fpr);
        assertTrue(fpr.waitOnResult());
    }

    class FindPrimeResult extends WorkListenerAdapter<InternalWork<Boolean>> {

        public boolean invoked = false;
        boolean expected;

        public FindPrimeResult(boolean expected) {
            this.expected = expected;
        }

        @Override
        public synchronized void workCompleted(InternalWork<Boolean> work) {
            invoked = true;
            boolean wasPrime = work.getResult();
            assertTrue(wasPrime);
            notifyAll();
        }

        public synchronized boolean waitOnResult() {
            try {
                while (invoked == false) {
                    wait();
                }
                return invoked;
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }
}
