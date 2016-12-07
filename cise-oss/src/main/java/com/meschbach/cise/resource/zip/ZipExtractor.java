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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/resource/zip/ZipExtractor.java $
 * $Id: ZipExtractor.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.resource.zip;

import com.meschbach.cise.util.StreamCopier;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class ZipExtractor {

    ZipEntryIterator zei;
    File base;

    public ZipExtractor(InputStream in, File aBase) {
        zei = new ZipEntryIterator(new ZipInputStream(in));
        base = aBase;
    }

    public void extractAll() throws IOException {
        while (zei.hasNext()) {
            ZipEntry ze = zei.next();
            File target = new File(base, ze.getName());
            if (ze.isDirectory()) {
                if (!target.exists() && !target.mkdirs()) {
                    throw new IOException("Unable to create directory " + target.getAbsolutePath());
                }
            } else {
                FileOutputStream fos = new FileOutputStream(target);
                StreamCopier.copy(zei.getInputStream(), fos);
            }
        }
    }

    public static void extractTo(InputStream source, File base) throws IOException {
        new ZipExtractor(source, base).extractAll();
    }
}
