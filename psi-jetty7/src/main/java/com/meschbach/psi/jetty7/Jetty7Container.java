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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-jetty7/src/main/java/com/meschbach/psi/jetty7/Jetty7Container.java $
 * $Id: Jetty7Container.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.jetty7;

import com.meschbach.cise.util.StreamCopier;
import com.meschbach.psi.Container;
import com.meschbach.psi.PSIException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mario Tinoco" marioandrest@gmail.com
 * @version 1.1.0
 */
public class Jetty7Container implements Container {

    Server service;
    SelectChannelConnector connector;
    ContextHandlerCollection contexts;

    public Jetty7Container(Server service, SelectChannelConnector connector, ContextHandlerCollection contexts) {
        this.service = service;
        this.connector = connector;
        this.contexts = contexts;
    }

    public void deploy(String aContext, InputStream aWebApplicationArchive) throws PSIException {
        try {
            /*
             * Copy the WAR
             */
            File f = File.createTempFile(aContext.substring(1), ".war");
            f.deleteOnExit();
            /*
             * Write the WAR to disk
             */
            FileOutputStream fos = new FileOutputStream(f);
            StreamCopier.copy(aWebApplicationArchive, fos);
            /*
             * Setup our deployer
             */
            WebAppContext wac = new WebAppContext();
            wac.setContextPath(aContext);
            wac.setWar(f.getAbsolutePath());
            wac.setParentLoaderPriority(false);
            wac.setExtractWAR(false);
            /*
             * Add to the context handlers
             */
            contexts.addHandler(wac);
            if (!wac.isRunning()) {
                try {
                    wac.start();
                } catch (Exception e) {
                    throw new PSIException(e);
                }
            }
        } catch (IOException ioe) {
            throw new PSIException(ioe);
        }
    }

    public String getRemoteURL() {
        return "http://localhost:" + getServicePort();
    }

    public int getServicePort() {
        return connector.getLocalPort();
    }

    public void shutdown() throws PSIException {
        try {
            service.stop();
        } catch (Throwable t) {
            throw new PSIException(t);
        }
    }
}
