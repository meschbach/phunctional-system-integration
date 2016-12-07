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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/ipc/DivisionRequest.java $
 * $Id: DivisionRequest.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
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
@XmlRootElement(name = "divide")
public class DivisionRequest implements Respondable<DivisionResponse> {

    BigDecimal divisor;
    BigDecimal dividend;

    public DivisionRequest() {
    }

    public DivisionRequest(BigDecimal divisor, BigDecimal dividend) {
        this.divisor = divisor;
        this.dividend = dividend;
    }

    @XmlElement
    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    @XmlElement
    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setDivisor(BigDecimal divisor) {
        this.divisor = divisor;
    }

    public DivisionResponse buildResponse() {
        BigDecimal result[] = dividend.divideAndRemainder(divisor, new MathContext(64, RoundingMode.UP));
        return new DivisionResponse(result[0], result[1]);
    }
}
