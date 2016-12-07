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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/prime/immed/DivideWork.java $
 * $Id: DivideWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.prime.immed;

import com.meschbach.psi.example.dprimecore.prime.CompletionClosure;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DivideWork extends ImmediateWorkUnit {

    CompletionClosure closure;
    BigDecimal divisor;
    BigDecimal dividend;

    public DivideWork(CompletionClosure closure, BigDecimal divisor, BigDecimal dividend) {
        this.closure = closure;
        this.divisor = divisor;
        this.dividend = dividend;
    }

    @Override
    public void doWork() {
        BigDecimal result[] = dividend.divideAndRemainder(divisor, new MathContext(32, RoundingMode.UP));
        closure.completedResult(result[1]);
    }
}
