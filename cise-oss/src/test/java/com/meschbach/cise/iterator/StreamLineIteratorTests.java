/*
 * Copyright 2011 Mark Eschbach.
 *
 * $HeadURL$
 * $Id$
 */
package com.meschbach.cise.iterator;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.testng.annotations.Test;
import static org.mockito.BDDMockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author "Mark Eschbach" (meschbach@gmail.com)
 */
public class StreamLineIteratorTests {

    @Test
    public void emptySourceProducesEmptySLI() throws IOException {
        StreamLineIterator sli = new StreamLineIterator(new EmptyIterator<Character, IOException>());
        assertFalse(sli.hasNext());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void nextOnEmptySourceProducesException() throws IOException {
        StreamLineIterator sli = new StreamLineIterator(new EmptyIterator<Character, IOException>());
        sli.next();
    }

    @Test
    public void whenSourceHasNextSLIHasNext() throws IOException {
        /*
         * 
         */
        MIterator<Character, IOException> source = mock(MIterator.class);
        given(source.hasNext()).willReturn(true);
        /*
         *
         */
        StreamLineIterator sli = new StreamLineIterator(source);
        assertTrue(sli.hasNext());
    }

    @Test
    public void anEmptyResultOnImmediateTerminator() throws IOException {
        /*
         *
         */
        MIterator<Character, IOException> source = mock(MIterator.class);
        given(source.hasNext()).willReturn(true);
        given(source.next()).willReturn('\n');
        /*
         * 
         */
        StreamLineIterator sli = new StreamLineIterator(source);
        StringBuilder sb = sli.next();
        /*
         *
         */
        assertEquals(sb.length(), 0);
    }

    @Test
    public void aSimpleResultWithTerminator() throws IOException {
        /*
         *
         */
        MIterator<Character, IOException> source = mock(MIterator.class);
        given(source.hasNext()).willReturn(true);
        given(source.next()).willReturn('a', 'b', 'c', '\n');
        /*
         * 
         */
        StreamLineIterator sli = new StreamLineIterator(source);
        StringBuilder sb = sli.next();
        /*
         *
         */
        assertEquals(sb.length(), 3);
        assertEquals(sb.toString(), "abc");
    }

    @Test
    public void aComplexInputSetGeneratesMultipleOutputs() throws IOException {
        /*
         *
         */
        Character inputs[] = new Character[]{'a', 'b', '\n', 'c', 'd', 'e', '\n', 'f'};
        ArrayIterator<Character, IOException> source = new ArrayIterator<Character, IOException>(inputs);
        /*
         * 
         */
        StreamLineIterator sli = new StreamLineIterator(source);
        assertEquals(sli.next().toString(), "ab");
        assertEquals(sli.next().toString(), "cde");
        assertEquals(sli.next().toString(), "f");
    }

    @Test
    public void aComplexInputSetGeneratesCorrectHasNexts() throws IOException {
        /*
         *
         */
        Character inputs[] = new Character[]{'a', 'b', '\n', 'c', 'd', 'e', '\n', 'f'};
        ArrayIterator<Character, IOException> source = new ArrayIterator<Character, IOException>(inputs);
        /*
         *
         */
        StreamLineIterator sli = new StreamLineIterator(source);
        assertTrue(sli.hasNext());
        assertEquals(sli.next().toString(), "ab");
        assertTrue(sli.hasNext());
        assertEquals(sli.next().toString(), "cde");
        assertTrue(sli.hasNext());
        assertEquals(sli.next().toString(), "f");
        assertFalse(sli.hasNext());
    }
}
