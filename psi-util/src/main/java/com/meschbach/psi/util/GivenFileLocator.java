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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/GivenFileLocator.java $
 * $Id: GivenFileLocator.java 331 2011-04-24 04:39:25Z meschbach@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.PSIException;
import java.io.File;

/**
 * A <code>GivenFileLocator</code> is a kind of <code>FileArtifactLocator</code>
 * which resolves to a given file name.  This class is intended to be used
 * with dependency resolution which place artifacts in known places.
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0 (PSI 2.3.0)
 * @version  1.0.0
 */
public class GivenFileLocator implements FileArtifactLocator {

    /**
     * The <code>fileName</code> is the name of the file this locator will
     * be resolved too.
     */
    protected String fileName;

    /**
     * Constructs a new <code>GivenFileLocator</code> which will resolve to the
     * given artifact name.
     *
     * @param fileName is the file name of the artifact to resolve to
     */
    public GivenFileLocator(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @see  FileArtifactLocator#resolveArtifact()
     */
    public File resolveArtifact() throws PSIException {
        File resolved = new File(fileName);
        if (!resolved.exists()) {
            throw new PSIException("Artifact '" + resolved.getAbsolutePath() + "' does not exist");
        }
        return resolved;
    }
}
