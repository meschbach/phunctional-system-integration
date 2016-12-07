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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/distributed/DivisionWork.java $
 * $Id: DivisionWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.distributed;

import com.meschbach.psi.example.dprime.work.AbstractDistributedWork;
import com.meschbach.psi.example.dprimecore.ipc.DivisionRequest;
import com.meschbach.psi.example.dprimecore.ipc.DivisionResponse;
import java.math.BigDecimal;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DivisionWork extends AbstractDistributedWork<DivisionResponse, DivisionRequest, DivisionResponse> {

    BigDecimal dividend, divisior;
    DivisionResponse response;

    public DivisionWork(BigDecimal dividend, BigDecimal divisior) {
        this.dividend = dividend;
        this.divisior = divisior;
        dispatchAvailable();
    }

    public String getDescription() {
        return "Dividing " + dividend + " divided by " + divisior;
    }

    @Override
    protected DivisionResponse calculateResponseFromAnswer(DivisionResponse answer) {
        return answer;
    }

    @Override
    protected DivisionRequest createDistributedWork() {
        return new DivisionRequest(divisior, dividend);
    }
}
