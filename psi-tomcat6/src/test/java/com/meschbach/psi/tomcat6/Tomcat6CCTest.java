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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-tomcat6/src/test/java/com/meschbach/psi/tomcat6/Tomcat6CCTest.java $
 * $Id: Tomcat6CCTest.java 320 2011-03-12 06:41:14Z marioandrest@gmail.com $
 */
package com.meschbach.psi.tomcat6;

import com.meschbach.psi.Container;
import com.meschbach.psi.PSIException;
import com.meschbach.psi.cct.ContainerCompatabilityFixture;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mario Tinoco" marioandrest@gmail.com
 */
public class Tomcat6CCTest extends ContainerCompatabilityFixture {

    @Override
    public Container buildContainer() throws PSIException {
        return new Tomcat6Builder().buildContainer();
    }
}
