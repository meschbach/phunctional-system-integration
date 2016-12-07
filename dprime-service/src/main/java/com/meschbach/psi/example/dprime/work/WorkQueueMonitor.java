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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/WorkQueueMonitor.java $
 * $Id: WorkQueueMonitor.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work;

import com.meschbach.cise.iterator.MIterator;

/**
 * A <code>WorkQueueMonitor</code> allows for administrative interaction with a
 * work queue.
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 * @since 1.0.0
 * @version 1.0.0
 */
public interface WorkQueueMonitor<W extends Work> {

    public WorkQueue<W> getMonitoredQueue();

    public MIterator<W, Exception> getWork();

    public MIterator<W, Exception> getWork(int base, int length);

    public MIterator<W, Exception> getOutstandingWork();

    public MIterator<W, Exception> getCompletedWork();

    public int getTaskCount();
}
