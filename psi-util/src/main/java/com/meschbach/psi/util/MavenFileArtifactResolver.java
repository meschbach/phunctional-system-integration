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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/MavenFileArtifactResolver.java $
 * $Id: MavenFileArtifactResolver.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.PSIException;
import java.io.File;

/**
 * A <code>MavenFileArtifactResolver</code> resolves a Maven dependency in the
 * 'target/dependency' directory starting with the given artifact name.  This
 * is useful to load a dependent artifact while not pinning down the specific
 * version of the artifact, which would quickly become a maintenance nightmare<p>
 *
 * This code was refactored from the WebAppHarness to be reused by other
 * objects.<p>
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since  1.1.0
 * @version 1.1.0
 */
public class MavenFileArtifactResolver implements FileArtifactLocator {

    /**
     * The <code>artifact</code> contains the beginning of the file name we are
     * going to match.
     */
    protected String artifact;

    /**
     * Constructs a new <code>MavenFileArtifactResolver</code> to resolve a
     * dependent artifact starting with the <code>artifact</code> name.  Under
     * the default configuration of the Maven dependency plug-in this should
     * be the 'artifactId' element for the dependency.
     * 
     * @param artifact is the beginning name of the artifact to resolve
     */
    public MavenFileArtifactResolver(String artifact) {
        this.artifact = artifact;
    }

    /**
     * @see  FileArtifactLocator#resolveArtifact() 
     */
    public File resolveArtifact() throws PSIException {
        /*
         * Locate the directory containing the dependencies
         */
        File cwd = new File(".");
        File dependenciesDir = new File(cwd, "target/dependency");
        if (!dependenciesDir.exists()) {
            throw new PSIException("Depedency directory '" + dependenciesDir.getAbsolutePath() + "' doesn not exist");
        }
        /*
         * Locate our artifact
         */
        String[] files = dependenciesDir.list();
        String dependencyName = null;
        for (String f : files) {
            if (f.startsWith(artifact)) {
                if (dependencyName != null) {
                    throw new PSIException("Multiple dependencies starting with '" + artifact + "' exist");
                }
                dependencyName = f;
            }
        }
        if (dependencyName == null) {
            throw new PSIException("Unable to find a dependency starting with " + artifact);
        }
        /*
         * Create a file object representing the dependency name
         */
        return new File(dependenciesDir, dependencyName);
    }

    /**
     * Attempts to resolve the given artifact, and failing that will throw an
     * AssertionError with the reason why we couldn't locate the artifact.
     * 
     * @param anArtifact is the artifact to find
     * @return a file representing the given artifact
     */
    public static File assertAndRetrieveArtifact(String anArtifact) {
        try {
            return new MavenFileArtifactResolver(anArtifact).resolveArtifact();
        } catch (PSIException e) {
            throw new AssertionError(e.getMessage());
        }
    }
}
