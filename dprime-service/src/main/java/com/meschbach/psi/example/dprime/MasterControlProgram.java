/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/MasterControlProgram.java $
 * $Id: MasterControlProgram.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime;

import com.meschbach.psi.example.dprime.work.IsCompletedPredicate;
import com.meschbach.psi.example.dprime.work.InternalWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprime.work.WorkQueueMonitor;
import com.meschbach.cise.iterator.MIterator;
import static com.meschbach.cise.iterator.Iterators.*;
import static com.meschbach.cise.predicates.Predicates.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The <code>MasterControlProgram</code> (MCP) is charged with monitoring and
 * managing work to be executed.  Poor definition; but deal.  Otherwise MCP
 * will send you into to play pong....and we all know what happens after that.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @version 1.0.0
 */
public class MasterControlProgram implements WorkQueue<InternalWork>, WorkQueueMonitor<InternalWork> {

    List<InternalWork> knownWork;

    public MasterControlProgram() {
        knownWork = new CopyOnWriteArrayList<InternalWork>();
    }

    public void submit(InternalWork work) {
        knownWork.add(work);
        work.start();
    }

    public MIterator<InternalWork, Exception> getCompletedWork() {
        return select(new IsCompletedPredicate(), getWork());
    }

    public WorkQueue<InternalWork> getMonitoredQueue() {
        return this;
    }

    public MIterator<InternalWork, Exception> getOutstandingWork() {
        return select(not(new IsCompletedPredicate()), getWork());
    }

    public int getTaskCount() {
        return knownWork.size();
    }

    public MIterator<InternalWork, Exception> getWork() {
        return fromCollection(knownWork);
    }

    public MIterator<InternalWork, Exception> getWork(int base, int length) {
        return limit(offset(getWork(), base), length);
    }
}
