/*
 *  Copyright 2007-2011 Mark Eschbach
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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/event/EventPump.java $
 * $Id: EventPump.java 327 2011-04-01 21:32:21Z meschbach@gmail.com $
 */
package com.meschbach.cise.event;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * An <code>EventPump</code> manages and dispatches events to a set of listeners.
 * A <code>EventPump</code> is really nothing more than a book keeper for the
 * listeners, allowing a dispatcher to visit each listener.
 * <p>
 * For each message you would like to dispatch you should implement a dispatcher
 * with the specified type.  For simple systems you could implement this as
 * an anonymous inner class while for more complex systems the dispatchers
 * should be first class objects.
 * <p>
 * The order that listeners will be notified is an implementation detail and
 * client code should not assume any specific order.
 * <p>
 * Ideal the event listener would be a subclass java.util.EventListener, however
 * enforcing this as the type parameter is a poor idea.  Many frameworks may
 * use a delegate pattern or even use the event pattern, however they do not
 * derived from the JVM standard event listener object.  By not forcing the type
 * parameter we allow for greater reuse without compromising code integrity.
 * <p>
 * This is code originating from Mark Eschbach's personal library and is licensed
 * under the Apache License, Version 2.0.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 3.0.0
 * @since 1.4.0
 *
 * @param L is the type of the listener to dispatch to, ideally java.util.EventListener
 */
public class EventPump<L> {

    /**
     * The <code>listeners</code> contain a list of listeners to visit while
     * dispatching.
     */
    private List<L> listeners;

    /**
     * Creates a new <code>EventPump</code> backed by a
     * <code>CopyOnWriteArrayList</code> for concurrent modifications.
     */
    public EventPump() {
        this(new CopyOnWriteArrayList<L>());
    }

    /**
     * Creates a new <code>EventPump</code> backed by the given list algorithm.
     *
     * @param listeners is a container algorithm to use for the lists
     */
    public EventPump(List<L> listeners) {
        this.listeners = listeners;
    }

    /**
     * Adds a given <code>listener</code> to the list to be visited while
     * dispatching.
     *
     * @param listener is the listener to visit while dispatching
     */
    public void addListener(L listener) {
        listeners.add(listener);
    }

    /**
     * Removes a given <code>listener</code> from the visitation list while
     * dispatching.
     * 
     * @param listener is the listener not to visit
     */
    public void removeListener(L listener) {
        listeners.remove(listener);
    }

    /**
     * Visits all listeners, sending the listeners to the <code>dispatcher</code>
     * 
     * @param dispatcher object to send messages to the listeners
     * @return true if dispatched to all listeners, otherwise false
     * @throws RuntimeException if an exceptional condition occurs
     */
    public boolean dispatch(final Dispatcher<L> dispatcher) {
        try {
            return dispatch(new ThrowableDispatcher<L, Throwable>() {

                public boolean dispatch(L aListener) throws Throwable {
                    return dispatcher.dispatch(aListener);
                }
            });
        } catch (Throwable t) {
            throw new RuntimeException("Is this a violation of LSP?", t);
        }
    }

    /**
     * Dispatches each a given message to each of the listeners.
     *
     * @param <T> is an exceptional condition may be which maybe raised
     * @param dispatcher will dispatch the message to the listeners
     * @return true if visited all listeners, otherwise false
     * @throws T if thrown by the dispatcher
     */
    public <T extends Throwable> boolean dispatch(ThrowableDispatcher<L, T> dispatcher) throws T {
        Iterator<L> lit = listeners.iterator();
        boolean continueDispatching = true;
        while (lit.hasNext() && continueDispatching) {
            continueDispatching = dispatcher.dispatch(lit.next());
        }
        return continueDispatching;
    }
}
