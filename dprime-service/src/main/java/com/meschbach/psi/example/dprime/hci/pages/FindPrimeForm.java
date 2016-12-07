/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/hci/pages/FindPrimeForm.java $
 * $Id: FindPrimeForm.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.hci.pages;

import com.meschbach.psi.example.dprime.DPrimeService;
import java.math.BigDecimal;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class FindPrimeForm extends Form<FindPrimeTask> {

    IModel<DPrimeService> service;

    public FindPrimeForm(String id, IModel<DPrimeService> service, IModel<FindPrimeTask> model) {
        super(id, model);
        this.service = service;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        TextField<BigDecimal> queryNumber = new TextField<BigDecimal>("prime_query", new PropertyModel<BigDecimal>(getModel(), "number"));
        add(queryNumber);
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();

        service.getObject().findIfPrime(getModel().getObject().getNumber());
        getModel().getObject().setNumber(BigDecimal.ZERO);
    }
}
