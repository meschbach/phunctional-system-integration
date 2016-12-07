/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/test/java/com/meschbach/psi/examples/dprime/mcp/PrimeCrawlerTests.java $
 * $Id: PrimeCrawlerTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.mcp;

import com.meschbach.psi.example.dprime.MasterControlProgram;
import com.meschbach.psi.example.dprime.mcp.PrimeCrawler;
import com.meschbach.psi.example.dprime.pkb.MemoryPrimeKB;
import com.meschbach.psi.example.dprime.pkb.PrimerReactor;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprime.work.dqueue.SingleThreadedWorkQueue;
import com.meschbach.psi.example.dprimecore.prime.CompletionClosure;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimeCrawlerTests {

    @Test
    public void testKnownCrawlerPrimws() {
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        WorkQueue<DistributedWork> dwq = new SingleThreadedWorkQueue();
        PrimerReactor reactor = new PrimerReactor(mpkb, dwq, new MasterControlProgram());
        PrimeCrawler pc = new PrimeCrawler(BigDecimal.valueOf(3), mpkb, reactor);
        pc.setResult(new CompletionClosure() {

            public void completedResult(BigDecimal result) {
                assertEquals(result, BigDecimal.valueOf(2));
            }
        });
        pc.doWork(dwq);
        pc.setResult(new CompletionClosure() {

            public void completedResult(BigDecimal result) {
                assertEquals(result, BigDecimal.valueOf(3));
            }
        });
        pc.doWork(dwq);
    }

    @Test
    public void testCanCrawlUnknowns() throws InterruptedException {
        MemoryPrimeKB mpkb = new MemoryPrimeKB();
        WorkQueue<DistributedWork> dwq = new SingleThreadedWorkQueue();
        PrimerReactor reactor = new PrimerReactor(mpkb, dwq, new MasterControlProgram());
        PrimeCrawler pc = new PrimeCrawler(BigDecimal.valueOf(5), mpkb, reactor);
        pc.setResult(new CompletionClosure() {

            public void completedResult(BigDecimal result) {
            }
        });
        pc.doWork(dwq);
        pc.doWork(dwq);
        SynchronizingCompletionHandler sch = new SynchronizingCompletionHandler();
        pc.setResult(sch);
        pc.doWork(dwq);
        assertEquals(sch.result(), BigDecimal.valueOf(5));
    }

    class SynchronizingCompletionHandler implements CompletionClosure {

        BigDecimal result;

        public synchronized BigDecimal result() throws InterruptedException {
            while (result == null) {
                wait();
            }
            return result;
        }

        public synchronized void completedResult(BigDecimal result) {
            this.result = result;
            notifyAll();
        }
    }
}
