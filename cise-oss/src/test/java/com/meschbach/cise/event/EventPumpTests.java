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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/test/java/com/meschbach/cise/event/EventPumpTests.java $
 * $Id: EventPumpTests.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.event;

import java.util.EventListener;
import static org.mockito.BDDMockito.*;
import org.testng.annotations.Test;

/**
 * This test set ensures that basic functions of the EventPump class work as
 * expected.  This class should grow over time to ensure the event pump
 * maintains its functionality.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class EventPumpTests {

    @Test
    public void testEmptyPumpHasNoProblems() {
        /*
         * Given
         */
        EventPump e = new EventPump();
        Dispatcher d = mock(Dispatcher.class);
        when(d.dispatch(any(EventListener.class))).thenReturn(true);
        /*
         * When
         */
        e.dispatch(d);
        /*
         * Then
         */
        verifyZeroInteractions(d);
    }

    @Test
    public void testPumpWillDispatchToOneListener() {
        /*
         * Given
         */
        EventListener el = new EventListener() {
        };
        EventPump e = new EventPump();
        e.addListener(el);
        Dispatcher d = mock(Dispatcher.class);
        when(d.dispatch(any(EventListener.class))).thenReturn(true);
        /*
         * When
         */
        e.dispatch(d);
        /*
         *
         */
        verify(d).dispatch(el);
    }

    @Test
    public void testPumpWillDispatchToMultipleListeners() {
        /*
         * Given
         */
        EventListener el1 = new EventListener() {
        };
        EventListener el2 = new EventListener() {
        };
        EventPump e = new EventPump();
        e.addListener(el1);
        e.addListener(el2);
        Dispatcher d = mock(Dispatcher.class);
        when(d.dispatch(any(EventListener.class))).thenReturn(true);
        /*
         * When
         */
        e.dispatch(d);
        /*
         *
         */
        verify(d).dispatch(el1);
        verify(d).dispatch(el2);
    }
}
