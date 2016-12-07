/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/test/java/com/meschbach/psi/examples/dprime/work/distributed/DivisionWorkTests.java $
 * $Id: DivisionWorkTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.work.distributed;

import com.meschbach.psi.example.dprime.work.distributed.DivisionWork;
import com.meschbach.psi.example.dprime.work.event.WorkListenerAdapter;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DivisionWorkTests {

    @Test
    public void testSimpleRequestResponseCycle() {
        final DivisionWorkCompletedAssertion dwca = new DivisionWorkCompletedAssertion(BigDecimal.valueOf(5), BigDecimal.ZERO);
        final DivisionWork dw = new DivisionWork(BigDecimal.TEN, BigDecimal.valueOf(2));
        dw.addStateListener(dwca);
        assertFalse(dwca.invoked);
        dw.responseRecieved(dw.createRequest().buildResponse());
        assertTrue(dwca.invoked);
    }

    class DivisionWorkCompletedAssertion extends WorkListenerAdapter<DivisionWork> {

        public boolean invoked = false;
        BigDecimal q, r;

        public DivisionWorkCompletedAssertion(BigDecimal q, BigDecimal r) {
            this.q = q;
            this.r = r;
        }

        @Override
        public void workCompleted(DivisionWork work) {
            assertEquals(work.getResult().getQuotient(), q);
            assertEquals(work.getResult().getRemainder(), r);
            invoked = true;
        }
    }
}
