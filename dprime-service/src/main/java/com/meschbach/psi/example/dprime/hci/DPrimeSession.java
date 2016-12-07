/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/hci/DPrimeSession.java $
 * $Id: DPrimeSession.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.hci;

import com.meschbach.psi.example.dprime.DPrimeService;
import org.apache.wicket.Request;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DPrimeSession extends WebSession {

    public DPrimeSession(Request request) {
        super(request);
    }
}
