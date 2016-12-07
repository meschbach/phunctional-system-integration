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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/jam/VerifyEntryContents.java $
 * $Id: VerifyEntryContents.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.jam;

import com.meschbach.cise.util.StreamCopier;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class VerifyEntryContents implements EntryVisitor {

    byte expectedData[];
    boolean verified;

    public VerifyEntryContents(final byte[] expectedData) {
        final int count = expectedData.length;
        this.expectedData = new byte[count];
        System.arraycopy(expectedData, 0, this.expectedData, 0, count);
        
        verified = false;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void visitEntry(String name, ZipEntry e, ZipInputStream input, ZipOutputStream output) throws IOException {
        /*
         * Note we have verified our entry
         */
        verified = true;
        /*
         * Read all data into a buffer
         */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StreamCopier sc = new StreamCopier(input, baos);
        sc.doCopy();
        /*
         * Verify the data is the same
         */
        byte data[] = baos.toByteArray();
        assert expectedData.length == data.length : "Expected data length of " + expectedData.length + "; got " + data.length;
        for (int i = 0; i < expectedData.length; i++) {
            assert expectedData[i] == data[i];
        }
    }
}
