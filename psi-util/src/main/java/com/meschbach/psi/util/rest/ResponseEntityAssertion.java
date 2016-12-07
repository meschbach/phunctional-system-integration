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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/rest/ResponseEntityAssertion.java $
 * $Id: ResponseEntityAssertion.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util.rest;

import com.meschbach.psi.PSIException;
import com.meschbach.psi.util.HttpStatusCode;
import com.meschbach.psi.util.Util;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;

/**
 * A <code>ResponseEntityAssertion</code> ensures that a give response entity
 * is received from an HTTP request.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0 (PSI 2.3.0)
 * @version  1.0.0
 */
public class ResponseEntityAssertion implements ResponseHandler {

    /**
     * The <code>expected</code> is the expected entity of the request.
     */
    protected String expected;

    /**
     * Constructs a new entity assertion to ensure the response is the same
     * as what is given.
     * @param expected is the expected string
     */
    public ResponseEntityAssertion(String expected) {
        this.expected = expected;
    }

    public void handleResponse(HttpResponse response, HttpStatusCode stautsCode) throws PSIException {
        try {
            InputStream input = response.getEntity().getContent();
            String entity = Util.convertToString(input);
            assert entity.equals(expected) : "Expected HTTP entity '" + expected + "', recieved '" + entity + "'";
        } catch (IOException ioe) {
            throw new PSIException(ioe);
        }
    }
}
