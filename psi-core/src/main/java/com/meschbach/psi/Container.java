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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/Container.java $
 * $Id: Container.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

/**
 * A <code>Container</code> represents a Web Application container, providing
 * the basic deployment and life cycle (okay, just shutdown) services.  I
 * recommend using a ContainerFactory to construct a Container.<p>
 *
 * This interface is intended to replace the <code>Client</code> interface from
 * which it is derived.  In the next major revision of this framework the
 * <code>Client</code> interface will be removed an all methods will be moved
 * into this interface.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mario Tinoco" marioandrest@gmail.com
 * @since 2.2.0
 * @version 1.1.0
 */
public interface Container extends Client {

    /**
     * Returns the connection port for HTTP
     *
     * @return HTTP service port for connecting to container
     * @since 2.4.0
     */
    public int getServicePort();
}
