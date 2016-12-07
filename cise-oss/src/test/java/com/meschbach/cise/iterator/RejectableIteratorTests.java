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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/test/java/com/meschbach/cise/iterator/RejectableIteratorTests.java $
 * $Id: RejectableIteratorTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 */
public class RejectableIteratorTests {

    @Test
    public void canPullThroughSource() throws Exception {
        Object[] data = {"A", BigDecimal.ONE, BigInteger.ZERO};
        MIterator mit = new RejectableIterator(new ArrayIterator(data));
        MIteratorTestHarness mith = new MIteratorTestHarness(mit, data);
        mith.test();
    }

    @Test
    public void emptyWithEmptySource() throws Exception {
        RejectableIterator ri = new RejectableIterator(new EmptyIterator());
        assertFalse(ri.hasNext());
    }

    @Test
    public void withRejectHasMoreResults() throws Exception {
        RejectableIterator ri = new RejectableIterator(new EmptyIterator());
        ri.reject("reject");
        assertTrue(ri.hasNext());
    }

    @Test
    public void ableToRejectNull() throws Exception {
        RejectableIterator ri = new RejectableIterator(new EmptyIterator());
        ri.reject(null);
        assertTrue(ri.hasNext());
    }
}
