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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/resource/zip/ZipEntryIterator.java $
 * $Id: ZipEntryIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.resource.zip;

import com.meschbach.cise.iterator.MIterator;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class ZipEntryIterator implements MIterator<ZipEntry, IOException> {

    ZipInputStream input;
    ZipEntry entry;

    public ZipEntryIterator(ZipInputStream input) {
        this.input = input;
        entry = null;
    }

    public boolean hasNext() throws IOException {
        /*
         * Have we already found the entry?
         */
        if (entry != null) {
            return true;
        }
        /*
         * Nope, grab the next entry
         */
        entry = input.getNextEntry();
        return entry != null;
    }

    public ZipEntry next() throws IOException {
        /*
         * Ensure we have an additional element
         */
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        /*
         *
         */
        ZipEntry result = entry;
        entry = null;
        return result;
    }

    public ZipInputStream getInputStream() {
        return input;
    }

    public void close() {
        try {
            input.close();
        } catch (Throwable t) {
            throw new IllegalStateException("Unable to close source stream", t);
        }
    }
}
