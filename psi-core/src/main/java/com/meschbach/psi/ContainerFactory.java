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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/ContainerFactory.java $
 * $Id: ContainerFactory.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

/**
 * A <code>ContainerFactory</code> provides a Factory pattern for the
 * construction a container.<p>
 *
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 1.0.0
 * @since 2.2.0
 */
public interface ContainerFactory {

    /**
     * Requests the object construct the container.
     * 
     * @return an instance of the container described by this factory object
     * @throws PSIException if a problem occurs during construction.
     */
    public Container buildContainer() throws PSIException;
}
