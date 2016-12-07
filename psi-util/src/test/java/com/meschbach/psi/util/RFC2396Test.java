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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/test/java/com/meschbach/psi/util/RFC2396Test.java $
 * $Id: RFC2396Test.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class RFC2396Test {

    @Test
    public void testSpaceEncoding() {
        assertEquals(RFC2396.encode(" "), "%20");
    }

    @DataProvider
    public String[][] buildSimpleEncodingData() {
        return new String[][]{
                    {"http://localhost:50215/salutator/dci/salutator", "http://localhost:50215/salutator/dci/salutator"}
                };
    }

    @Test(dataProvider = "buildSimpleEncodingData")
    public void testSimpleEncoding(String input, String expected) {
        String encoded = RFC2396.encode(input);
        assertEquals(encoded, expected);
    }
}
