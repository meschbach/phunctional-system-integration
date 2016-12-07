/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/IsCompletedPredicate.java $
 * $Id: IsCompletedPredicate.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work;

import com.meschbach.cise.Predicate;
import com.meschbach.psi.example.dprime.work.Work;
import com.meschbach.psi.example.dprime.work.Work.State;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class IsCompletedPredicate<W extends Work, T extends Exception> implements Predicate<W, T> {

    public boolean evaluateTruth(Work anElement) throws T {
        return anElement.getState().equals(State.Completed);
    }
}
