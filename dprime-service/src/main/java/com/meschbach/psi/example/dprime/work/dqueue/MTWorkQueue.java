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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/dqueue/MTWorkQueue.java $
 * $Id: MTWorkQueue.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.dqueue;

import com.meschbach.cise.Compute;
import com.meschbach.cise.iterator.MIterator;
import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprime.work.WorkQueue;
import com.meschbach.psi.example.dprime.work.WorkQueueMonitor;
import static com.meschbach.cise.iterator.Iterators.*;
import static com.meschbach.cise.predicates.Predicates.*;
import com.meschbach.psi.example.dprime.work.IsCompletedPredicate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implements a distributed work queue utilizing a fixed thread pool size
 * 
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class MTWorkQueue implements WorkQueue<DistributedWork>, WorkQueueMonitor<DistributedWork>, Stopable {

    ExecutorService es;
    List<UnitOfWork> work;

    public MTWorkQueue() {
        es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1);
        work = new CopyOnWriteArrayList<UnitOfWork>();
    }

    public void stop() {
        es.shutdown();
    }

    public void submit(final DistributedWork dwork) {
        UnitOfWork uow = new UnitOfWork(dwork);
        work.add(uow);
        es.submit(uow);
    }

    public WorkQueue getMonitoredQueue() {
        return this;
    }

    public MIterator<DistributedWork, Exception> getCompletedWork() {
        return select(new IsCompletedPredicate(), getWork());
    }

    public MIterator<DistributedWork, Exception> getOutstandingWork() {
        return select(not(new IsCompletedPredicate()), fromCollection(work));
    }

    public int getTaskCount() {
        return work.size();
    }

    public MIterator<DistributedWork, Exception> getWork() {
        return computing(new WorkFromUnitOfWork(), fromCollection(work));
    }

    public MIterator<DistributedWork, Exception> getWork(int base, int length) {
        return limit(offset(getWork(), base), length);
    }
}

class WorkFromUnitOfWork implements Compute<UnitOfWork, DistributedWork, Exception> {

    public DistributedWork compute(UnitOfWork input) throws Exception {
        return input.getWork();
    }
}
