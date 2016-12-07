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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/psi-util/src/main/java/com/meschbach/psi/util/RFC2396.java $
 * $Id: RFC2396.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.util;

/**
 * This class is intended to provide utility methods to encode and decode
 * fragments of URI's according to RFC2396.<p>
 * At the current time there is a minimalist implementation, as you discover
 * more requirements please expand this class.
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 * @see http://www.ietf.org/rfc/rfc2396.txt
 */
public class RFC2396 {

    public static String encode(CharSequence s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ' ': {
                    String result = String.format("%%%X", (int) c);
                    sb.append(result);
                }
                break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
