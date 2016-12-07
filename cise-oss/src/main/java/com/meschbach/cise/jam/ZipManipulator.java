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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/jam/ZipManipulator.java $
 * $Id: ZipManipulator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.jam;

import com.meschbach.cise.util.JarEntryIterator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * A <code>ZipManipulator</code> encapsulates a series of transformations on
 * a ZIP archive include: adding entries, removing entries, and replacing
 * entries.
 * 
 * Copyright 2010-2011 by Mark Eschbach, under Apache License, Vesrion 2.0<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 * @version 1.0.4
 * @since 1.1.0
 */
public class ZipManipulator {

    InputStream source;
    OutputStream destination;
    List<StreamProcessor> postProcessors;
    Map<String, EntryVisitor> filters;
    EntryVisitor defaultVisitor;

    public ZipManipulator() {
        filters = new HashMap<String, EntryVisitor>();
        postProcessors = new LinkedList<StreamProcessor>();
        defaultVisitor = CopyVisitor.getSharedInstance();
    }

    public OutputStream getDestination() {
        return destination;
    }

    public void setDestination(OutputStream destination) {
        this.destination = destination;
    }

    public InputStream getSource() {
        return source;
    }

    public void setSource(InputStream source) {
        this.source = source;
    }

    public void createEmptySource() throws IOException {
        /*
         * Create our buffer
         */
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(buffer);
        /*
         * Create our default entry
         */
        ZipEntry defaultEntry = new ZipEntry("META-INF/created-by");
        defaultEntry.setTime(System.currentTimeMillis());
        /*
         * Place and close the entry
         */
        zos.putNextEntry(defaultEntry);
        zos.write(getClass().getCanonicalName().getBytes());
        zos.close();
        /*
         * Grab the data as a new source
         */
        byte data[] = buffer.toByteArray();
        source = new ByteArrayInputStream(data);
    }

    public void performOperations() throws IOException {
        /*
         * Ensure our object is in a valid state
         */
        if (source == null) {
            throw new IllegalStateException("JAR source may not be null");
        }
        if (destination == null) {
            throw new IllegalStateException("JAR destination may not be null");
        }
        /*
         * Setup our input stream
         */
        JarInputStream jis = new JarInputStream(source);
        /*
         * Setup our output stream
         */
        JarOutputStream jos = new JarOutputStream(destination);
        jos.setLevel(9);
        jos.setMethod(JarOutputStream.DEFLATED);
        /*
         * While the source has entries
         */
        JarEntryIterator jei = new JarEntryIterator(jis);
        while (jei.hasNext()) {
            /*
             * Grab the source entry
             */
            JarEntry sourceEntry = jei.next();
            /*
             * Visit our entry processor
             */
            final String entryName = sourceEntry.getName();
            if (filters.containsKey(entryName)) {
                EntryVisitor ev = filters.get(entryName);
                ev.visitEntry(entryName, sourceEntry, jis, jos);
            } else {
                defaultVisitor.visitEntry(entryName, sourceEntry, jis, jos);
            }
        }
        /*
         * Notify all post processors
         */
        Iterator<StreamProcessor> isp = new LinkedList<StreamProcessor>(postProcessors).iterator();
        while (isp.hasNext()) {
            StreamProcessor sp = isp.next();
            sp.affectStream(jos);
        }
        /*
         * Finish the output stream
         */
        jos.finish();
        jos.flush();
    }

    public void addEntryVisitor(String name, EntryVisitor ev) {
        if (ev == null) {
            throw new NullPointerException("Entry visitor may not be null");
        }
        if (name == null) {
            throw new NullPointerException("Entry name may not be null");
        }
        filters.put(name, ev);
    }

    public void addPostProcessor(StreamProcessor processor) {
        if (processor == null) {
            throw new NullPointerException();
        }
        postProcessors.add(processor);
    }
}
