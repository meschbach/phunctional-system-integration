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
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/salutator/src/main/java/com/meschbach/psi/example/salutator/SalutatorJAXRS.java $
 * $Id: SalutatorJAXRS.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.salutator;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class SalutatorJAXRS {

    Salutator greeter;

    @GET
    @Path("/{name}")
    @Produces("text/plain")
    public String getGreeting(@PathParam("name") String name) {
        if (!greeter.hasGreetingFor(name)) {
            throw new WebApplicationException(404);
        } else {
            return greeter.getGreetingFor(name);
        }
    }

    @PUT
    @Path("{name}")
    @Consumes("text/plain")
    public Response setGreeting(
            @PathParam("name") String name,
            String message) {
        boolean wasCreated = !greeter.hasGreetingFor(name);
        greeter.setGreetingFor(name, message);

        Response result;
        if (wasCreated) {
            result = Response.created(URI.create(name)).build();
        } else {
            result = Response.noContent().build();
        }
        return result;
    }

    public Salutator getGreeter() {
        return greeter;
    }

    public void setGreeter(Salutator greeter) {
        this.greeter = greeter;
    }
}
