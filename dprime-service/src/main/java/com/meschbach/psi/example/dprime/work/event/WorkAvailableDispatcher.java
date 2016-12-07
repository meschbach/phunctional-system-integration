/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/event/WorkAvailableDispatcher.java $
 * $Id: WorkAvailableDispatcher.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.event;

import com.meschbach.cise.event.Dispatcher;
import com.meschbach.psi.example.dprime.work.Work;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class WorkAvailableDispatcher implements Dispatcher<WorkListener> {

    Work work;

    public WorkAvailableDispatcher(Work work) {
        this.work = work;
    }

    public boolean dispatch(WorkListener listener) {
        listener.workAvailable(work);
        return true;
    }
}
