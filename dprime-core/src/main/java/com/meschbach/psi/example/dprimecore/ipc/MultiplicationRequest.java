/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/ipc/MultiplicationRequest.java $
 * $Id: MultiplicationRequest.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.ipc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
@XmlRootElement(name = "mutliply")
public class MultiplicationRequest implements Respondable<MultiplicationResponse>{

    BigDecimal a, b;

    public MultiplicationRequest() {
    }

    public MultiplicationRequest(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    @XmlElement(name = "a")
    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    @XmlElement(name = "b")
    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    public MultiplicationResponse buildResponse() {
        BigDecimal result = a.multiply(b, new MathContext(64, RoundingMode.UP));
        return new MultiplicationResponse(result);
    }


}
