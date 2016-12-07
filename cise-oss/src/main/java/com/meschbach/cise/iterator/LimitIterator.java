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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/LimitIterator.java $
 * $Id: LimitIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.util.NoSuchElementException;

/**
 * The <code>LimitIterator</code> allows for up to <code>count</code> number of 
 * elements to be retrieved from the source iterator before claiming there are
 * no more elements.
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 * @since 1.2.0
 * @version 1.0.0
 */
public class LimitIterator<E, T extends Exception> implements MIterator<E, T> {

    /**
     * The <code>source</code> is the iterator to draw from.
     */
    protected MIterator<E, T> source;
    /**
     * The <code>count</code> is the number of elements already retrieved.
     */
    protected int count;
    /**
     * The <code>limit</code> is the maximum number of elements which may be
     * retrieved.
     */
    protected int limit;

    /**
     * Constructs a new  <code>LimitIterator</code> with up to
     * <code>limit</code> accessible.
     * 
     * @param source is the iterator to draw elements from
     * @param limit the maximum number of elements allow to be drawn
     */
    public LimitIterator(MIterator<E, T> source, int limit) {
        this.source = source;
        this.limit = limit;
        count = 0;
    }

    /**
     * @see  MIterator#hasNext() 
     */
    public boolean hasNext() throws T {
        return source.hasNext() && count < limit;
    }

    /**
     * @see  MIterator#next()
     */
    public E next() throws T {
        if (!source.hasNext()) {
            throw new NoSuchElementException();
        }
        count++;
        return source.next();
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
