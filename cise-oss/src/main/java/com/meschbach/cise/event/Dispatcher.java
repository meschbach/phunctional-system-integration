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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/event/Dispatcher.java $
 * $Id: Dispatcher.java 327 2011-04-01 21:32:21Z meschbach@gmail.com $
 */
package com.meschbach.cise.event;

import java.util.EventListener;

/**
 * A <code>Dispatcher</code> sends a message to a given <code>listener</code>.
 * <p>
 * Please see the documentation with the EventPump regarding the usage of the
 * java.util.EventListener interface.
 * <p>
 * This is code originating from Mark Eschbach's personal library and is licensed
 * under the Apache License, Version 2.0.
 * 
 * @param E is the type of the event listener to be dispatched too
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 1.0.0
 * @since  1.4.0
 * @see  ThrowableDispatcher
 */
public interface Dispatcher<E> {

    /**
     * Instructs this dispatcher to dispatch to the given listener, optionally
     * terminating the listener visitation
     * 
     * @param listener is the listener to dispatch too
     * @return true to continue, false to terminate listener visitation
     */
    public boolean dispatch(E listener);
}
