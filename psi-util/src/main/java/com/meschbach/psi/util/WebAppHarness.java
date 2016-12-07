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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/WebAppHarness.java $
 * $Id: WebAppHarness.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.Client;
import com.meschbach.psi.Container;
import com.meschbach.psi.ContainerFactory;
import com.meschbach.psi.PSI;
import com.meschbach.psi.PSIException;
import java.io.File;

/**
 * A <code>WebAppHarness</code> is a testing harness providing a facade around
 * the components required to locate and deploy a web application.  The idea is
 * to hide many of the details which would be repetitive in locating and
 * deploying web applications.<p>
 *
 * At the current time the client is responsible for providing the web container
 * created by a PSI implementation.  In the next version a factory pattern will
 * most likely be added then this class will most likely be modified to take
 * in a factory-like object as opposed to an actual client instance.<p>
 *
 * This class was designed to be utilized in test cases.  As a result of this,
 * and an attempt to stay testing framework neutral, this class utilizes the
 * assert keyword to ensure consistent state.  If you are having trouble with
 * this class failing with NPEs I would suggest you enable assertions<p>
 *
 * This class has been designed to resolve artifacts from a Maven styled build
 * system.  As a result the artifact resolution looks in the
 * "./target/dependency" directory for a file starting with
 * <code>webAppArtifact</code>.  To ensure Maven pulls in the dependencies prior
 * to testing, please add the following to your Maven pom.xml file:
 *
 * <project>
 *      ...
 *      <build>
 *          ...
 *          <plugins>
 *              ...
 *               <plugin>
 *                  <groupId>org.apache.maven.plugins</groupId>
 *                  <artifactId>maven-dependency-plugin</artifactId>
 *                  <executions>
 *                      <execution>
 *                          <id>copy-dependencies</id>
 *                          <phase>compile</phase>
 *                          <goals>
 *                              <goal>copy-dependencies</goal>
 *                          </goals>
 *                          <configuration>
 *                <!-- configure the plugin here -->
 *                          </configuration>
 *                      </execution>
 *                  </executions>
 *              </plugin>
 *              ...
 *          </plugins>
 *          ...
 *      </build>
 *      ...
 * </project>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.1.0
 * @version 1.0.1
 */
public class WebAppHarness {

    /**
     * The <code>psiClient</code> is a client for interacting with the PSI
     * container. Note: in the next version this will most likely be constructed
     * in by another instance variable which is a factory pattern.
     */
    protected Client psiClient;
    /**
     * The <code>psi</code> is an instance of the PSI utility class wrapping the
     * client service.  This is initialized in the start() method.
     */
    protected PSI psi;
    /**
     * The <code>artifactLocator</code> represents the algorithm for locating the
     * WAR to be deployed.
     */
    protected FileArtifactLocator artifactLocator;
    /**
     * The <code>contextPath</code> is the context path to deploy our artifact
     * too.
     */
    protected String contextPath;
    /**
     * The <code>artifact</code> is a <code>File</code> representing the actual
     * artifact which will be deployed.  This field is currently initialized
     * by the resolveWebAppArtifact() method, however this may change in the
     * future.
     */
    protected File artifact;

    /**
     * Cosntructs a new <code>WebAppHarness</code> with a factory for
     * creating a new container, the specified artifact prefix, and
     * a given context path.
     *
     * @param factory is the factory to utilize
     * @param webAppArtifact is the artifact name to utilize
     * @param contextPath is the context path
     */
    public WebAppHarness(ContainerFactory factory, String webAppArtifact, String contextPath) throws PSIException {
        this(factory.buildContainer(), webAppArtifact, contextPath);
    }

    /**
     * Constructs a new harness with the specified web container which will
     * locate the WAR with the specified artifact name and deploy the artifact
     * to the given context path.
     * 
     * @param psiClient is the PSI client interface to the container to utilize
     * @param webAppArtifact is the name of the artifact to be tested.
     * @param contextPath is the context path t the deploy the artifact too
     */
    public WebAppHarness(Container psiClient, String webAppArtifact, String contextPath) {
        this(psiClient, new MavenFileArtifactResolver(webAppArtifact), contextPath);
    }

    /**
     * Constructs a new WebAppHarness with the specified container, deploying
     * the artifact resolved by <code>locator</code> to the specified
     * <code>contextPath</code>.
     * 
     * @param psiClient is the container to deploy too
     * @param locator is the algorithm for locating the WAR to be deployed
     * @param contextPath is the context path to be deployed too
     */
    public WebAppHarness(Container psiClient, FileArtifactLocator locator, String contextPath) {
        this.psiClient = psiClient;
        this.artifactLocator = locator;
        this.contextPath = contextPath;
    }

    /**
     * Resolve the web application artifact to be deployed.  This method will
     * fail for a number of reason with assertions, which I have attempted to be
     * as explicit as possible why.<p>
     *
     * This method should really be considered internal as I'm sure future
     *  versions of this class will resolve artifacts utilizing a different set
     * of algorithms.
     */
    public void resolveWebAppArtifact() throws PSIException {
        assert artifact == null : "Dependency has already been resolved";
        /*
         * Locate the directory containing the dependencies
         */
        artifact = artifactLocator.resolveArtifact();
    }

    /**
     * Locates an deploys our artifact within the given web container.
     * 
     * @throws PSIException
     */
    public void start() throws PSIException {
        assert psi == null : "Create a fresh instance of the harness";
        /*
         * Resolve our dependencies
         */
        if (artifact == null) {
            resolveWebAppArtifact();
        }
        /*
         * Start our web application service
         */
        psi = new PSI(psiClient);
        /*
         * Deploy the dependency
         */
        psi.deploy(contextPath, artifact);
    }

    /**
     * Shutdowns the PSI instance and attempts resource cleanup.  This method
     * makes a reasonable attempt to ensure this method remains sane through
     * all internal state transitions.
     * 
     * @throws PSIException
     */
    public void shutdown() throws PSIException {
        if (psi != null) {
            psi.shutdown();
        }
    }

    /**
     * @return the URL of the web application
     */
    public String getURL() {
        return psi.getWebServiceURL() + contextPath;
    }

    /**
     * Concatenates a given resource name on to the end of the deployment URL.
     *
     * @param aResource is the resource to append to the end of the URL
     * @return the application url with the resource name appended
     */
    public String getURL(String aResource) {
        return getURL() + aResource;
    }
}
