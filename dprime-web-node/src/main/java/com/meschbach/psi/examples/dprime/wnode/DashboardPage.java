/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-web-node/src/main/java/com/meschbach/psi/examples/dprime/wnode/DashboardPage.java $
 * $Id: DashboardPage.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.wnode;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DashboardPage extends TemplatePage {

    @Override
    protected void onInitialize() {
        //
        super.onInitialize();
        /*
         * Grab our node name
         */
        
    }

    @Override
    protected String getPageTitle() {
        return "Dashboard";
    }
}
