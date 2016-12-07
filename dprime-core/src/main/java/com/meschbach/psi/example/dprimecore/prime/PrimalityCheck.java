/*
 *  Copyright 2011 Mark Eschbach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/prime/PrimalityCheck.java $
 * $Id: PrimalityCheck.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.prime;

import java.math.BigDecimal;

/**
 * A <code>PrimalityCheck</code> is an algorithm for determining the next step
 * to finding if a number is prime.  A PrimalityCheck works with a
 * <code>WorkFactory</code> to produce implementation specific units of
 * work.  Upon completion the unit of work notifies the PrimalityCheck of the
 * result by invoking the <code>CompletionClousre#completedResult(BigDecimal)</code>
 * provided during construction.
 * <p>
 * I was attempting to be as generic as possible on specifying how work is to
 * be completed, or when.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @version 1.0.0
 */
public class PrimalityCheck<T, P> {

    /**
     * The <code>factory</code> is charged with creating units of work at the
     * request this object.  In practice this is utilized by the current state
     * when requesting the net action.
     */
    protected WorkFactory<T, P> factory;
    /**
     * The <code>current</code> state represents the current step in our series
     * of transitions to attempt to find our prime number.  This will change
     * each time the completion calls.
     */
    State<T> current;
    /**
     * The <code>maybePrime</code> is the actual prime number we are attempting
     * to check
     */
    BigDecimal maybePrime;
    /**
     * The <code>delegate</code> is notified after any time we have a state
     * transition.  This allows for both push and pull systems to be
     * established.
     */
    StateChangeDelegate<T, P> delegate;
    /**
     * The <code>primeState</code> represents the current prime state.  This is
     * intentionally vague to allow for great variation in how we obtain the
     * next prime number.  This reference is given to our <code>factory</code>
     * any time we need the next prime number.
     */
    P primeState;

    /**
     * Constructs a new <code>PrimalityCheck</code> with the specified work
     * factory, attempting to find if <code><maybePrime/code> is prime.
     * 
     * @param factory is the factory
     * @param maybePrime is the number we are checking for primality
     */
    public PrimalityCheck(WorkFactory<T, P> factory, BigDecimal maybePrime) {
        this.factory = factory;
        this.maybePrime = maybePrime;
        current = new FindNextPrimeState();
    }

    public StateChangeDelegate<T, P> getDelegate() {
        return delegate;
    }

    public void setDelegate(StateChangeDelegate<T, P> delegate) {
        this.delegate = delegate;
    }

    public WorkFactory<T, P> getFactory() {
        return factory;
    }

    public void setFactory(WorkFactory<T, P> factory) {
        this.factory = factory;
    }

    public P getPrimeState() {
        return primeState;
    }

    public void setPrimeState(P primeState) {
        this.primeState = primeState;
    }

    /**
     * This is a Convenience method to notify a delegate we have changed our
     * state.  This is useful is you are using the delegate to do the work.
     */
    public void start() {
        setState(current);
    }

    /**
     * Retrieves the next operation to be completed.  This method is reentrent
     * in that the next operation will always be similar (same type of
     * operation, however may be different object depending on your
     *  implementation of the work factory) until a state transition.
     * <p>
     * Once the operation has been performed and the completion closure invoked
     * this will cause a state transition and new operation will be returned.
     *
     * @return the next operation to be performed
     */
    public synchronized T nextOperation() {
        T op = current.buildOperation();
        return op;
    }

    /**
     * Transitions the current state to the given <code>next</code> state, then
     * notifies the delegate (if there is a delegate) of our state transition.
     *
     * @param next is the next state to be used.
     */
    protected synchronized void setState(State<T> next) {
        current = next;
        if (delegate != null) {
            delegate.changedState(this);
        }
    }

    /**
     * Asks for the next prime in the sequence.  When the next prime is
     * found our next state is to multiply to find the prime.
     */
    protected class FindNextPrimeState implements State<T>, CompletionClosure {

        public T buildOperation() {
            return factory.nextPrime(primeState, this);
        }

        public void completedResult(BigDecimal result) {
            setState(new Square(result));
        }
    }

    /**
     * Requests the square of a value.  Upon completion we check our square
     * against our prime to ensure we haven't passed our target.  If we have
     * then we have a prime number, otherwise divide the prime.
     */
    protected class Square implements State<T>, CompletionClosure {

        BigDecimal prime;

        public Square(BigDecimal prime) {
            this.prime = prime;
        }

        public T buildOperation() {
            return factory.square(prime, this);
        }

        public void completedResult(BigDecimal result) {
            State<T> state;
            if (maybePrime.compareTo(result) < 0) {
                state = new ResultState(true);
            } else {
                state = new DivideNumberState(prime);
            }
            setState(state);
        }
    }

    /**
     * Asks the client to divide our possible prime by the given prime.  When
     * complete, ensure that the resulting remainder is not zero and then set
     * the next state to find the next prime.
     */
    protected class DivideNumberState implements State<T>, CompletionClosure {

        BigDecimal divisor;

        public DivideNumberState(BigDecimal divisor) {
            this.divisor = divisor;
        }

        public T buildOperation() {
            return factory.divide(divisor, maybePrime, this);
        }

        public void completedResult(BigDecimal result) {
            State next;
            if (result.equals(BigDecimal.ZERO)) {
                next = new ResultState(false);
            } else {
                next = new FindNextPrimeState();
            }
            setState(next);
        }
    }

    /**
     * When we have reached our determination we set this state, which will
     * request the work factory to produce the result.
     */
    protected class ResultState implements State<T> {

        boolean result;

        public ResultState(boolean result) {
            this.result = result;
        }

        public T buildOperation() {
            return factory.result(result);
        }
    }
}
