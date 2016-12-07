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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-container-compliance/src/main/java/com/meschbach/psi/cct/ContainerCompatabilityFixture.java $
 * $Id: ContainerCompatabilityFixture.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.cct;

import com.meschbach.psi.Container;
import com.meschbach.psi.PSIException;
import com.meschbach.psi.util.HttpStatusCode;
import com.meschbach.psi.util.WebAppHarness;
import org.testng.annotations.Test;
import static com.meschbach.psi.util.RESTClient.*;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mario Tinoco" marioandrest@gmail.com
 */
public abstract class ContainerCompatabilityFixture {

    public abstract Container buildContainer() throws PSIException;

    @Test
    public void testAbleToStartAndStop() throws PSIException {
        Container container = buildContainer();
        container.shutdown();
    }

    @Test
    public void testRetrievesStaticResources() throws PSIException {
        WebAppHarness harness = new WebAppHarness(buildContainer(), "echo-webapp", "/Echo");
        try {
            harness.start();
            assertGetStatus(harness.getURL("/index.html"), HttpStatusCode.Ok);
        } finally {
            harness.shutdown();
        }
    }

    @Test
    public void testEchoGet() throws PSIException {
        WebAppHarness harness = new WebAppHarness(buildContainer(), "echo-webapp", "/Echo");
        try {
            harness.start();
            final String testGET = "this_is_a_test_input";
            String echoURL = harness.getURL("/EchoServlet");
            assertGet(echoURL + "?input=" + testGET, testGET);
        } finally {
            harness.shutdown();
        }
    }

    @Test
    public void testServicePortNotZero() throws PSIException {
        Container container = buildContainer();
        try {
            assertNotSame(container.getServicePort(), 0);
        } finally {
            container.shutdown();
        }
    }

    @Test
    public void getURLDoesntContainTrailingSlash() throws PSIException {
        Container container = buildContainer();
        try {
            assertFalse(container.getRemoteURL().endsWith("/"));
        } finally {
            container.shutdown();
        }
    }
}
