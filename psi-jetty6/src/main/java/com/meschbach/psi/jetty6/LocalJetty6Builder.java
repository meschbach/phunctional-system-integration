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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-jetty6/src/main/java/com/meschbach/psi/jetty6/LocalJetty6Builder.java $
 * $Id: LocalJetty6Builder.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.jetty6;

import com.meschbach.cise.vfs.Util;
import com.meschbach.psi.ContainerFactory;
import com.meschbach.psi.PSIException;
import java.io.File;
import java.io.IOException;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;

/**
 * A <code>LocalJetty6Builder</code> builds a Jetty 6 PSI container.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 2.4.0
 * @version 1.0.0
 */
public class LocalJetty6Builder implements ContainerFactory {

    String workingDirectory;

    public LocalJetty6Builder() {
        workingDirectory = "target/jetty6";
    }

    public Jetty6Container buildContainer() throws PSIException {
        /*
         * Setup our working directory
         */
        File wd;
        try {
            wd = Util.createTemporaryDirectory("psi", "jetty6");
        } catch (IOException ioe) {
            throw new PSIException(ioe);
        }
        /*
         * Build our channel listener
         */
        SelectChannelConnector scc = new SelectChannelConnector();
        scc.setPort(0);

        /*
         * Build and configure our context handler
         */
        ContextHandlerCollection contextCollection = new ContextHandlerCollection();

        /*
         * Construct the Jetty 6 mediator
         */
        Server server = new Server();
        server.addConnector(scc);
        server.setHandler(contextCollection);
        /*
         * Start the service
         */
        try {
            server.start();
        } catch (Exception e) {
            throw new PSIException(e);
        }
        /*
         * 
         */
        return new Jetty6Container(server, contextCollection, scc, wd);
    }
}
