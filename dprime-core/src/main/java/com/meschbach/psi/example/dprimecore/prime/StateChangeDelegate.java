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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/prime/StateChangeDelegate.java $
 * $Id: StateChangeDelegate.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.prime;

/**
 * Notification from the <code>PrimalityCheck</code> to be handled by a
 * delegate.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public interface StateChangeDelegate<T, P> {

    /**
     * Notifies the delegate a specific PrimalityCheck has had an internal
     * state transition and a new operation is available.
     * 
     * @param checker is the checker where the transition occurred.
     */
    public void changedState(PrimalityCheck<T, P> checker);
}
