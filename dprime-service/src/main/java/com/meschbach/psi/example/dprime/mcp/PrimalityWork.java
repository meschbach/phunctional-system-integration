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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/mcp/PrimalityWork.java $
 * $Id: PrimalityWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.mcp;

import com.meschbach.psi.example.dprime.pkb.PrimeKB;
import com.meschbach.psi.example.dprime.pkb.PrimerReactor;
import com.meschbach.psi.example.dprime.work.AbstractWork;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.InternalWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprimecore.prime.PrimalityCheck;
import com.meschbach.psi.example.dprimecore.prime.StateChangeDelegate;
import java.math.BigDecimal;

/**
 * A <code>PrimalityWork</code> represents an internal unit of work for
 * ascertaining if a given number is prime.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class PrimalityWork extends AbstractWork<Boolean> implements InternalWork<Boolean> {

    PrimeKB pkb;
    PrimerReactor reactor;
    WorkQueue<DistributedWork> distributedWorkQueue;
    BigDecimal maybePrime;
    WorkAction result;

    public PrimalityWork(PrimeKB pkb, PrimerReactor reactor, WorkQueue<DistributedWork> distributedWorkQueue, BigDecimal maybePrime) {
        this.pkb = pkb;
        this.reactor = reactor;
        this.distributedWorkQueue = distributedWorkQueue;
        this.maybePrime = maybePrime;
    }

    public void start() {
        DistributedWorkFactory dwf = new DistributedWorkFactory();
        PrimalityCheck<WorkAction, PrimeCrawler> pc = new PrimalityCheck<WorkAction, PrimeCrawler>(dwf, maybePrime);
        pc.setPrimeState(new PrimeCrawler(maybePrime, pkb, reactor));
        pc.setDelegate(new StateChangeDelegate<WorkAction, PrimeCrawler>() {

            public void changedState(PrimalityCheck<WorkAction, PrimeCrawler> checker) {
                WorkAction wa = checker.nextOperation();
                if (wa.isComplete()) {
                    result = wa;
                    dispatchCompleted();
                } else {
                    wa.doWork(distributedWorkQueue);
                }
            }
        });
        dispatchCalculating();
        pc.start();
    }

    public String getDescription() {
        return "Checking the primality of " + maybePrime.toPlainString();
    }

    public Boolean getResult() {
        return result.isPrime();
    }

    public BigDecimal getMaybePrime() {
        return maybePrime;
    }
}
