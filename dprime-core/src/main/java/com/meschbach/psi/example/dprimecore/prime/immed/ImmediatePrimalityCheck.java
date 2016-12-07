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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/prime/immed/ImmediatePrimalityCheck.java $
 * $Id: ImmediatePrimalityCheck.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.prime.immed;

import com.meschbach.psi.example.dprimecore.prime.PrimalityCheck;
import java.math.BigDecimal;

/**
 * An <code>ImmediatePrimalityCheck</code> is a facade to perform a primality
 * check immediately.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class ImmediatePrimalityCheck {

    ImmediateWorkFactory factory;

    public ImmediatePrimalityCheck() {
        factory = new ImmediateWorkFactory();
    }

    public boolean isPrime(BigDecimal value) {
        PrimalityCheck<ImmediateWorkUnit, PrimeState> pc = new PrimalityCheck<ImmediateWorkUnit, PrimeState>(factory, value);
        pc.setPrimeState(new PrimeState());
        ImmediateWorkUnit iwu;
        do {
            iwu = pc.nextOperation();
            if (!iwu.isDone()) {
                iwu.doWork();
            }
        } while (!iwu.isDone());
        return iwu.isPrime();
    }
}
