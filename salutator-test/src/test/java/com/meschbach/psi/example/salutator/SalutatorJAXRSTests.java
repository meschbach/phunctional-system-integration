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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/salutator-test/src/test/java/com/meschbach/psi/example/salutator/SalutatorJAXRSTests.java $
 * $Id: SalutatorJAXRSTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.salutator;

import com.meschbach.psi.jetty6.LocalJetty6Builder;
import com.meschbach.psi.util.HttpStatusCode;
import com.meschbach.psi.util.WebAppHarness;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.meschbach.psi.util.RESTClient.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class SalutatorJAXRSTests {

    WebAppHarness harness;

    @BeforeMethod
    public void setupHarness() throws Throwable {
        harness = new WebAppHarness(new LocalJetty6Builder(), "salutator", "/salutator");
        harness.start();
    }

    @AfterMethod
    public void shutdownHarness() throws Throwable {
        harness.shutdown();
    }

    public String getServiceURL() {
        return harness.getURL() + "/dci/salutator/";
    }

    @Test
    public void testDeployment() {
        assertGetStatus(harness.getURL(), HttpStatusCode.Ok);
    }

    @Test(dependsOnMethods = {"testDeployment"})
    public void test404OnNoGreeting() {
        assertGetStatus(getServiceURL() + "doesNotExist", HttpStatusCode.NotFound);
    }

    @Test(dependsOnMethods = {"testDeployment"})
    public void testCreateGreeting() {
        assertPutStatus(getServiceURL() + "brian", "Hey!", HttpStatusCode.Created);
    }

    @Test(dependsOnMethods = {"testCreateGreeting"})
    public void testGetSetGreeting() {
        final String resource = getServiceURL() + "mario";
        final String greeting = getServiceURL() + "Whats up slacker?";
        assertPutStatus(resource, greeting, HttpStatusCode.Created);
        assertGet(resource, greeting);
    }

    @Test(dependsOnMethods = {"testDeployment"})
    public void testOverwriteGreetingResultsInNoContent() {
        final String resource = getServiceURL() + "overwrite";
        final String greeting1 = "Test greeting";
        final String greeting2 = "Greeting 2";

        assertPutStatus(resource, greeting1, HttpStatusCode.Created);
        assertPutStatus(resource, greeting2, HttpStatusCode.NoContent);
        assertGet(resource, greeting2);
    }
}
