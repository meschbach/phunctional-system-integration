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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/Closable.java $
 * $Id: Closable.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise;

/**
 * A <code>Closable</code> is an object which provides a method to
 * release resources associated with such an object in an exception safe
 * manner.  Any condition was is considered exceptional within the close
 * message should be considered a severe runtime issue and the appropriate
 * subclass of <code>RuntimeException</code> should be thrown.<p>
 *
 * This interface is under an Apache License, Version 2.0 and is copyright
 * 2010-2011 by Mark Eschbach.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.0.0
 * @version 1.0.0
 */
public interface Closable {

    /**
     * Cleans up any used resources.  If an underlying resource will throw an
     * exceptional condition, the underlying error should be considered a
     * runtime problem and should probably be logged.
     *
     * If the resource this interface is implemented against is already closed
     * then this method should do nothing more.
     */
    public void close();
}
