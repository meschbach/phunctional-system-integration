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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/jam/ReplaceEntryVisitor.java $
 * $Id: ReplaceEntryVisitor.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.jam;

import com.meschbach.cise.util.StreamCopier;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class ReplaceEntryVisitor implements EntryVisitor {

    InputStream alternateData;

    public ReplaceEntryVisitor(byte alternateDataBuffer[]) {
        alternateData = new ByteArrayInputStream(alternateDataBuffer);
    }

    public ReplaceEntryVisitor(InputStream alternateData) {
        this.alternateData = alternateData;
    }

    public void visitEntry(String name, ZipEntry e, ZipInputStream input, ZipOutputStream output) throws IOException {
        ZipEntry ze = new ZipEntry(e.getName());
        ze.setComment(e.getComment());
        ze.setTime(System.currentTimeMillis());
        output.putNextEntry(ze);

        StreamCopier sc = new StreamCopier(alternateData, output);
        sc.doCopy();

        output.closeEntry();
    }
}
