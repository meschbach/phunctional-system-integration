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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/salutator/src/main/java/com/meschbach/psi/example/salutator/MemorySalutator.java $
 * $Id: MemorySalutator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.salutator;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class MemorySalutator implements Salutator {

    Map<String, String> greetings;

    public MemorySalutator() {
        greetings = new Hashtable<String, String>();
    }

    public String getGreetingFor(String aName) {
        return greetings.get(aName);
    }

    public boolean hasGreetingFor(String aName) {
        return greetings.containsKey(aName);
    }

    public void setGreetingFor(String aName, String aGreeting) {
        greetings.put(aName, aGreeting);
    }
}
