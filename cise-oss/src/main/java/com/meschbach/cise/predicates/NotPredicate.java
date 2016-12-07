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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/predicates/NotPredicate.java $
 * $Id: NotPredicate.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.predicates;

import com.meschbach.cise.Predicate;

/**
 * A <code>NotPredicate</code> inverts the response of an evaluation from a
 * source predicate.<p>
 *
 * Copyright 2010-2011 by Mark Eschbach, licensed under the Apache License, Version 2.0.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class NotPredicate<E, T extends Exception> implements Predicate<E, T> {

    Predicate<E, T> source;

    public NotPredicate(Predicate<E, T> source) {
        this.source = source;
    }

    public boolean evaluateTruth(E anElement) throws T {
        return !source.evaluateTruth(anElement);
    }
}
