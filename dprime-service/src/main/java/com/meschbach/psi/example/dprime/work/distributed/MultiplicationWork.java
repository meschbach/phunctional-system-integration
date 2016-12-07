/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/distributed/MultiplicationWork.java $
 * $Id: MultiplicationWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.distributed;

import com.meschbach.psi.example.dprime.work.AbstractDistributedWork;
import com.meschbach.psi.example.dprimecore.ipc.MultiplicationRequest;
import com.meschbach.psi.example.dprimecore.ipc.MultiplicationResponse;
import java.math.BigDecimal;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class MultiplicationWork extends AbstractDistributedWork<BigDecimal, MultiplicationRequest, MultiplicationResponse> {

    BigDecimal a, b;
    MultiplicationResponse response;

    public MultiplicationWork(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
        dispatchAvailable();
    }

    public String getDescription() {
        return "Multiply " + a.toPlainString() + " by " + b.toPlainString();
    }

    @Override
    protected BigDecimal calculateResponseFromAnswer(MultiplicationResponse answer) {
        return answer.getResult();
    }

    @Override
    protected MultiplicationRequest createDistributedWork() {
        return new MultiplicationRequest(a, b);
    }
}
