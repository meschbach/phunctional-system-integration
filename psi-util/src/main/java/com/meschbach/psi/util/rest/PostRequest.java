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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/rest/PostRequest.java $
 * $Id: PostRequest.java 315 2011-03-12 02:24:09Z meschbach@gmail.com $
 */
package com.meschbach.psi.util.rest;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * A <code>PostRequest</code> is a request builder for sending HTTP POST verbs
 * to the a remote URI, possibly with a given body.
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 2.5.0
 * @since 2.5.0
 */
public class PostRequest extends AbstractEntityRequest {

    public PostRequest() {
        super();
    }

    /**
     * Constructs a new PostRequest with the given entity.  If the input entity
     * is null then no HTTP entity will be attached.
     * 
     * @param entity is the entity 
     */
    public PostRequest(String entity) {
        super(entity);
    }

    public PostRequest(EntityBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpUriRequest build(URI resource) {
        return new HttpPost(resource);
    }

    @Override
    protected HttpUriRequest build(URI resource, HttpEntity entity) {
        HttpPost post = new HttpPost(resource);
        post.setEntity(entity);
        return post;
    }
}
