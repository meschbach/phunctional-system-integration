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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/rest/ResponseHandler.java $
 * $Id: ResponseHandler.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util.rest;

import com.meschbach.psi.PSIException;
import com.meschbach.psi.util.HttpStatusCode;
import org.apache.http.HttpResponse;

/**
 * A <code>ResponseHandler</code> performs an algorithm specific operation
 * upon the completion of an HTTP request.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0 (PSI 2.3.0)
 * @version  1.0.0
 */
public interface ResponseHandler {

    /**
     * Notifies the handler a specific HTTP response was received
     * 
     * @param response is the response received from the remote host
     * @param stautsCode is the status code extracted from the response
     * @throws PSIException if a problem occurs processing the response
     */
    public void handleResponse(HttpResponse response, HttpStatusCode stautsCode) throws PSIException;
}
