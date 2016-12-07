/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/InternalWork.java $
 * $Id: InternalWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work;

import com.meschbach.psi.example.dprime.work.Work;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public interface InternalWork<T> extends Work<T> {

    public void start();
}
