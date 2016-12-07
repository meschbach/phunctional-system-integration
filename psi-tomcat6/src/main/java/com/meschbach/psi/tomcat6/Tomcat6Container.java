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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-tomcat6/src/main/java/com/meschbach/psi/tomcat6/Tomcat6Container.java $
 * $Id: Tomcat6Container.java 325 2011-03-26 07:24:32Z marioandrest@gmail.com $
 */
package com.meschbach.psi.tomcat6;

import com.meschbach.cise.resource.zip.ZipExtractor;
import com.meschbach.psi.Container;
import com.meschbach.psi.PSIException;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Embedded;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mario Tinoco" marioandrest@gmail.com
 */
public class Tomcat6Container implements Container {

    File catalinaHome;
    File catalinaWebapps;
    String baseURL;
    Embedded embedded;
    Host host;
    int port;

    public Tomcat6Container(File catalinaHome, File catalinaWebapps, String baseURL, Embedded embedded, Host host, int port) {
        this.catalinaHome = catalinaHome;
        this.catalinaWebapps = catalinaWebapps;
        this.baseURL = baseURL;
        this.embedded = embedded;
        this.host = host;
        this.port = port;
    }

    public void deploy(String aContext, InputStream aWebApplicationArchive) throws PSIException {
        // Extract war into catalinaWebapps directory
        File file = new File(catalinaWebapps, aContext.substring(1));
        try {
            ZipExtractor.extractTo(aWebApplicationArchive, file);
        } catch (IOException ioe) {
            throw new PSIException(ioe);
        }

        // Deploy the extracted webapp into the container
        Context context = embedded.createContext(aContext, file.getAbsolutePath());
        host.addChild(context);
    }

    public String getRemoteURL() {
        return baseURL;
    }

    public void shutdown() throws PSIException {
        /*
         * Shutdown the container
         */
        try {
            embedded.stop();
        } catch (Exception ex) {
            throw new PSIException("Unable to stop the embedded container", ex);
        }
        /*
         * Need to add cleanup code without breaking the compatability tests
         * initial attempt broke ability to serve static files, so check tests.
         */
    }

    public int getServicePort() {
        return port;
    }
}
