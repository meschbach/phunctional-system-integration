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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/test/java/com/meschbach/cise/iterator/ArrayIteratorTests.java $
 * $Id: ArrayIteratorTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class ArrayIteratorTests {

    @Test
    public void emptyArrayProducesNoResults() throws Exception{
        ArrayIterator ai = new ArrayIterator();
        assertFalse(ai.hasNext());
    }

    @Test
    public void testSingleLengthInput() throws Exception {
        ArrayIterator ai = new ArrayIterator(this);
        assertTrue(ai.hasNext());
        assertEquals(ai.next(), this);
        assertFalse(ai.hasNext());
    }

    @Test
    public void testMultipleInputs() throws Exception {
        String[] inputs = new String[]{"a", "b", "c", "d"};
        ArrayIterator<String,Exception> ai = new ArrayIterator<String,Exception>(inputs);
        int count = 0;
        while (ai.hasNext()) {
            assertEquals(ai.next(), inputs[count]);
            count++;
        }
        /*
         * Ensure the array allowed access to all elements
         */
        assertEquals(count, inputs.length);
    }
}
