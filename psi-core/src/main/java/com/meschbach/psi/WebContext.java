/*
 *  Copyright 2010-2011 Mark Eschbach
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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/WebContext.java $
 * $Id: WebContext.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

/**
 * A <code>WebContext</code> is a contract interface defining server side
 * control on a specific context.<p>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 1.0.5
 * @since 1.0.0
 */
public interface WebContext {

    /**
     * Retrieves the name of the given context.  This should be the context path
     * of this context.
     *
     * @return a string containing the context path
     */
    public String getName();

    /**
     * Starts the represented context.  If the current context is already running
     * then the results are undefined, however if possible it would be nice
     * if an exception could be thrown.
     *
     * @throws PSIException if a problem occurs starting the context path
     */
    public void start() throws PSIException;

    /**
     * Stop the represented context.  If the current context is not running then
     * the results are undefined.
     *
     * @throws PSIException if a problem occurs stopping the context
     */
    public void stop() throws PSIException;
}
