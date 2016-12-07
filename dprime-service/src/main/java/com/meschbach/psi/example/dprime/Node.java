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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/Node.java $
 * $Id: Node.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime;

import com.meschbach.psi.example.dprime.work.Work;

/**
 * A <code>Node</code> represents a unit capable of executing units of work
 * towards the completion of the DPrime goal.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public interface Node {

    public String getName();

    public boolean mayAccept(Work aUnit);
}
