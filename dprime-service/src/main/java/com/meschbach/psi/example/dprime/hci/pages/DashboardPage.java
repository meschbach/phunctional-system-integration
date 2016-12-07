/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/hci/pages/DashboardPage.java $
 * $Id: DashboardPage.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.hci.pages;

import com.meschbach.cise.iterator.MIterator;
import com.meschbach.cise.iterator.MIteratorCollection;
import com.meschbach.psi.example.dprime.work.InternalWork;
import com.meschbach.psi.example.dprime.pkb.PrimeKB;
import com.meschbach.psi.example.dprime.work.WorkQueueMonitor;
import java.math.BigDecimal;
import java.util.Iterator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DashboardPage extends TemplatePage {

    public DashboardPage() {
    }

    @Override
    protected String getPageTitle() {
        return "Instrument Panel";
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        PrimeKB pkb = getDPrimeService().getObject().getPrimeKB();
        String pkbImpl = pkb.getClass().getCanonicalName();
        add(new Label("pkb_implementation", pkbImpl));
        add(new Label("pkb_largetKnowPrime", pkb.getLargestPrime().toPlainString()));
        add(new Label("pkb_smallestKnowPrime", pkb.getSmallestPrime().toPlainString()));
        add(new FindPrimeForm("findPrimeForm", getDPrimeService(), new Model<FindPrimeTask>(new FindPrimeTask(BigDecimal.ZERO))));
        add(new TaskTable("tasks", new TaskDataProvider(new PropertyModel<WorkQueueMonitor<InternalWork>>(getDPrimeService(), "InternalMonitor"))));
    }
}

class HashTaskModel extends LoadableDetachableModel<InternalWork> {

    IModel<WorkQueueMonitor<InternalWork>> queueMonitor;
    int hash;

    public HashTaskModel(InternalWork object, IModel<WorkQueueMonitor<InternalWork>> queueMonitor) {
        hash = object.hashCode();
        this.queueMonitor = queueMonitor;
    }

    @Override
    protected InternalWork load() {
        try {
            WorkQueueMonitor<InternalWork> wqm = queueMonitor.getObject();
            MIterator<InternalWork, Exception> mi = wqm.getWork();
            InternalWork work = null;
            while (mi.hasNext() && work == null) {
                InternalWork iw = mi.next();
                if (iw.hashCode() == hash) {
                    work = iw;
                }
            }
            return work;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class TaskDataProvider implements IDataProvider<InternalWork> {

    IModel<WorkQueueMonitor<InternalWork>> queueMonitor;

    public TaskDataProvider(IModel<WorkQueueMonitor<InternalWork>> queueMonitor) {
        this.queueMonitor = queueMonitor;
    }

    public Iterator<? extends InternalWork> iterator(int first, int count) {
        WorkQueueMonitor<InternalWork> queue = queueMonitor.getObject();
        return new MIteratorCollection<InternalWork>(queue.getWork(first, count));
    }

    public IModel<InternalWork> model(InternalWork object) {
        return new HashTaskModel(object, queueMonitor);
    }

    public int size() {
        return queueMonitor.getObject().getTaskCount();
    }

    public void detach() {
        queueMonitor.detach();
    }
}

class TaskTable extends DataView<InternalWork> {

    public TaskTable(String id, IDataProvider<InternalWork> dataProvider, int itemsPerPage) {
        super(id, dataProvider, itemsPerPage);
    }

    public TaskTable(String id, IDataProvider<InternalWork> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected void populateItem(Item<InternalWork> item) {
        InternalWork iw = item.getModelObject();
        item.add(new Label("hash", "" + iw.hashCode()));
        item.add(new Label("state", iw.getState().name()));
        item.add(new Label("description", iw.getDescription()));
    }
}
