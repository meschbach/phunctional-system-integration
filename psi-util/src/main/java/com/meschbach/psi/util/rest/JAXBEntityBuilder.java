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
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXB;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

/**
 * A <code>JAXBEntityBuilder</code> utilizes JAXB to marshal the given object
 * into a text/xml request.
 * 
 * @author "Mark Eschbach" (meschbach@gmail.com)
 * @since 2.5.0
 * @version 2.5.0
 */
public class JAXBEntityBuilder implements EntityBuilder {

    Object target;

    public JAXBEntityBuilder(Object target) {
        this.target = target;
    }

    public HttpEntity buildEntity() throws PSIException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JAXB.marshal(target, baos);
            return new StringEntity(baos.toString());
        } catch (Exception e) {
            throw new PSIException("Unable to marshall object " + target + "via JAXB", e);
        }
    }
}
