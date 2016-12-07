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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/SelectiveIterator.java $
 * $Id: SelectiveIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import com.meschbach.cise.Predicate;
import java.util.NoSuchElementException;

/**
 * A <code>SelectiveIterator</code> is an iterator which will only produces
 * results when a given decision predicate returns true.  All other results from
 * the source iterator a discarded.<p>
 * 
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 *
 * @author Mark Eschbach <meschbach@gmail.com>
 * @version 2.0.0
 * @since 1.2.0
 */
public class SelectiveIterator<E, T extends Exception> implements MIterator<E, T> {

    /**
     * The <code>decisionMaker</code> is an predicate to decide if a
     *  given value should be returned by this iterator.
     */
    private Predicate<E, T> decisionMaker;
    /**
     * The <code>source</code> is a rejectable iterator wrapped around a given
     * input source iterator.  The iterator will be queried until the
     * <code>decisionMaker</code> returns true on an element.  If we are
     * querying if we have more elements then we will reject our accepted value
     * to be retrieved again.
     */
    private RejectableIterator<E, T> source;

    /**
     * Constructs a new SelectiveIterator with the predicate an source for
     * the elements invovled.
     *
     * @param decisionMaker is the predicate to accept or reject values
     * @param source is the source of the elements to process
     */
    public SelectiveIterator(Predicate<E, T> decisionMaker, MIterator<E, T> source) {
        this.decisionMaker = decisionMaker;
        this.source = new RejectableIterator<E, T>(source);
    }

    /***************************************************************************
     * MIterator<E,T> interface implementation
     **************************************************************************/
    /**
     * @see  MIterator#hasNext()
     */
    public boolean hasNext() throws T {
        /*
         * Set the initial state of our findings.
         */
        boolean result = false;
        /*
         * While our source iterato has more elements and we have not found a
         * valid result
         */
        while (source.hasNext() && !result) {
            /*
             * Then check with our predicate if the next element is valid
             */
            E next = source.next();
            if (decisionMaker.evaluateTruth(next)) {
                /*
                 * If the next element is valid, set to the top of our internal
                 * source iterator and flag that we have found our result
                 */
                source.reject(next);
                result = true;
            }
        }
        /*
         * Return our evaluated results
         */
        return result;
    }

    /**
     * @see  MIterator#next()
     */
    public E next() throws T {
        /*
         * Ensure that we do have a next element; a side effect of which is
         * that we will find our next element and it will be placed on the top
         * of our rejecting iterator.
         *
         * NOTE: Performance is only minorly impacted by doing the next
         * evalutation in this manner and it greatly simplifies the code as
         * the next best alternative I figured was a state based approach.
         */
        if (!hasNext()) {
            /*
             * Nope, full-fill our Iterator<E> contract.
             */
            throw new NoSuchElementException();
        } else {
            /*
             * Yes, give it up
             */
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
        decisionMaker = null;
    }
}
