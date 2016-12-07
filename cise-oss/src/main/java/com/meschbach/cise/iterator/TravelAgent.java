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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/iterator/TravelAgent.java $
 * $Id: TravelAgent.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.iterator;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com;
 */
public class TravelAgent<E, T extends Exception, P extends Throwable> {

    IteratorFactory<E, T> factory;
    Visitor<E, P> delegate;

    public TravelAgent(IteratorFactory<E, T> factory) {
        this(factory, DEFAULT_VISITOR);
    }

    public TravelAgent(IteratorFactory<E, T> factory, Visitor<E, P> delegate) {
        this.factory = factory;
        this.delegate = delegate;
    }

    public Visitor<E, P> getDelegate() {
        return delegate;
    }

    public void setDelegate(Visitor<E, P> delegate) {
        if (delegate == null) {
            this.delegate = DEFAULT_VISITOR;
        } else {
            this.delegate = delegate;
        }
    }

    public void visitElements() throws T, P {
        MIterator<E, T> i = factory.buildIterator();
        while (i.hasNext()) {
            E anElement = i.next();
            delegate.visitElement(anElement);
        }
    }

    public interface Visitor<E, P extends Throwable> {

        public void visitElement(E anElement) throws P;
    }

    public static class IgnorantVisitor<E, P extends Throwable> implements Visitor<E, P> {

        public void visitElement(E anElement) throws P {
        }
    }
    public static final IgnorantVisitor DEFAULT_VISITOR = new IgnorantVisitor();
}
