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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/AbstractWork.java $
 * $Id: AbstractWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work;

import com.meschbach.cise.event.EventPump;
import com.meschbach.psi.example.dprime.work.event.WorkAvailableDispatcher;
import com.meschbach.psi.example.dprime.work.event.WorkCalculatingDispatcher;
import com.meschbach.psi.example.dprime.work.event.WorkCompletedDispatcher;
import com.meschbach.psi.example.dprime.work.event.WorkListener;
import com.meschbach.psi.example.dprime.work.event.WorkSetupDispatcher;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @version 1.0.0
 */
public abstract class AbstractWork<T> implements Work<T> {

    private State currentState;
    private EventPump<WorkListener> workEventPump;

    public AbstractWork() {
        currentState = State.Setup;
        workEventPump = new EventPump<WorkListener>();
    }

    public synchronized void addStateListener(WorkListener listener) {
        if (getState().equals(State.Completed)) {
            new WorkCompletedDispatcher<WorkListener>(this).dispatch(listener);
        } else {
            workEventPump.addListener(listener);
        }
    }

    public synchronized void removeStateListener(WorkListener listener) {
        workEventPump.removeListener(listener);
    }

    protected synchronized void dispatchCompleted() {
        currentState = State.Completed;
        workEventPump.dispatch(new WorkCompletedDispatcher<WorkListener>(this));
    }

    protected synchronized void dispatchSetup() {
        currentState = State.Setup;
        workEventPump.dispatch(new WorkSetupDispatcher(this));
    }

    protected synchronized void dispatchCalculating() {
        currentState = State.Calculating;
        workEventPump.dispatch(new WorkCalculatingDispatcher(this));
    }

    protected synchronized void dispatchAvailable() {
        currentState = State.Available;
        workEventPump.dispatch(new WorkAvailableDispatcher(this));
    }

    public synchronized State getState() {
        return currentState;
    }
}
