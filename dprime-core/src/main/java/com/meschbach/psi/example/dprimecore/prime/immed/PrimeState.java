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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/prime/immed/PrimeState.java $
 * $Id: PrimeState.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.prime.immed;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimeState {

    SortedSet<BigDecimal> primeKnowledgeBase;
    BigDecimal last;

    public PrimeState() {
        primeKnowledgeBase = new TreeSet<BigDecimal>();
        primeKnowledgeBase.add(BigDecimal.valueOf(2));
    }

    public BigDecimal getNextPrime() {
        BigDecimal next;
        if (last == null) {
            next = primeKnowledgeBase.first();
        } else {
            BigDecimal base = last.add(BigDecimal.ONE);
            if (base.compareTo(primeKnowledgeBase.last()) >= 0) {
                next = findNextPrime();
            } else {
                SortedSet<BigDecimal> sub = primeKnowledgeBase.subSet(last.add(BigDecimal.ONE), primeKnowledgeBase.last());
                next = sub.first();
            }
        }
        last = next;
        return next;
    }

    protected BigDecimal findNextPrime() {
        ImmediatePrimalityCheck ipc = new ImmediatePrimalityCheck();
        BigDecimal v = BigDecimal.ONE.add(last);
        while (!ipc.isPrime(v)) {
            v = BigDecimal.ONE.add(v);
        }
        primeKnowledgeBase.add(v);
        return v;
    }
}
