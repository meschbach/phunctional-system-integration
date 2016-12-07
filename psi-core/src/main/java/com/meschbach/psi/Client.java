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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/Client.java $
 * $Id: Client.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

import java.io.InputStream;

/**
 * ===NOTE===: This class is deprecated, use
 * <code>com.meschbach.psi.Container</code> instead.
 *
 * This class is not marked as deprecated because the annotation would
 * contaminate the entire hierarchy and the replacement interface is derived
 * during the cross over period.  Please refactor your code to use the
 * container interface<p>
 *
 * A <code>Client</code> is the management interface to the web service.<p>
 *
 * The Client should be provided by the construction algorithm (either a builder,
 * or a factory ).  A <code>Client</code> is intended to be the primary method
 * of communication with the Servlet container.<p>
 *
 * NOTE: This is an extremely poor name for this interface.  The name should
 * have been <code>Container</code>, or something of the like and will be
 * changed in version 3.<p>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 2.0.5
 * @since 1.0.0
 */
public interface Client {

    /**
     * Retrieves the remote URL to contact the deployed web applications.  This
     * is the base URL for the service container itself and will be in a proper
     * URL format (IE: http://localhost:8080/).
     * 
     * @return a string pointing to the remote URL of the remote server
     * @since 1.0.0
     */
    public String getRemoteURL();

    /**
     * Shuts down the remote service container for operation.  This method is
     * used asynchronisly with the service, and just notifies the service of
     * a desire to shutdown.
     * 
     * @throws PSIException if a problem occurs while requesting the shutdown
     * @since 1.0
     */
    public void shutdown() throws PSIException;

    /**
     * Requests the remote service to deploy the given Web Application Archive
     * to to the given PSI service container.  As of current the only way to
     * deploy is using an existing file.
     *
     * @param aContext the context path to deploy the service under
     * @param aWebApplicationArchive is the archive to deploy to the service
     * @throws PSIException if a problem occurs while attempting to request deployment
     */
    public void deploy(String aContext, InputStream aWebApplicationArchive) throws PSIException;
}
