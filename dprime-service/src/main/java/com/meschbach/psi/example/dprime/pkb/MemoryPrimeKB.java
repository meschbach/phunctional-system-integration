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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/pkb/MemoryPrimeKB.java $
 * $Id: MemoryPrimeKB.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.pkb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * IMPORTANT: Many tests depend on fact a new instance of this class doesn't
 * know that 7 is prime.  Fragile yes, however this makes testing other algorithms
 * _very_ easy and consumes far fewer resources.
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @version 1.0.0
 */
public class MemoryPrimeKB implements PrimeKB {

    SortedSet<BigDecimal> knownPrimes;

    public MemoryPrimeKB() {
        knownPrimes = new TreeSet<BigDecimal>();
        knownPrimes.add(BigDecimal.valueOf(2));
        knownPrimes.add(BigDecimal.valueOf(3));
    }

    public synchronized void addPrime(BigDecimal bd) {
        knownPrimes.add(bd);
    }

    public synchronized Iterator<BigDecimal> getKnownPrimes() {
        return new ArrayList<BigDecimal>(knownPrimes).iterator();
    }

    public synchronized BigDecimal getLargestPrime() {
        return knownPrimes.last();
    }

    public synchronized BigDecimal getNextPrime(BigDecimal last) {
        SortedSet<BigDecimal> sub = knownPrimes.subSet(last.add(BigDecimal.ONE), getLargestPrime().add(BigDecimal.ONE));
        BigDecimal result;
        if (sub.size() < 1) {
            result = null;
        } else {
            result = sub.first();
        }
        return result;
    }

    public synchronized BigDecimal getSmallestPrime() {
        return knownPrimes.first();
    }

    public boolean isPrime(BigDecimal number) {
        return knownPrimes.contains(number);
    }
}
