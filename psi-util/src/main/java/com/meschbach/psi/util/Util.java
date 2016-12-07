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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/Util.java $
 * $Id: Util.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

import com.meschbach.psi.PSIException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * The <code>Util</code> class contains a number of static methods of reusable
 * algorithms.
 * 
 * This class is copyright 2010-2011 by Mark Eschbach and is licensed under the
 * Apache License, Version 2.0; accessible at
 * http://www.apache.org/licenses/LICENSE-2.0.<p>
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 * @since 1.0.0
 * @version 1.0.0
 */
public class Util {

    public static String convertToString(InputStream is) throws PSIException {
        try {
            /*
             * Allocate our bufer
             */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            /*
             * While there is data available to be copied, copy the data
             */
            boolean isGood = true;
            while (isGood) {
                int totalRead = is.read(buffer);
                if (totalRead == -1) {
                    isGood = false;
                } else {
                    baos.write(buffer, 0, totalRead);
                }
            }
            /*
             * Return the content as a string
             */
            return baos.toString();
        } catch (IOException ioe) {
            throw new PSIException(ioe);
        }
    }
}
