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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/Predicate.java $
 * $Id: Predicate.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise;

/**
 * A <code>Predicate</code> is an algorithm to determine the truth value of
 * a given input.<p>
 *
 * This class is intentionally vague; defining very few pre and post conditions.
 * The idea behind this class it formalize a series of predicates and reduce
 * the creation of subclasses merely because of a different predicate.<p>
 *
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @version 2.0.0
 * @since 1.0.0
 */
public interface Predicate<E, T extends Exception> {

    /**
     * Evaluates the given input in an algorithm specific manner to determine
     * if the given value is true or false.
     * 
     * @param anElement is the element to determine the truth value for
     * @return true or false, depending on the algorithm
     * @throws T if an unexpected condition occurs
     */
    public boolean evaluateTruth(E anElement) throws T;
}
