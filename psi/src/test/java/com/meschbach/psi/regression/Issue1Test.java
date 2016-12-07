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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi/src/test/java/com/meschbach/psi/regression/Issue1Test.java $
 * $Id: Issue1Test.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.regression;

import com.meschbach.psi.util.RFC2396;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
/*
 * This is a set of regression tests to enure that issue 1 is not encountered
 * again.
 * 
 * <a
 * href='http://code.google.com/p/phunctional-system-integration/issues/detail?id=1'
 * >
 * http://code.google.com/p/phunctional-system-integration/issues/detail?id=1
 * </a>
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mark Eschbach" marioandrest@gmail.com
 */

public class Issue1Test {

    @Test
    public void testSpaceInGetURL() throws Throwable {
        assertEquals(RFC2396.encode("test name"), "test%20name");
    }
}
