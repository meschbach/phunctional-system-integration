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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/MIteratorTestHarness.java $
 * $Id: MIteratorTestHarness.java 245 2011-03-07 06:09:15Z meschbach@gmail.com $
 */
package com.meschbach.cise.iterator;

/**
 * A <code>IteratorTestHarness</code> is a component to test an iterator to
 * ensure the iterator will provide an expected result.<p>
 *
 * @author Mark Eschbach meschbach@gmail.com;
 * @version 1.0.0
 * @since 1.1.0
 */
public class MIteratorTestHarness<E, T extends Exception> {

    protected MIterator<E, T> testIterator;
    protected MIterator<E, T> expected;

    public MIteratorTestHarness(E... expected) {
        this(new ArrayIterator<E, T>(expected));
    }

    public MIteratorTestHarness(MIterator<E, T> expected) {
        this(null, expected);
    }

    public MIteratorTestHarness(MIterator<E, T> testIterator, MIterator<E, T> expected) {
        this.testIterator = testIterator;
        this.expected = expected;
    }

    public MIteratorTestHarness(MIterator<E, T> testIterator, E... expected) {
        this(testIterator, new ArrayIterator(expected));
    }

    public MIteratorTestHarness() {
    }

    public MIterator<E, T> getExpected() {
        return expected;
    }

    public void setExpected(MIterator<E, T> expected) {
        this.expected = expected;
    }

    public MIterator<E, T> getTestIterator() {
        return testIterator;
    }

    public void setTestIterator(MIterator<E, T> testIterator) {
        this.testIterator = testIterator;
    }

    public void test() throws T {
        assert testIterator != null : "Test iterator is null";
        assert expected != null : "Expectation iterator is null";
        int elementIndex = 0;
        while (testIterator.hasNext() && expected.hasNext()) {
            E aTestT = testIterator.next();
            E anExpectedT = expected.next();
            if (anExpectedT == null) {
                /*
                 * Ensure that our expected element is null, then our test
                 * element is also null
                 */
                assert aTestT == null : "Expected iterator element @ " + elementIndex + ": '" + anExpectedT + "'; got '" + aTestT + "'";
            } else {
                assert anExpectedT.equals(aTestT) : "Expected iterator element @ " + elementIndex + ": '" + anExpectedT + "'; got '" + aTestT + "'";
            }
            elementIndex++;
        }
        if (expected.hasNext() && !testIterator.hasNext()) {
            assert false : "Premature end of test iterator at element " + elementIndex + "; expected '" + expected.next() + "'";
        } else if (!expected.hasNext() && testIterator.hasNext()) {
            assert false : "Tested iterator has too many elements @ " + elementIndex;
        } else {
            assert !testIterator.hasNext() : "Test iterator is not complete @ element " + elementIndex;
            assert !expected.hasNext() : "Expected iterator is not complete @ element " + elementIndex;
        }
    }
}
