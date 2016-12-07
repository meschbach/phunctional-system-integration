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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/ArrayIterator.java $
 * $Id: ArrayIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

/**
 * An <code>ArrayIterator</code> is a generic iterator to allow for the 
 * iteration  * of over an array without having to create a list.  This iterator
 * is not thread-safe, however the memory overhead is minimal.<p>
 * 
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.1.0
 * @version 2.0.0
 */
public class ArrayIterator<E, T extends Exception> implements MIterator<E, T> {

    /**
     * The <code>values</code> array contains the data elements to be returned
     * when queried.
     */
    private E values[];
    /**
     * The <code>index</code> contains the next value to be returned upon
     * request.  This may contain greater than the length of the array upon the
     * condition the iteration has completed.  The index value may never be less
     * than zero.
     */
    private int index;

    /**
     * Constructs a new ArrayIterator to iterator over the entire array.  This
     * will iterator over all values within the array.
     *
     * @param values is a reference to an array containing the list of values to iterator over
     */
    public ArrayIterator(E... values) {
        this.values = values;
        index = 0;
    }

    public ArrayIterator(int base, E... values) {
        this.values = values;
        this.index = base;
    }

    public boolean hasNext() throws T {
        return index < values.length;
    }

    public E next() throws T {
        E result = values[index];
        index++;
        return result;
    }

    public void close() {
        values = null;
    }
}
