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
import com.meschbach.psi.util.HttpStatusCode;
import java.io.IOException;
import javax.xml.bind.JAXB;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/**
 *
 * @author "Mark Eschbach" (meschbach@gmail.com)
 * @version 2.5.0
 * @since 2.5.0
 */
public abstract class JAXBResponseHandler<T> implements ResponseHandler {

    Class<T> type;

    public JAXBResponseHandler(Class<T> type) {
        this.type = type;
    }

    public void handleResponse(HttpResponse response, HttpStatusCode stautsCode) throws PSIException {
        try {
            HttpEntity entity = response.getEntity();
            T result = JAXB.unmarshal(entity.getContent(), type);
            response(result);
        } catch (IOException ioe) {
            throw new PSIException("Failed to handle unmarshalled response of type " + type.getName(), ioe);
        }
    }

    protected abstract void response(T result) throws PSIException;
}
