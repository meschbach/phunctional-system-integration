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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/util/JarEntryReplacement.java $
 * $Id: JarEntryReplacement.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class JarEntryReplacement {

    File jar;
    File target;
    String name;
    InputStream source;
    long size;

    public JarEntryReplacement(File jar, File target, String name, InputStream source, long size) {
        this.jar = jar;
        this.target = target;
        this.name = name;
        this.source = source;
        this.size = size;
    }

    public boolean run() throws IOException {
        FileInputStream jarIn = null;
        FileOutputStream jarOut = null;
        boolean wasReplaced = false;
        try {
            /*
             * Estbalish our phyiscal streams
             */
            jarIn = new FileInputStream(jar);
            jarOut = new FileOutputStream(target);
            /*
             * Construct our Jar parsers
             */
            JarInputStream jis = new JarInputStream(jarIn);
            JarOutputStream jos = new JarOutputStream(jarOut);

            /*
             * For each entry
             */
            JarEntryIterator jei = new JarEntryIterator(jis);
            while (jei.hasNext()) {
                JarEntry e = jei.next();
                /*
                 * Are we interested in using the alternate source?
                 */
                InputStream dataSource;
                //Is the entry name the name we are replacing?
                String entryName = e.getName();
                if (entryName.equals(name)) {
                    wasReplaced = true;
                    dataSource = source;
                    e = new JarEntry(name);
                    e.setSize(size);
                } else {
                    dataSource = jis;
                }
                jos.putNextEntry(e);

                /*
                 * Copy the data
                 */
                StreamCopier sc = new StreamCopier(dataSource, jos);
                sc.doCopy();
            }
            /*
             * Flush the output stream
             */
            jos.finish();
            jos.flush();
            jos.close();
            jos = null;
        } finally {
            /*
             * Ensure our input stream is closed
             */
            if (jarIn != null) {
                jarIn.close();
                jarIn = null;
            }

            /*
             * Ensure our output stream is closed
             */
            if (jarOut != null) {
                jarOut.close();
                jarOut = null;
            }
        }
        return wasReplaced;
    }
}
