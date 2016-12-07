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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi/src/test/java/com/meschbach/psi/regression/Issue4Test.java $
 * $Id: Issue4Test.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.regression;

import com.meschbach.psi.jetty6.LocalJetty6Builder;
import com.meschbach.psi.util.WebAppHarness;
import org.testng.annotations.Test;

/**
 * <a href='http://code.google.com/p/phunctional-system-integration/issues/detail?id=4'>
 * http://code.google.com/p/phunctional-system-integration/issues/detail?id=4
 * </a>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @author "Mark Eschbach" marioandrest@gmail.com
 */
public class Issue4Test {

    @Test
    public void testWebAppHarnessShutdownNoNPE() throws Throwable {
        WebAppHarness wah = new WebAppHarness(new LocalJetty6Builder(),
                "bad-artfiact", "/bad-artfiact");
        wah.shutdown();
    }
}
