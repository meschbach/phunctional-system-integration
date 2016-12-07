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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/mcp/DistributedWorkFactory.java $
 * $Id: DistributedWorkFactory.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.mcp;

import com.meschbach.psi.example.dprime.work.event.WorkListenerAdapter;
import com.meschbach.psi.example.dprime.work.distributed.DivisionWork;
import com.meschbach.psi.example.dprime.work.distributed.MultiplicationWork;
import com.meschbach.psi.example.dprimecore.prime.CompletionClosure;
import com.meschbach.psi.example.dprimecore.prime.WorkFactory;
import java.math.BigDecimal;

/**
 * A <code>DistributedWorkFactory</code> provides a bridge between the
 * <code>WorkFactory</code> hierarchy used by the meta-algorithm for calculating
 * prime numbers and the distributed work queues within the MCP which delegate
 * work to the actual work nodes.
 * <p>
 * A <code>DistributedWorkFactory</code> builds a series of
 * <code>WorkAction</code> describing the work to be completed.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @version 1.0.0
 */
public class DistributedWorkFactory implements WorkFactory<WorkAction, PrimeCrawler> {

    public WorkAction divide(BigDecimal divisor, BigDecimal dividend, final CompletionClosure closure) {
        DivisionWork dw = new DivisionWork(dividend, divisor);
        dw.addStateListener(new WorkListenerAdapter<DivisionWork>() {

            @Override
            public void workCompleted(DivisionWork work) {
                closure.completedResult(work.getResult().getRemainder());
            }
        });
        return new WorkAction(dw);
    }

    public WorkAction doNothing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public WorkAction nextPrime(PrimeCrawler state, CompletionClosure closure) {
        state.setResult(closure);
        return state;
    }

    public WorkAction result(boolean isPrime) {
        return new WorkAction(isPrime);
    }

    public WorkAction square(BigDecimal value, final CompletionClosure closure) {
        MultiplicationWork mw = new MultiplicationWork(value, value);
        mw.addStateListener(new WorkListenerAdapter<MultiplicationWork>() {

            @Override
            public void workCompleted(MultiplicationWork work) {
                closure.completedResult(work.getResult());
            }
        });
        return new WorkAction(mw);
    }
}
