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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/test/java/com/meschbach/psi/examples/dprime/prime/PrimalityCheckTests.java $
 * $Id: PrimalityCheckTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.prime;

import com.meschbach.psi.example.dprimecore.prime.immed.ImmediatePrimalityCheck;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimalityCheckTests {

    @Test
    public void testPrimalityAlgorithm() {
        ImmediatePrimalityCheck ipc = new ImmediatePrimalityCheck();
        assertTrue(ipc.isPrime(BigDecimal.valueOf(2)));
        assertTrue(ipc.isPrime(BigDecimal.valueOf(3)));
        assertFalse(ipc.isPrime(BigDecimal.valueOf(4)));
        assertTrue(ipc.isPrime(BigDecimal.valueOf(29)));
    }
}
