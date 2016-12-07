/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/pkb/PrimeReactorListener.java $
 * $Id: PrimeReactorListener.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.pkb;

import java.math.BigDecimal;
import java.util.EventListener;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public interface PrimeReactorListener extends EventListener {

    /**
     * Notifies the listener we have determined if <code>number</code> is
     * prime.
     * @param r is the reactor triggering the event
     * @param number is the number we are seeking
     * @param isPrime is a flag indicating if the number is prime.
     */
    public void numberCheckCompleted(PrimerReactor r, BigDecimal number, boolean isPrime);

    /**
     * Notifies all listeners we have reached our target are no longer
     * checking any more numbers.
     *
     * @param r the reactor which is the event source
     */
    public void completedPrimeChecks(PrimerReactor r);
}
