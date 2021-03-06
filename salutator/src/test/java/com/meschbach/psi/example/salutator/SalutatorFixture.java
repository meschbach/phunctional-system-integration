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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/salutator/src/test/java/com/meschbach/psi/example/salutator/SalutatorFixture.java $
 * $Id: SalutatorFixture.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.salutator;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class SalutatorFixture<T extends Salutator> {

    protected T salutator;

    @Test
    public void testGreetingState() {
        final String NAME = "This is a name";
        final String GREETING = "Hello!";

        salutator.setGreetingFor(NAME, GREETING);
        assertTrue(salutator.hasGreetingFor(NAME));
        assertEquals(salutator.getGreetingFor(NAME), GREETING);
    }
}
