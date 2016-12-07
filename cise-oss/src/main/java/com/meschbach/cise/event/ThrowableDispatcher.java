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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/event/ThrowableDispatcher.java $
 * $Id: ThrowableDispatcher.java 327 2011-04-01 21:32:21Z meschbach@gmail.com $
 */
package com.meschbach.cise.event;

import java.util.EventListener;

/**
 * A <code>ThrowableDispatcher</code> provides a service <code>dispatch</code>
 * which is intended to send a message, with a possible exceptional condition
 * being raised.  This interface allows an EventPump to full fill LSP in a type
 * safe way for listeners which may result in an exceptional condition.  In
 * All other respects, a <code>ThrowableDispatcher</code> is no different than
 * a <code>Dispatcher</code>.
 * <p>
 * Please see the documentation with the EventPump regarding the usage of 
 * the listener type regarding java.util.EventListener.
 * <p>
 * This is code originating from Mark Eschbach's personal library and is licensed
 * under the Apache License, Version 2.0.
 *
 * @param L is the event listener type to dispatch too
 * @param E is the exception which may be thrown while attempting to dispatch
 * 
 * @author "Mark Eschbach" meschbach@gmail.com;
 * @since 1.4.0
 * @version 1.0.0
 * @see  Dispatcher
 */
public interface ThrowableDispatcher<L, E extends Throwable> {

    /**
     * Instructs the given dispatcher to send its message to the given
     * listener.
     * 
     * @param aListener is the listener the pump is currently visiting
     * @return true to continue dispatching, otherwise false
     * @throws E an exceptional condition which may occur
     */
    public boolean dispatch(L aListener) throws E;
}
