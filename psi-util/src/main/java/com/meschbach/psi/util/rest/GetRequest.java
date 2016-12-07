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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/rest/GetRequest.java $
 * $Id: GetRequest.java 315 2011-03-12 02:24:09Z meschbach@gmail.com $
 */
package com.meschbach.psi.util.rest;

import com.meschbach.psi.PSIException;
import java.net.URI;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * A <code>GetRequest</code> is a kind of <code>RequestBuilder</code> geared
 * towards issuing an HTTP GET request.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0 (PSI 2.3.0)
 * @version 1.0.0
 */
public class GetRequest implements RequestBuilder {

    public GetRequest() {
    }

    public HttpUriRequest buildRequest(URI resource) throws PSIException {
        return new HttpGet(resource);
    }
}
