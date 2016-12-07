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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/FileArtifactLocator.java $
 * $Id: FileArtifactLocator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.PSIException;
import java.io.File;

/**
 * A <code>FileArtifactLocator</code> locates an artifact.  The ArtifactLocator is
 * responsible for knowing which artifact to locate.
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.1.0
 * @version 1.0.0
 */
public interface FileArtifactLocator {

    /**
     * Resolves the artifact which this locator represents.
     * 
     * @return a file object describing the location of artifact
     * @throws PSIException if the artifact could not be located
     * @since 1.1.0
     */
    public File resolveArtifact() throws PSIException;
}
