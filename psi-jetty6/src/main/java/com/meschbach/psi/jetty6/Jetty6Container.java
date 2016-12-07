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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-jetty6/src/main/java/com/meschbach/psi/jetty6/Jetty6Container.java $
 * $Id: Jetty6Container.java 320 2011-03-12 06:41:14Z marioandrest@gmail.com $
 */
package com.meschbach.psi.jetty6;

import com.meschbach.cise.util.StreamCopier;
import com.meschbach.cise.vfs.DeleteFileTree;
import com.meschbach.psi.Container;
import com.meschbach.psi.PSIException;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 1.0.0
 * @since 2.4.0
 */
public class Jetty6Container implements Container {

    File wd;
    Server jettyServer;
    ContextHandlerCollection contextHandlers;
    SelectChannelConnector connector;

    public Jetty6Container(Server jettyServer, ContextHandlerCollection contextHandlers, SelectChannelConnector connector, File wd) {
        this.jettyServer = jettyServer;
        this.contextHandlers = contextHandlers;
        this.connector = connector;
        this.wd = wd;
    }

    public void deploy(String aContext, InputStream aWebApplicationArchive) throws PSIException {
        /*
         * Check if a file by that name has already been deployed
         */
        String contextFileName = aContext.substring(1) + ".war";
        File targetFile = new File(wd, contextFileName);
        if (targetFile.exists()) {
            throw new PSIException("Context path" + aContext + " has a web application already deployed there");
        }
        /*
         * Pull the data into our war
         */
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);
            StreamCopier.copy(aWebApplicationArchive, fos);
        } catch (Exception e) {
            throw new PSIException("Unable to copy WAR contents", e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                throw new PSIException(e);
            }
        }
        /*
         * Create a new WebAppContext
         */
        WebAppContext wap = new WebAppContext();
        wap.setContextPath(aContext);
        wap.setWar(targetFile.getAbsolutePath());
        wap.setServer(jettyServer);
        contextHandlers.addHandler(wap);
        try {
            wap.start();
        } catch (Exception e) {
            throw new PSIException();
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
            jettyServer.stop();
            DeleteFileTree.delete(wd);
        } catch (Exception e) {
            throw new PSIException(e);
        }
    }
}
