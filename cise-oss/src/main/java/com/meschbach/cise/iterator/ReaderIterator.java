/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/ReaderIterator.java $
 * $Id: ReaderIterator.java 330 2011-04-10 06:45:40Z meschbach@gmail.com $
 */
package com.meschbach.cise.iterator;

import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;

/**
 *
 * @author "Mark Eschbach" (meschbach@gmail.com)
 */
public class ReaderIterator implements MIterator<Character, IOException> {

    Reader source;
    char cachedData;
    boolean hasCachedData;

    public ReaderIterator(Reader source) {
        this.source = source;
        hasCachedData = false;
    }

    public boolean hasNext() throws IOException {
        if (!hasCachedData) {
            int input = source.read();
            if (input != -1) {
                cachedData = (char) input;
                hasCachedData = true;
            } else {
                hasCachedData = false;
            }
        }
        return hasCachedData;
    }

    public Character next() throws IOException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        hasCachedData = false;
        return cachedData;
    }

    public void close() {
        try {
            source.close();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
