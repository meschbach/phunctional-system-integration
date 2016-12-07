/*
 *  Copyright 2010-2011 Mark Eschbach
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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/test/java/com/meschbach/cise/jam/ZipManipulatorTests.java $
 * $Id: ZipManipulatorTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.jam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.mockito.BDDMockito.*;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class ZipManipulatorTests {

    @Test
    public void testZipCreation() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /*
         * Create our new ZipManipulator
         */
        ZipManipulator zm = new ZipManipulator();
        zm.createEmptySource();
        zm.setDestination(baos);
        /*
         * Ensure performing an operation will not fail
         */
        zm.performOperations();
        /*
         * Ensure teh output stream is not zero
         */
        assertNotSame(baos.toByteArray().length, 0);
    }

    @Test
    public void testPostProcessorsRun() throws IOException {
        /*
         * Given a stream processor and a configured ZipManipulator
         */
        StreamProcessor sp = mock(StreamProcessor.class);
        ZipManipulator zm = new ZipManipulator();
        zm.createEmptySource();
        zm.setDestination(new ByteArrayOutputStream());

        /*
         * When we perform our operation
         */
        zm.addPostProcessor(sp);
        zm.performOperations();

        /*
         * Ensure the post processor is called
         */
        verify(sp).affectStream(any(ZipOutputStream.class));
    }

    @Test
    public void canAddAnEntryToArchive() throws IOException {
        final String ENTRY_NAME = "test-entry";
        final byte data[] = {0, 1, 2};
        /*
         * Create our manipulator
         */
        ByteArrayOutputStream testBuffer = new ByteArrayOutputStream();
        ZipManipulator zm = new ZipManipulator();
        zm.createEmptySource();
        zm.setDestination(testBuffer);
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        AddEntryStreamProcessor aesp = new AddEntryStreamProcessor(ENTRY_NAME, bais);
        zm.addPostProcessor(aesp);
        zm.performOperations();
        /*
         * Construct our processing entry
         */
        ZipManipulator verifier = new ZipManipulator();
        verifier.setSource(new ByteArrayInputStream(testBuffer.toByteArray()));
        verifier.setDestination(new ByteArrayOutputStream());
        VerifyEntryContents vec = new VerifyEntryContents(data);
        verifier.addEntryVisitor(ENTRY_NAME, vec);
        verifier.performOperations();

        assertTrue(vec.isVerified());
    }

    @Test
    public void testReplaceEntry() throws IOException {
        byte altData[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        //perform the operations
        ZipManipulator zm = new ZipManipulator();
        zm.createEmptySource();
        zm.setDestination(buffer);
        zm.addEntryVisitor("META-INF/created-by", new ReplaceEntryVisitor(altData));
        zm.addPostProcessor(new AddEntryStreamProcessor("test", new ByteArrayInputStream(new byte[]{0, 1, 2, 3})));
        zm.performOperations();
        //ensure our desired entry makes sense
        ZipManipulator checker = new ZipManipulator();
        checker.setSource(new ByteArrayInputStream(buffer.toByteArray()));
        checker.setDestination(new ByteArrayOutputStream());
        checker.addEntryVisitor("META-INF/created-by", new VerifyEntryContents(altData));
        checker.performOperations();
    }
}
