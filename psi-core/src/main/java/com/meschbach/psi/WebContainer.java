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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/WebContainer.java $
 * $Id: WebContainer.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

import java.util.List;

/**
 * A <code>WebContainer</code> is a contract interface defining how a to
 * interact with a Servlet Container within the current process.<p>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.0.0
 * @version 1.0.4
 */
public interface WebContainer<C extends WebContext> {

    /**
     * Creates a new web context with the given context path and a path to
     * a local WAR.
     * 
     * @param aContextPath the context path to create the web application under
     * @param jar is string with the path of the jar to be deployed
     * @return is the created context
     */
    public C createContext(String aContextPath, String jar);

    /**
     * Lists all available web contexts
     * @return the list of all available contexts
     */
    public List<C> getContexts();

    /**
     * Start the given web container on the specified <code>port</code>.  If the
     * given <code>port</code> is zero, then the service will randomly chose
     * an available port and bind to that port.
     * 
     * @param port the port to start the server on
     * @return the actual port the service was constructed under.
     */
    public int start(int port);

    /**
     * @return true if the web container has been started and is currently running
     */
    public boolean isRunning();

    /**
     * Stop the HTTP process, allowing the process to be restarted
     */
    public void stop();

    /**
     * Stops the service and ends the JVM process
     */
    public void shutdown();
}
