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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi/src/test/java/com/meschbach/psi/util/Issue11Test.java $
 * $Id: Issue11Test.java 320 2011-03-12 06:41:14Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.PSIException;
import com.meschbach.psi.jetty6.LocalJetty6Builder;
import com.meschbach.psi.util.rest.PostRequest;
import com.meschbach.psi.util.rest.PutRequest;
import com.meschbach.psi.util.rest.RequestBuilder;
import com.meschbach.psi.util.rest.ResponseEntityAssertion;
import com.meschbach.psi.util.rest.StatusAssertionResponseHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class Issue11Test {

    WebAppHarness harness;
    final String testEntity = "This is an a test entity.  Hopefully this will work.";

    @BeforeMethod
    public void setupHarness() throws PSIException {
        harness = new WebAppHarness(new LocalJetty6Builder(), "echo-webapp", "/Echo");
        harness.start();
    }

    @AfterMethod
    public void takeDownHarness() throws PSIException {
        harness.shutdown();
    }

    private void doTest(RequestBuilder builder) throws PSIException {
        RESTClient client = new RESTClient(harness.getURL("/EchoEntity"), builder);
        client.addHandler(new StatusAssertionResponseHandler(HttpStatusCode.Ok));
        client.addHandler(new ResponseEntityAssertion(testEntity));
        client.doRequest();
    }

    @Test
    public void testPutEntity() throws PSIException {
        doTest(new PutRequest(testEntity));
    }

    @Test
    public void testPostEntity() throws PSIException {
        doTest(new PostRequest(testEntity));
    }
}
