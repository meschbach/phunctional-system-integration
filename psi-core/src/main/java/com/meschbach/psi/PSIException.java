/*
 *  Copyright 2010-2011 Mark Eschbach
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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/PSIException.java $
 * $Id: PSIException.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

/**
 * A <code>PSIException</code> represent a checked except for problems occurring
 * within the PSI layer of components.<p>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class PSIException extends Exception {

    /**
     * Constructs a new <code>PSIException</code> with the specified root cause.
     * 
     * @param cause is the root cause of this exceptional condition
     */
    public PSIException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new <code>PSIException</code> with the specified detail
     * message with the specified root cause.
     * 
     * @param message is a detail message regarding why the exception was raised
     * @param cause is the root cause of the exception being raised.
     */
    public PSIException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new <code>PSIException</code> with the specified messaging
     * detailing the cause of the exception.
     *
     * @param message contains the details of why the exception occurred.
     */
    public PSIException(String message) {
        super(message);
    }

    /**
     * Throw a dog a freakin' bone here.  Don't use this constructor unless you
     * absolutely need to.
     */
    public PSIException() {
    }
}
