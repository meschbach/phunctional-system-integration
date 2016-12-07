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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/OffsetIterator.java $
 * $Id: OffsetIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.util.NoSuchElementException;

/**
 * A <code>OffsetIterator</code> skips up to a specified number of elements
 * prior to returning an element.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0
 * @version  1.0.0
 */
public class OffsetIterator<E, T extends Exception> implements MIterator<E, T> {

    /**
     * The <code>source</code> is the iterator to draw elements from
     */
    protected MIterator<E, T> source;
    /**
     * The <code>skipTo</code> is the count of the number of elements to be
     * skipped prior to returning another element.
     */
    protected int skipTo;
    /**
     * The <code>skipped</code> accumulator contains the number of elements we
     * have skipped.
     */
    protected int skipped;

    /**
     * Constructs a new <code>OffsetIterator</code> skipping <code>skipTo</code>
     * elements.
     * 
     * @param source is the source to draw from
     * @param skipTo is the number of elements to skip
     */
    public OffsetIterator(MIterator<E, T> source, int skipTo) {
        this.source = source;
        this.skipTo = skipTo;
        this.skipped = 0;
    }

    /**
     * @see  MIterator#hasNext()
     */
    public boolean hasNext() throws T {
        while (source.hasNext() && skipped < skipTo) {
            source.next();
            skipped++;
        }
        return source.hasNext();
    }

    /**
     * @see  MIterator#next()
     */
    public E next() throws T {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return source.next();
        }
    }

    /**
     * @see  MIterator#close()
     */
    public void close() {
        if (source != null) {
            source.close();
            source = null;
        }
    }
}
