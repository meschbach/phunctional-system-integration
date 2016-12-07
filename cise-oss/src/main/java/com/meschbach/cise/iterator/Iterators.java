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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/Iterators.java $
 * $Id: Iterators.java 326 2011-04-01 20:16:12Z meschbach@gmail.com $
 */
package com.meschbach.cise.iterator;

import com.meschbach.cise.Compute;
import com.meschbach.cise.Predicate;
import java.util.Collection;
import java.util.Iterator;

/**
 * The <code>Iterators</code> class is a utility class simplifying the
 * construction of complex iterator chains by creating a set of DSL-like
 * functions.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0
 * @version 1.0.0
 */
public class Iterators {

    public static <E, T extends Exception> ArrayIterator<E, T> fromArray(E... data) {
        return new ArrayIterator<E, T>(data);
    }

    public static <E, T extends Exception> CollectionsMIterator<E, T> fromCollection(Collection<E> c) {
        return new CollectionsMIterator<E, T>(c.iterator());
    }

    public static <E, T extends Exception> SelectiveIterator<E, T> select(Predicate<E, T> p, MIterator<E, T> source) {
        return new SelectiveIterator<E, T>(p, source);
    }

    public static <I, O, T extends Exception> ComputingIterator<I, O, T> computing(Compute<I, O, T> computer, MIterator<I, T> source) {
        return new ComputingIterator<I, O, T>(source, computer);
    }

    public static <E, T extends Exception> OffsetIterator<E, T> offset(MIterator<E, T> source, int count) {
        return new OffsetIterator<E, T>(source, count);
    }

    public static <E, T extends Exception> LimitIterator<E, T> limit(MIterator<E, T> source, int count) {
        return new LimitIterator<E, T>(source, count);
    }

    public static <E, T extends Exception> Iterator<E> toCollections(MIterator<E, T> source) {
        return new MIteratorCollection<E>(source);
    }
}
