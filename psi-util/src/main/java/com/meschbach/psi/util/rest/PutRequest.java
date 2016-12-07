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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/rest/PutRequest.java $
 * $Id: PutRequest.java 315 2011-03-12 02:24:09Z meschbach@gmail.com $
 */
package com.meschbach.psi.util.rest;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * A <code>PutRequest</code> encapsulates an HTTP PUT request with an HTTP
 * entity possibility attached.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 2.3.0
 * @version  2.5.0
 */
public class PutRequest extends AbstractEntityRequest {

    /**
     * Constructs a new PUT request without an HTTP entity with the request
     */
    public PutRequest() {
        super();
    }

    /**
     * Constructs a new PUT request builder with the specified entity
     * @param entity
     */
    public PutRequest(String entity) {
        super(entity);
    }

    public PutRequest(EntityBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpUriRequest build(URI resource) {
        return new HttpPut(resource);
    }

    @Override
    protected HttpUriRequest build(URI resource, HttpEntity entity) {
        HttpPut put = new HttpPut(resource);
        put.setEntity(entity);
        return put;
    }
}
