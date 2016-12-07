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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/HttpStatusCode.java $
 * $Id: HttpStatusCode.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

/**
 * The <code>HttpStatusCode</code> is an enumeration of the HTTP status
 * codes which are defined in HTTP/1.1.
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0 (PSI 2.3.0)
 */
public enum HttpStatusCode {

    Ok(200),
    Created(201),
    NoContent(204),
    ResetContent(205),
    TemporaryRedirect(307),
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    ServerError(500),
    ServiceUnavailable(503);
    /**
     * The <code>code</code> represents the numeric version of an HTTP status
     * code.
     */
    protected int code;

    private HttpStatusCode(int code) {
        this.code = code;
    }

    /**
     * Translates the given HTTP status code into an enumeration element
     * @param statusCode is the status code we are interested in finding
     * @return the enumerated status code
     * @throws RuntimeException if the status code is not know
     */
    public static HttpStatusCode getCode(int statusCode) {
        for (HttpStatusCode code : HttpStatusCode.values()) {
            if (code.code == statusCode) {
                return code;
            }
        }
        throw new RuntimeException("Unknown code " + statusCode + "; please patch to expand");
    }
}
