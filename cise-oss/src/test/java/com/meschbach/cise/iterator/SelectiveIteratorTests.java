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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/test/java/com/meschbach/cise/iterator/SelectiveIteratorTests.java $
 * $Id: SelectiveIteratorTests.java 329 2011-04-10 06:37:29Z meschbach@gmail.com $
 */
package com.meschbach.cise.iterator;

import static com.meschbach.cise.predicates.Predicates.*;
import static com.meschbach.cise.iterator.Iterators.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 */
public class SelectiveIteratorTests {

    @Test
    public void ableToPullFullSource() throws Throwable {
        Object sourceData[] = {"A", null, BigDecimal.ZERO, BigInteger.TEN};
        SelectiveIterator si = select(always(true), fromArray(sourceData));
        MIteratorTestHarness mith = new MIteratorTestHarness(si, sourceData);
        mith.test();
    }

    @Test
    public void alwaysFalseHasNoElements() throws Throwable {
        Object sourceData[] = {"A", null, BigDecimal.ZERO, BigInteger.TEN};
        SelectiveIterator si = select(always(false), fromArray(sourceData));
        assertFalse(si.hasNext());
    }
}
