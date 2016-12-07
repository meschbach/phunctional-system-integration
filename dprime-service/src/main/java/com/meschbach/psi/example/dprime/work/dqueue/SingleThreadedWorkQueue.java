/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/dqueue/SingleThreadedWorkQueue.java $
 * $Id: SingleThreadedWorkQueue.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.dqueue;

import com.meschbach.psi.example.dprime.work.dqueue.Stopable;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprime.work.dqueue.UnitOfWork;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class SingleThreadedWorkQueue implements WorkQueue<DistributedWork>, Stopable {

    public void submit(DistributedWork work) {
        UnitOfWork uow = new UnitOfWork(work);
        uow.run();
    }

    public void stop() {
    }
}
