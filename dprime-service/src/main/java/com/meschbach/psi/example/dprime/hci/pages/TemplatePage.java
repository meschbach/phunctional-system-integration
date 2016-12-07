/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/hci/pages/TemplatePage.java $
 * $Id: TemplatePage.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.hci.pages;

import com.meschbach.psi.example.dprime.DPrimeService;
import com.meschbach.psi.example.dprime.hci.DPrimeApplication;
import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class TemplatePage extends WebPage {

    public TemplatePage(IPageMap pageMap, PageParameters parameters) {
        super(pageMap, parameters);
    }

    public TemplatePage(PageParameters parameters) {
        super(parameters);
    }

    public TemplatePage(IPageMap pageMap, IModel<?> model) {
        super(pageMap, model);
    }

    public TemplatePage(IPageMap pageMap) {
        super(pageMap);
    }

    public TemplatePage(IModel<?> model) {
        super(model);
    }

    public TemplatePage() {
    }

    protected String getPageTitle(){ return "Untitled"; }
    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("page_title", getPageTitle()));
    }

    public IModel<DPrimeService> getDPrimeService() {
        return ((DPrimeApplication) getApplication()).getDPrimeService();
    }
}
