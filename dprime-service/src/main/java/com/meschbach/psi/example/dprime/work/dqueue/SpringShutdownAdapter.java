/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/dqueue/SpringShutdownAdapter.java $
 * $Id: SpringShutdownAdapter.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.dqueue;

import org.springframework.beans.factory.DisposableBean;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class SpringShutdownAdapter implements DisposableBean {

    Stopable queue;

    public Stopable getQueue() {
        return queue;
    }

    public void setQueue(Stopable queue) {
        this.queue = queue;
    }

    public void destroy() throws Exception {
        queue.stop();
    }
}
