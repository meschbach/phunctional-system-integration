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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi/src/test/java/com/meschbach/psi/util/RESTClientTest.java $
 * $Id: RESTClientTest.java 320 2011-03-12 06:41:14Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.jetty6.LocalJetty6Builder;
import com.meschbach.psi.PSIException;
import org.testng.annotations.Test;
import static com.meschbach.psi.util.RESTClient.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class RESTClientTest {

    @Test
    public void testIssue1() throws PSIException {
        WebAppHarness harness = new WebAppHarness(new LocalJetty6Builder(), "salutator", "/salutator");
        try {
            harness.start();
            /*
             * Assert our get
             */
            final String resourceName = RFC2396.encode("/dci/salutator/test name");
            assertGetStatus(harness.getURL() + resourceName, HttpStatusCode.NotFound);
        } finally {
            harness.shutdown();
        }
    }
}
