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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/rest/StatusAssertionResponseHandler.java $
 * $Id: StatusAssertionResponseHandler.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util.rest;

import com.meschbach.psi.util.HttpStatusCode;
import org.apache.http.HttpResponse;

/**
 * The <code>StatusAssertionResponseHandler</code> ensures a given response code
 * is received, otherwise an AssertionException is thrown.
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0 (PSI 2.3.0)
 * @version  1.0.0
 */
public class StatusAssertionResponseHandler implements ResponseHandler {

    /**
     * The expected status code.
     */
    protected HttpStatusCode expected;

    /**
     * Constructs a new <code>StatusAssertionResponseHandler</code> which will
     * assert that <code>expected</code> was received as a response.
     * 
     * @param expected is the expected value
     */
    public StatusAssertionResponseHandler(HttpStatusCode expected) {
        this.expected = expected;
    }

    public void handleResponse(HttpResponse response, HttpStatusCode statusCode) {
        assert statusCode.equals(expected) : "Excepted response code " + expected + "; got response " + statusCode;
    }
}
