/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/pkb/NumberCompletedDispatcher.java $
 * $Id: NumberCompletedDispatcher.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.pkb;

import com.meschbach.cise.event.Dispatcher;
import java.math.BigDecimal;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class NumberCompletedDispatcher implements Dispatcher<PrimeReactorListener> {

    PrimerReactor reactor;
    BigDecimal number;
    boolean wasPrime;

    public NumberCompletedDispatcher(PrimerReactor reactor, BigDecimal number, boolean wasPrime) {
        this.reactor = reactor;
        this.number = number;
        this.wasPrime = wasPrime;
    }

    public boolean dispatch(PrimeReactorListener listener) {
        listener.numberCheckCompleted(reactor, number, wasPrime);
        return true;
    }
}
