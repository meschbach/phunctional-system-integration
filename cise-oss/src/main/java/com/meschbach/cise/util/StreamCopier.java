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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/util/StreamCopier.java $
 * $Id: StreamCopier.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A <code>StreamCopier</code> is the encapsulation of an algorithm to copy data
 * from an input stream to an output stream.<p>
 * 
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 * @version 1.1.0
 * @since 1.2.0
 */
public class StreamCopier {

    /**
     * The <code>source</code> is input stream to read the data from
     */
    protected InputStream source;
    /**
     * The <code>sink</code> is the destination to place the read data.
     */
    protected OutputStream sink;
    /**
     * The <code>bufferSize</code> is the block size to read and write.
     */
    protected int bufferSize;
    /**
     * The <code>continueReading</code> flag allows for the class client to
     * prematurely end the copying
     */
    protected boolean continueReading;

    /**
     * The <code>copy</code> class method provides a simple utility method to
     * copy the given data.
     * 
     * @param source is the source to copy the data from
     * @param target is the sink to place the data on
     * @throws IOException if a problem occurs while attempting to copy the data
     */
    public static void copy(InputStream source, OutputStream target) throws IOException {
        new StreamCopier(source, target).doCopy();
    }

    public StreamCopier() {
    }

    public StreamCopier(InputStream source, OutputStream sink) {
        this(source, sink, 1024);
    }

    public StreamCopier(InputStream source, OutputStream sink, int bufferSize) {
        this.source = source;
        this.sink = sink;
        this.bufferSize = bufferSize;
    }

    public synchronized void start() {
        continueReading = true;
        notifyAll();
    }

    public synchronized void stop() {
        continueReading = false;
    }

    public synchronized boolean continueReading() {
        return continueReading;
    }

    public long doCopy() throws IOException {
        long count = 0;
        start();
        byte buffer[] = new byte[bufferSize];
        while (continueReading()) {
            int totalRead = source.read(buffer);
            if (totalRead == -1) {
                stop();
            } else {
                count += totalRead;
                sink.write(buffer, 0, totalRead);
            }
        }
        return count;
    }

    public long doCopy(long limit) throws IOException {
        long count = 0;
        start();
        byte buffer[] = new byte[bufferSize];
        while (continueReading() && (limit - count) > 0) {
            long leftToRead = limit - count;
            int totalRead = source.read(buffer, 0, (int) leftToRead);
            if (totalRead == -1) {
                stop();
            } else {
                count += totalRead;
                sink.write(buffer, 0, totalRead);
            }
        }
        return count;
    }

    public OutputStream getSink() {
        return sink;
    }

    public void setSink(OutputStream sink) {
        this.sink = sink;
    }

    public InputStream getSource() {
        return source;
    }

    public void setSource(InputStream source) {
        this.source = source;
    }
}
