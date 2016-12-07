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
 * $HeadURL$
 * $Id$
 */
package com.meschbach.psi.util.rest;

import com.meschbach.psi.PSIException;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * An <code>AbstractEntityRequest</code> is an abstract base class for requests
 * which allow for an HTTP entity to accompany an HTTP request.
 * 
 * @author "Mark Eschbach" (meschbach@gmail.com)
 * @version 2.5.0
 * @since 2.5.0
 */
public abstract class AbstractEntityRequest implements RequestBuilder {

    EntityBuilder builder;

    public AbstractEntityRequest() {
    }

    public AbstractEntityRequest(String value) {
        builder = new StringEntityBuilder(value);
    }

    public AbstractEntityRequest(EntityBuilder builder) {
        this.builder = builder;
    }

    protected abstract HttpUriRequest build(URI resource);

    protected abstract HttpUriRequest build(URI resource, HttpEntity entity);

    public HttpUriRequest buildRequest(URI resource) throws PSIException {
        HttpUriRequest req;
        if (builder == null) {
            req = build(resource);
        } else {
            req = build(resource, builder.buildEntity());
        }
        return req;
    }
}
