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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-tomcat6/src/main/java/com/meschbach/psi/tomcat6/Tomcat6Builder.java $
 * $Id: Tomcat6Builder.java 325 2011-03-26 07:24:32Z marioandrest@gmail.com $
 */
package com.meschbach.psi.tomcat6;

import com.meschbach.cise.util.StreamCopier;
import com.meschbach.cise.vfs.Util;
import com.meschbach.psi.ContainerFactory;
import com.meschbach.psi.PSIException;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Embedded;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mario Tinoco" marioandrest@gmail.com
 */
public class Tomcat6Builder implements ContainerFactory {

    public Tomcat6Container buildContainer() throws PSIException {
        final File catalinaHome;
        File catalinaWebapps;
        File catalinaConf;

        // Extract Configuration files into temporary catalina home
        try {
            catalinaHome = Util.createTemporaryDirectory("tomcat6-", "-home");
            catalinaWebapps = new File(catalinaHome, "webapps");
            catalinaConf = new File(catalinaHome, "conf");
            if (!catalinaWebapps.mkdirs() || !catalinaConf.mkdirs()) {
                throw new PSIException("Unable to create configuration folder.");
            }
            extractConfigurationFile(catalinaConf, "/com/meschbach/psi/tomcat6/conf/", "context.xml");
            extractConfigurationFile(catalinaConf, "/com/meschbach/psi/tomcat6/conf/", "server.xml");
            extractConfigurationFile(catalinaConf, "/com/meschbach/psi/tomcat6/conf/", "web.xml");
        } catch (IOException ioe) {
            throw new PSIException(ioe);
        }

        // Create embedded
        Embedded embedded = new Embedded();


        // Adding cleanup shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    FileUtils.deleteDirectory(catalinaHome);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        embedded.setCatalinaHome(catalinaHome.getAbsolutePath());

        // Create host
        Host localHost = embedded.createHost("localHost", catalinaWebapps.getAbsolutePath());

        // Create engine
        Engine engine = embedded.createEngine();
        engine.setName("localEngine");
        engine.addChild(localHost);
        engine.setDefaultHost(localHost.getName());
        embedded.addEngine(engine);

        // Create Connector
        int port = findOpenPort();
        Connector connector = embedded.createConnector((InetAddress) null, port, false);
        embedded.addConnector(connector);


        // Start the contianer
        try {
            embedded.start();
        } catch (LifecycleException le) {
            throw new PSIException(le);
        }

        // Configure a Tomcat container for managing the container
        return new Tomcat6Container(catalinaHome, catalinaWebapps, "http://localhost:" + port, embedded, localHost, port);
    }

    /**
     * @param confFolder
     * @param resourcePath
     * @param filename
     * @throws IOException
     */
    public void extractConfigurationFile(File confFolder, String resourcePath, String filename)
            throws IOException {
        InputStream sourceStream = getClass().getResourceAsStream(resourcePath + filename);
        File targetFile = new File(confFolder, filename);
        OutputStream targetStream = new FileOutputStream(targetFile);
        try {
            StreamCopier.copy(sourceStream, targetStream);
        } finally {
            targetStream.close();
        }
    }

    /**
     * The following method is ugly.  Horribly ugly.  But its a necessity
     * unfortunately due to Tomcat sprinkling copies of data everywhere.
     */
    public int findOpenPort() throws PSIException {
        ServerSocket s = null;
        try {
            s = new ServerSocket(0);
            return s.getLocalPort();
        } catch (IOException e) {
            throw new PSIException(e);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException ioe) {
                    throw new PSIException(ioe);
                }
            }
        }
    }
}
