/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-web-node/src/main/java/com/meschbach/psi/examples/dprime/wnode/TemplatePage.java $
 * $Id: TemplatePage.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.wnode;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class TemplatePage extends WebPage {

    @Override
    protected void onInitialize() {
        //
        super.onInitialize();
        /*
         * 
         */
        add(new Label("pageTitle", getPageTitle() + " - DPrime Web Node"));
    }

    protected String getPageTitle() {
        return "Untitled";
    }
}
