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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/pkb/PrimeKB.java $
 * $Id: PrimeKB.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.pkb;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * The <code>PrimeKB</code> interface describes a source of knowledge regarding
 * existing prime numbers.
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PrimeKB {

    /**
     * Returns an iterator where the next element is the smallest know prime
     * number, and will traverse the set of known prime numbers in natural order
     * until the largest number is reached.  The retrieved iterator must
     * recognize when a new prime number is added to the end of the known primes
     * and must automagically append the new element on to the end of the
     * iterator.
     *
     * @return an iterator of prime numbers in natural order (smallest to largest)
     */
    public Iterator<BigDecimal> getKnownPrimes();

    /**
     * @return the smallest known prime number
     */
    public BigDecimal getSmallestPrime();

    /**
     * Retrieves the next known prime number.  If we have reached the largest
     * known prime number, then return null.
     * @param last the last prime number used
     * @return the next prime number in the natural order
     */
    public BigDecimal getNextPrime(BigDecimal last);

    /**
     * @return the largest known prime number
     */
    public BigDecimal getLargestPrime();

    /**
     * Notifies this knowledge base to remember a specific prime number
     * @param bd is the prime number
     */
    public void addPrime(BigDecimal bd);

    /**
     * Quries this knowledge base if the given number is prime.
     * @param number the number we are interested in primality
     * @return true if the number is a known prime number
     */
    public boolean isPrime(BigDecimal number);
}
