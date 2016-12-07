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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/test/java/com/meschbach/psi/examples/dprime/pkb/PrimeKBFixture.java $
 * $Id: PrimeKBFixture.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.pkb;

import com.meschbach.psi.example.dprime.pkb.PrimeKB;
import java.math.BigDecimal;
import java.util.Iterator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * This class encapsulates the tests to ensure Liskov's substitution principle
 * stands for implementations of PrimeKB implementations.
 *
 * To test against this class: extends the class, then create a method with the
 * @BeforeMethod annotation to initialize the field <code>pkb</code>.
 */
public abstract class PrimeKBFixture<P extends PrimeKB> {

    protected P pkb;

    @BeforeMethod
    public void setupLSP() {
        pkb = buildPKB();
        if(pkb == null ){
            throw new IllegalStateException("Class level variable pkb was not initialized");
        }
    }

    @AfterMethod
    public void cleanUpLSP() {
        cleanUp(pkb);
    }

    public abstract P buildPKB();

    public void cleanUp(P pkb) {
    }

    @Test
    public void allKnownPrimesArePrime() {
        Iterator<BigDecimal> dit = pkb.getKnownPrimes();
        while (dit.hasNext()) {
            BigDecimal d = dit.next();
            assert pkb.isPrime(d) : "Prime number is not prime";
        }
    }

    @Test
    public void addPrimeIsPrime() {
        final BigDecimal tv = BigDecimal.ONE;
        pkb.addPrime(tv);
        assertTrue(pkb.isPrime(tv), "Added prime number is not prime");
    }

    @Test
    public void canRetrieve1stAndSecondPrime() {
        BigDecimal smallest = pkb.getSmallestPrime();
        assertEquals(smallest, BigDecimal.valueOf(2));
        assertEquals(pkb.getNextPrime(smallest), BigDecimal.valueOf(3));
    }
}
