/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/test/java/com/meschbach/psi/examples/dprime/mcp/PrimalityWorkerTests.java $
 * $Id: PrimalityWorkerTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.mcp;

import com.meschbach.psi.example.dprime.mcp.PrimalityWork;
import com.meschbach.psi.example.dprime.pkb.MemoryPrimeKB;
import com.meschbach.psi.example.dprime.work.dqueue.SingleThreadedWorkQueue;
import com.meschbach.psi.example.dprime.work.event.WorkListenerAdapter;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimalityWorkerTests {

    @Test(timeOut = 10 * 1000)
    public void integrationTestForPrimalityForKnownPrimes() {
        /*
         *
         */
        PrimalityWorkerAssertionListener pwal = new PrimalityWorkerAssertionListener();
        /*
         * 
         */
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        SingleThreadedWorkQueue stwq = new SingleThreadedWorkQueue();
        PrimalityWork pw = new PrimalityWork(mpkb, null, stwq, BigDecimal.valueOf(2));
        pw.addStateListener(pwal);
        pw.start();
        /*
         * 
         */
        pwal.waitForCompletion();
        assertTrue(pwal.didComplete);
    }

    class PrimalityWorkerAssertionListener extends WorkListenerAdapter<PrimalityWork> {

        boolean didComplete = false;

        public synchronized void waitForCompletion() {
            while (!didComplete) {
                try {
                    wait();
                } catch (Throwable t) {
                    throw new IllegalStateException(t);
                }
            }
        }

        @Override
        public synchronized void workCompleted(PrimalityWork work) {
            didComplete = true;
            notifyAll();
        }
    }
}
