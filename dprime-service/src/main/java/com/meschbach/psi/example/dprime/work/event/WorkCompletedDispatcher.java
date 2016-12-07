/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/event/WorkCompletedDispatcher.java $
 * $Id: WorkCompletedDispatcher.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.event;

import com.meschbach.cise.event.Dispatcher;
import com.meschbach.psi.example.dprime.work.Work;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class WorkCompletedDispatcher<T extends WorkListener> implements Dispatcher<T> {

    Work work;

    public WorkCompletedDispatcher(Work work) {
        this.work = work;
    }

    public boolean dispatch(T listener) {
        listener.workCompleted(work);
        return true;
    }
}
