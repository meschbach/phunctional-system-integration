/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/event/WorkListenerAdapter.java $
 * $Id: WorkListenerAdapter.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.event;

import com.meschbach.psi.example.dprime.work.Work;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class WorkListenerAdapter<T extends Work<?>> implements WorkListener<T> {

    public void workAvailable(T work) {
    }

    public void workCalculating(T work) {
    }

    public void workCompleted(T work) {
    }

    public void workSetup(T work) {
    }
}
