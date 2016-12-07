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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/PSI.java $
 * $Id: PSI.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

import com.meschbach.cise.jam.ReplaceEntryVisitor;
import com.meschbach.cise.jam.ZipManipulator;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A <code>PSI</code> is a facade around the PSI components to simplify the
 * common use cases.<p>
 *
 * Please note:  In many Serlvet Containers initialization of a Web Application
 * may be delayed until the initial request for that web application.  For this
 * reason take a lack of a PSIException being raised upon deployment with a
 * grain of salt.<p>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 2.0.0
 * @version 1.0.5
 */
public class PSI {

    /**
     * The <code>client</code> is used to communicate with the Serlvet
     * Container.
     */
    protected Client client;

    /**
     * Constructs a new instance of <code>PSI</code>.  The created instance is
     * not valid until a client is set.  If you fail to set the client, then a
     * NPE may occur.
     *
     * @since 2.0.0
     */
    public PSI() {
    }

    /**
     * Constructs a new PSI instance with the container built by the specified
     * factory.
     *
     * @param factory the factory to build with
     * @throws PSIException if there is a problem constructing the container
     * @since 2.2.0
     */
    public PSI(ContainerFactory factory) throws PSIException {
        this(factory.buildContainer());
    }

    /**
     * Constructs a new instance of <code>PSI</code> with the given <code>Client</code>.
     *
     * @param aClient is the client interfacing with the Servlet Container
     *
     * @since 1.0.3
     */
    public PSI(Client aClient) {
        this.client = aClient;
    }

    /**
     * Retrieves the base URL for the remote web service.  This is a utility
     * method to decouple the user from the underlying implementation details of
     * determining this from the client itself; and for those of us who like
     * easier to read code :).
     *
     * @return the base URL of the remote web service
     * @since 1.0.0
     */
    public String getWebServiceURL() {
        return client.getRemoteURL();
    }

    /**
     * Deploys the given WAR with the specified context path.  Please note: the
     * exception may not be raised no many Servlet Containers because the
     * web application are lazily initialized.
     *
     * This is the most basic deployment scenario and easiest to use.
     * 
     * @param aContextPath the context path to deploy too
     * @param archive the file of the archive to deploy
     * @throws PSIException if a problem occurs during deployment
     * @since 1.0.0
     */
    public void deploy(String aContextPath, File archive) throws PSIException {
        /*
         * Initialize the file input stream
         */
        FileInputStream fis;
        try {
            fis = new FileInputStream(archive);
        } catch (IOException ioe) {
            throw new PSIException("Unable to open archive '" + archive.getAbsolutePath() + "'.", ioe);
        }
        try {
            /*
             * Wrap with a buffered stream  (much better performance in most cases)
             */
            BufferedInputStream bis = new BufferedInputStream(fis);
            /*
             * As our client to deploy
             */
            client.deploy(aContextPath, bis);
        } finally {
            /*
             * Ensure the input stream is cleaned up
             */
            try {
                fis.close();//I still don't get why the have close throw an exception
            } catch (IOException ioe) {
                throw new PSIException(ioe);
            }
        }
    }

    /**
     * Deploys the given archive with the specified context path, injecting an
     * alternate /WEB-INF/web.xml file.  This is useful for varying configurations
     * which may only be tuned via web.xml.<p/>
     *
     * This method will not modify the source archive and does not utilize
     * temporary files.
     * 
     * @param aContextPath is the context path to deploy the application too
     * @param anArchive is the archive containing the web application
     * @param anAltWebXml is a file to the alternate web.xml file to inject
     * @throws PSIException if a problem occurs while attempting modify the archive or the deployment stage
     * @since 1.0.0
     */
    public void deploy(String aContextPath, File anArchive, File anAltWebXml) throws PSIException {
        FileInputStream archiveInput = null, altInput = null;
        try {
            /*
             * Initialize the archive input
             */
            try {
                archiveInput = new FileInputStream(anArchive);
            } catch (IOException ioe) {
                throw new PSIException("Unable to open given archive '" + anArchive.getAbsolutePath() + "'.", ioe);
            }
            /*
             * Open the alternate descriptor
             */
            try {
                altInput = new FileInputStream(anAltWebXml);
            } catch (IOException ioe) {
                throw new PSIException("Unable to open alternate Web.xml file '" + anAltWebXml.getAbsolutePath() + "'.", ioe);
            }
            /*
             * Create our Zip manipulator
             */
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ZipManipulator zm = new ZipManipulator();
            //Configure I/O
            zm.setSource(new BufferedInputStream(archiveInput));
            zm.setDestination(buffer);
            /*
             * Configure our entry replacement for WEB-INF/web.xml
             */
            zm.addEntryVisitor("WEB-INF/web.xml", new ReplaceEntryVisitor(new BufferedInputStream(altInput)));
            /*
             * Perform the copy
             */
            try {
                zm.performOperations();
            } catch (IOException ioe) {
                throw new PSIException("Unable to inject alternative WEB-INF/web.xml", ioe);
            }
            /*
             * Feed our entry into the underlying layer
             */
            byte newArchive[] = buffer.toByteArray();
            client.deploy(aContextPath, new ByteArrayInputStream(newArchive));
        } finally {
            try {
                if (archiveInput != null) {
                    archiveInput.close();
                }
                if (altInput != null) {
                    altInput.close();
                }
            } catch (IOException ioe) {
                throw new PSIException(ioe);
            }
        }
    }

    /**
     * Undeploys the given web application identified by the specified context
     * path.
     *
     * @param aContextPath is the context path to undeploy
     * @throws PSIException if a problem is encounted while undeploying.
     * @since 1.0.0
     */
    public void undeploy(String aContextPath) throws PSIException {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Ensures the Servlet Container is shutdown.  After the client is shutdown
     * the reference to the client is released.  Any further access to this
     * instance will result in NPEs.
     *
     * @throws PSIException if the web container was unable to shutdown cleanly.
     * @since 1.0.0
     */
    public void shutdown() throws PSIException {
        client.shutdown();
        client = null;
    }

    /**
     * Retrieves the client utilized by this object.
     * 
     * @return the client currently being used by this instance of PSI
     * @since 1.0.0
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the client to the specified client.  If a current client is in use
     * then the that client will be asked to shutdown.
     *
     * @param client is the client to utilize
     * @throws PSIException if a problem occurs shutting down the current client
     */
    public void setClient(Client client) throws PSIException {
        if (this.client != null) {
            this.client.shutdown();
        }
        this.client = client;
    }
}
