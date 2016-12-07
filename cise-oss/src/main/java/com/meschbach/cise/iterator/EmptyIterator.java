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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/EmptyIterator.java $
 * $Id: EmptyIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.util.NoSuchElementException;

/**
 * Am <ocde>EmptyIterator</code> is an iterator which never has more elements.
 * This iterator is usually useful for testing components operating against
 * iterators to ensure the components work when their source iterator doesn't
 * have any more elements.<p>
 *
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 2.0.0
 * @since 1.1.0
 */
public class EmptyIterator<E, T extends Exception> implements MIterator<E, T> {

    public boolean hasNext() throws T {
        return false;
    }

    public E next() throws T {
        throw new NoSuchElementException("Always empty.");
    }

    public void close() {
    }
}