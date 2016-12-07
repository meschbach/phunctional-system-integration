/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/StreamLineIterator.java $
 * $Id: StreamLineIterator.java 329 2011-04-10 06:37:29Z meschbach@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * A <code>StreamLineIterator</code> is an iterator for building a character
 * sequence from an input source on a per-line basis.  The resulting new line
 * character will be discarded.
 * <p>
 * This will read from the underlying source until one of two conditions is
 * true: (a) the underlying source no longer has data or (b) we have encountered
 * our intended token.
 * <p>
 * There are many alternatives to this class.  If you are just looking for the
 * line number you should consider java.io.LineNumberReader.
 *
 * @author "Mark Eschbach" (meschbach@gmail.com)
 * @version 1.0.0
 * @since 2.5.0
 */
public class StreamLineIterator implements MIterator<CharSequence, IOException> {

    MIterator<Character, IOException> source;
    char token;
    int lineNumber;

    public StreamLineIterator(MIterator<Character, IOException> source) {
        this(source, '\n', 0);
    }

    public StreamLineIterator(MIterator<Character, IOException> source, char token, int lineNumber) {
        this.source = source;
        this.token = token;
        this.lineNumber = lineNumber;
    }

    public boolean hasNext() throws IOException {
        return source.hasNext();
    }

    public StringBuilder next() throws IOException {
        /*
         * 
         */
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        /*
         * 
         */
        boolean continueSearch = true;
        StringBuilder buffer = new StringBuilder();
        do {
            char result = source.next();
            if (result != token) {
                buffer.append(result);
            } else {
                continueSearch = false;
            }
        } while (source.hasNext() && continueSearch);
        /*
         * Record we have completed another round
         */
        lineNumber++;
        /*
         * Retunr our result
         */
        return buffer;
    }

    public void close() {
        source.close();
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public MIterator<Character, IOException> getSource() {
        return source;
    }

    public void setSource(MIterator<Character, IOException> source) {
        this.source = source;
    }

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }
}
