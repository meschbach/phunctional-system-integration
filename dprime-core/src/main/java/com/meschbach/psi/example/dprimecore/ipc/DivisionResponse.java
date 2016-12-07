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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/ipc/DivisionResponse.java $
 * $Id: DivisionResponse.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.ipc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
@XmlRootElement(name = "division-result")
public class DivisionResponse {

    BigDecimal quotient, remainder;

    public DivisionResponse() {
    }

    public DivisionResponse(BigDecimal quotient, BigDecimal remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    @XmlElement(name = "quotient", required = true)
    public BigDecimal getQuotient() {
        return quotient;
    }

    public void setQuotient(BigDecimal quotient) {
        this.quotient = quotient;
    }

    @XmlElement(name = "remainder", required = true)
    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }
}
