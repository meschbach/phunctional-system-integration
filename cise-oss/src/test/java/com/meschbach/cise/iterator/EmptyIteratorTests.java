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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/test/java/com/meschbach/cise/iterator/EmptyIteratorTests.java $
 * $Id: EmptyIteratorTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.0.0
 * @version 1.1.0
 */
public class EmptyIteratorTests {

    EmptyIterator ei;

    @BeforeMethod
    public void build() {
        ei = new EmptyIterator();
    }

    @Test
    public void testDoesntHaveNext() throws Exception {
        assertFalse(ei.hasNext());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testThrowsNSEEWhenNext() throws Exception {
        ei.next();
    }
}
