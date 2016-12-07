/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/test/java/com/meschbach/psi/examples/dprime/pkb/PrimerReactorTests.java $
 * $Id: PrimerReactorTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.pkb;

import com.meschbach.psi.example.dprime.pkb.PrimeReactorListener;
import com.meschbach.psi.example.dprime.MasterControlProgram;
import com.meschbach.psi.example.dprime.pkb.MemoryPrimeKB;
import com.meschbach.psi.example.dprime.pkb.PrimerReactor;
import com.meschbach.psi.example.dprime.work.dqueue.SingleThreadedWorkQueue;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimerReactorTests {

    @Test
    public void testUtilizesHighestKnownPrimeOnContruction() {
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        PrimerReactor pr = new PrimerReactor(mpkb, new SingleThreadedWorkQueue(), new MasterControlProgram());
        assertEquals(pr.getTarget(), mpkb.getLargestPrime());
    }

    @Test
    public void primerAquisitionUnerLargestSendsEvent() {
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        PrimerReactor pr = new PrimerReactor(mpkb, new SingleThreadedWorkQueue(), new MasterControlProgram());
        AquireCallbackAssertions aca = new AquireCallbackAssertions();
        pr.aquireLargestTarget(mpkb.getLargestPrime().subtract(BigDecimal.ONE), aca);
        assertTrue(aca.wasInvoked);
    }

    @Test
    public void primerAquistationAtLargestSendsEvent() {
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        PrimerReactor pr = new PrimerReactor(mpkb, new SingleThreadedWorkQueue(), new MasterControlProgram());
        AquireCallbackAssertions aca = new AquireCallbackAssertions();
        pr.aquireLargestTarget(mpkb.getLargestPrime(), aca);
        assertTrue(aca.wasInvoked);
    }

    class AquireCallbackAssertions implements PrimeReactorListener {

        public boolean wasInvoked = false;

        public synchronized void completedPrimeChecks(PrimerReactor r) {
            assertTrue(wasInvoked);
        }

        public synchronized void numberCheckCompleted(PrimerReactor r, BigDecimal number, boolean isPrime) {
            wasInvoked = true;
        }
    }

//    @Test(timeOut=10*1000)
    @Test
    public void primerAquistionWorksAsExpected() {
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        PrimerReactor pr = new PrimerReactor(mpkb, new SingleThreadedWorkQueue(), new MasterControlProgram());
        PrimerCrawSync pcs = new PrimerCrawSync();
        pr.addListener(pcs);
        pr.aquireLargestTarget(BigDecimal.valueOf(5), pcs);
        pcs.waitForCompletion();
        assertTrue(pcs.checkCompleted);
        assertTrue(pcs.completed);
        assertTrue(pcs.wasPrime);
    }

    class PrimerCrawSync implements PrimeReactorListener {

        public boolean completed = false;
        public boolean checkCompleted = false;
        public boolean wasPrime = false;

        public synchronized void waitForCompletion() {
            try {
                while (!completed) {
                    wait();
                }
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        }

        public synchronized void completedPrimeChecks(PrimerReactor r) {
            completed = true;
            notifyAll();
        }

        public synchronized void numberCheckCompleted(PrimerReactor r, BigDecimal number, boolean isPrime) {
            checkCompleted = true;
            wasPrime = isPrime;
        }
    }
}
