/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/AbstractDistributedWork.java $
 * $Id: AbstractDistributedWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public abstract class AbstractDistributedWork<T, Q, A> extends AbstractWork<T> implements DistributedWork<T, Q, A> {

    T result;

    public AbstractDistributedWork() {
    }

    public T getResult() {
        return result;
    }

    protected synchronized void setResult(T aResult) {
        result = aResult;
        dispatchCompleted();
    }

    public Q createRequest() {
        dispatchCalculating();
        return createDistributedWork();
    }

    protected abstract Q createDistributedWork();

    public void responseRecieved(A answer) {
        T aResult = calculateResponseFromAnswer(answer);
        setResult(aResult);
    }

    protected abstract T calculateResponseFromAnswer(A answer);
}
