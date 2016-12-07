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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/MIterator.java $
 * $Id: MIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

import com.meschbach.cise.Closable;
import java.util.NoSuchElementException;

/**
 * An <code>MIterator</code> encapsulates the principals of an iterator
 * while allowing for reasonable resource management and not enforcing the need
 * to have a remove method.<p>
 *
 * The MIterator allows for exceptional conditions to be safely rising from
 * within an iterator, allowing for complex processing to occur within an
 * iterator, such a <code>Computable</code> or querying from a database.<p>
 *
 * The remove()V operation was chosen not to be included because in many
 * operations removal would not make any sense.<p>
 *
 * The close()V operation was included to release underlying resources
 * associated with the iterator, or chain of iterators<p>
 *
 * This interface may seem to be in direct competition with the Java
 * Collection's Iterator and in some instances are, however the Collection's
 * Iterator was only designed to iterate elements from a collection.  The
 * MIterator was designed to allow for complex computations to occurs
 * within the iterator chain.<p>
 *
 * This interface is under an Apache License, Version 2.0; copyright by Mark Eschbach.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com;
 * @version 1.0.0
 * @since 1.0.0
 */
public interface MIterator<E, T extends Exception> extends Closable {

    /**
     * Queries the iterator to see if the iterator contains an additional
     * element.  If this iterator is composed of one or more source iterators
     * this may result in those iterators being queried too.
     * 
     * @return true if another element exists, otherwise false
     * @throws T if a problem occurs while finding an additional element
     */
    public boolean hasNext() throws T;

    /**
     * Retrieves the next element from the iterator.
     * 
     * @return the next element
     * @throws T if a problem occurs while retrieving the next element
     * @throws NoSuchElementException if no additional elements exist
     */
    public E next() throws T;
}
