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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/util/FileLength.java $
 * $Id: FileLength.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class FileLength {

    File file;

    public FileLength(File file) {
        this.file = file;
    }

    public long findLength() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bif = new BufferedInputStream(fis);
        try {
            byte buffer[] = new byte[1024];
            long size = 0;

            boolean keepReading = true;
            while (keepReading) {
                long read = bif.read(buffer);
                if (read == -1) {
                    keepReading = false;
                } else {
                    size += read;
                }
            }
            return size;
        } finally {
            bif.close();
        }
    }

    public static long size(File file) throws IOException {
        return new FileLength(file).findLength();
    }
}