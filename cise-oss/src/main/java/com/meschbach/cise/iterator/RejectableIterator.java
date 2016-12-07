/*
 *  Copyright 2009-2011 Mark Eschbach
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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/RejectableIterator.java $
 * $Id: RejectableIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import com.meschbach.cise.Closable;

/**
 * A <code>RejectableIterator</code> is an iterator which allows a client to
 * insert a value as the next value to be retrieved from the iterator.  This
 * is useful for a single look ahead at the next element of the source iterator
 * and is used by algorithms such as the <code>SelectiveIterator</code>.<p>
 *
 * This class has been designed to work with null values for the reject element.<p>
 * This class has <strong>not</strong> been designed to deal with
 * multi-threading.<p>
 *
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 *
 * @author Mark Eschbach (meschbach@gmail.com)
 * @since 1.0.0
 * @version 2.0.0
 */
public class RejectableIterator<E, T extends Exception> implements MIterator<E, T> {

    /**
     * The <code>hasRejected</code> indicates when true the value of the
     * rejected field be used as opposed to the a value from the source.
     */
    private boolean hasRejected;
    /**
     * The <code>rejected</code> is the value to be retrieved when the
     *  <code>hasRejected</code>  flag indicates true.
     */
    private E rejected;
    /**
     * The <code>source</code> is an iterator to draw values from.
     */
    private MIterator<E, T> source;

    /**
     * Constructs a new <code>RejectableIterator</code> with the specified
     * source iterator.
     * 
     * @param source is the source to draw data from
     */
    public RejectableIterator(MIterator<E, T> source) {
        this.source = source;
        hasRejected = false;
    }

    /**
     * @see  MIterator#hasNext()
     */
    public boolean hasNext() throws T {
        return hasRejected || source.hasNext();
    }

    /**
     * @see  MIterator#next() 
     */
    public E next() throws T {
        E result;
        if (hasRejected) {
            hasRejected = false;
            result = rejected;
            rejected = null;
        } else {
            result = source.next();
        }
        return result;
    }

    /**
     * Places the given object as the next element to be retrieved form the
     * iterator.
     * 
     * @param value is the value to be utilized next
     */
    public void reject(E value) {
        rejected = value;
        hasRejected = true;
    }

    /**
     * @see  Closable#close()
     */
    public void close() {
        if (source != null) {
            source.close();
        }

        rejected = null;
        source = null;
        hasRejected = false;
    }
}
