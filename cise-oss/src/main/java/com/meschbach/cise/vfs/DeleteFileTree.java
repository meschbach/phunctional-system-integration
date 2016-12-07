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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/vfs/DeleteFileTree.java $
 * $Id: DeleteFileTree.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.vfs;

import java.io.File;
import java.io.IOException;

/**
 * The <code>DeleteFileTree</code> class encapsulates an algorithm for deleting
 * a file tree.<p>
 *
 * Copyright 2010-2011 by Mark Eschbach, under Apache License, Vesrion 2.0<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.2.0
 * @version 1.0.1
 */
public class DeleteFileTree {

    public static void delete(File base) throws IOException {
        if (base.isDirectory()) {
            for (File sub : base.listFiles()) {
                delete(sub);
            }
        } else {
            base.delete();
        }
    }
}
