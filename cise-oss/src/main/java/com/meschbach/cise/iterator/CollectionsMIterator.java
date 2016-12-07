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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/CollectionsMIterator.java $
 * $Id: CollectionsMIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.util.Iterator;

/**
 * The <code>CollectionsMIterator</code> is an adapter from the Collection's
 * Iterator interface.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class CollectionsMIterator<E, T extends Exception> implements MIterator<E, T> {

    Iterator<E> source;

    public CollectionsMIterator(Iterator<E> source) {
        this.source = source;
    }

    public boolean hasNext() throws T {
        return source.hasNext();
    }

    public E next() throws T {
        return source.next();
    }

    public void close() {
        source = null;
    }
}
