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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-core/src/main/java/com/meschbach/psi/example/dprimecore/prime/CompletionClosure.java $
 * $Id: CompletionClosure.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprimecore.prime;

import java.math.BigDecimal;

/**
 * The <code>CompletionClosure</code> is a method of delivering feedback to
 * the <code>PrimalityCheck</code>.  After each operation you notify the results
 * by invoking the member method <code>completedResult(BigDecimal)</code>.
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @version 1.0.0
 */
public interface CompletionClosure {

    public void completedResult(BigDecimal result);
}
