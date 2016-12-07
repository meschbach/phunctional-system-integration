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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/Compute.java $
 * $Id: Compute.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise;

/**
 * A <code>Compute</code> role describes the ability to compute a result given
 * a specified input.  If the given does not fit within the constraints placed
 * upon the implementations pattern then an exceptional condition may be
 * raised.<p>
 *
 * This is an attempt to be super generic to reduce the errors made in complex
 * algorithms, such as iterator processing.  By pulling out an encapsulating the
 * converting algorithm we allow that algorithm to be tested and reused.<p>
 *
 * The name has been changed from when it was apart of my (Mark Eschbach's)
 * internal library.  This interface used to be named <code>Convertable</code>,
 * however the algorithm is a little more generic than that.<p>
 *
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.0.0
 * @version 1.0.0
 */
public interface Compute<I, O, T extends Exception> {

    /**
     * Given the algorithm implemented, convert the input to an output.  If the
     * value can not be converted to a valid output then throw an exception of
     * the specified type.
     * 
     * @param input is the input to attempt to convert from
     * @return the resulting value of the converted input
     * @throws T if the given input can not be converted to the specified output
     */
    public O compute(I input) throws T;
}
