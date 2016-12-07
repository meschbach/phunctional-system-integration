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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/test/java/com/meschbach/psi/examples/dprime/ipc/DivisionResponseTests.java $
 * $Id: DivisionResponseTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.ipc;

import com.meschbach.psi.example.dprimecore.ipc.DivisionResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import javax.xml.bind.JAXB;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DivisionResponseTests {

    @Test
    public void canMarshallUnMarshallWithJAXB() {
        final BigDecimal quotient = BigDecimal.valueOf(125);
        final BigDecimal remainder = BigDecimal.valueOf(36);

        DivisionResponse dr = new DivisionResponse();
        dr.setQuotient(quotient);
        dr.setRemainder(remainder);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JAXB.marshal(dr, baos);
        DivisionResponse result = JAXB.unmarshal(new ByteArrayInputStream(baos.toByteArray()), DivisionResponse.class);
        assertEquals(result.getQuotient(), quotient);
        assertEquals(result.getRemainder(), remainder);
    }
}
