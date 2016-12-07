/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/ipc/MultiplicationResponse.java $
 * $Id: MultiplicationResponse.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.ipc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
@XmlRootElement(name = "multiplication-result")
public class MultiplicationResponse {

    BigDecimal result;

    public MultiplicationResponse(BigDecimal result) {
        this.result = result;
    }

    public MultiplicationResponse() {
    }

    @XmlElement(name = "result")
    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
