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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-jetty7/src/main/java/com/meschbach/psi/jetty7/Jetty7Builder.java $
 * $Id: Jetty7Builder.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.jetty7;

import com.meschbach.psi.Container;
import com.meschbach.psi.ContainerFactory;
import com.meschbach.psi.PSIException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class Jetty7Builder implements ContainerFactory {

    public Jetty7Builder() {
    }

    public Container buildContainer() throws PSIException {
        try {
            /*
             * Construct and install our connector
             */
            SelectChannelConnector scc = new SelectChannelConnector();
            scc.setPort(0);
            /*
             * Create our root context handler
             */
            ContextHandlerCollection chc = new ContextHandlerCollection();
            /*
             * Create our new Jetty Server
             */
            Server s = new Server();
            s.addConnector(scc);
            s.setHandler(chc);
            s.start();
            /*
             *
             */
            assert scc.getLocalPort() != 0 : "Port was cached by channel selector";
            /*
             * 
             */
            return new Jetty7Container(s, scc, chc);
        } catch (Exception e) {
            throw new PSIException(e);
        }
    }
}
