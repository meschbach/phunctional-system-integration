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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-core/src/main/java/com/meschbach/psi/CompositePSIException.java $
 * $Id: CompositePSIException.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class CompositePSIException extends PSIException {

    List<PSIException> exceptions;

    public CompositePSIException() {
        initCompositeException();
    }

    public CompositePSIException(String message) {
        super(message);
        initCompositeException();
    }

    public CompositePSIException(List<PSIException> exceptions) {
        this.exceptions = exceptions;
        initCompositeException();
    }

    public CompositePSIException(String message, List<PSIException> exceptions) {
        this.exceptions = exceptions;
        initCompositeException();
    }

    public void addException(PSIException pe) {
        exceptions.add(pe);
    }

    private void initCompositeException() {
        if (exceptions == null) {
            exceptions = new LinkedList<PSIException>();
        }
    }

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder(getClass().getCanonicalName());
        builder.append(" (");
        for (PSIException pe : exceptions) {
            builder.append(pe.getMessage());
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        for (PSIException pe : exceptions) {
            pe.printStackTrace(s);
        }
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        for (PSIException pe : exceptions) {
            pe.printStackTrace(s);
        }
    }

    public boolean shouldThrow() {
        return !exceptions.isEmpty();
    }

    public void throwIfApplicable() throws PSIException {
        if (shouldThrow()) {
            throw new CompositePSIException(exceptions);
        }
    }
}
