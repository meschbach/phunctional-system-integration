/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/pkb/CompletedChecksDispatcher.java $
 * $Id: CompletedChecksDispatcher.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.pkb;

import com.meschbach.cise.event.Dispatcher;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class CompletedChecksDispatcher implements Dispatcher<PrimeReactorListener> {

    PrimerReactor reactor;

    public CompletedChecksDispatcher(PrimerReactor reactor) {
        this.reactor = reactor;
    }

    public boolean dispatch(PrimeReactorListener listener) {
        listener.completedPrimeChecks(reactor);
        return true;
    }
}
