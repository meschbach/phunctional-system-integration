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
import org.apache.http.HttpEntity;

/**
 * An <code>EntityBuilder</code> is an interface to an object which may be
 * configured to construct HTTP entities for testing.  The interface name and the
 * implementing classes are are considered apart of the public API, while the
 * methods of the interface are to be considered private.
 * 
 * @author "Mark Eschbach" (meschbach@gmail.com)
 * @since 2.5.0
 * @version 2.5.0
 */
public interface EntityBuilder {

    public HttpEntity buildEntity() throws PSIException;
}
