<?php
require 'site.php';

renderTemplate( "Salutator - Examples", function() {
	?>
	<strong>This document is current a work in progress</strong>
	<div>
		<h2>PSI Example: Salutator</h2>
		<h5>Author: Mark Eschbach</h5>
		<p>This application is simple and designed to show the most basic usage of PSI.</p>
		<p>Salutator is a web appplication providing HTTP interface via JAX-RS to a key value service.  Its nothing more than a toy application intended to show using PSI for testing web services</p>
		<p>I'm assuming you are familiar with <a href='http://maven.apache.org/'>Maven</a>.</p>
	</div>
	<ol>
		<li>
			<h3>Create a new Maven project</h3>
			<p>Create a new Maven project with the group ID 'com.example', artifact ID 'salutator-test', and version '1.0-SNAPSHOT'.</p>
		</li>
		<li>
			<h3>Add the project Maven repository</h3>
			<p>You could generate the artifacts yourself, however for the purposes fo this demonstration you can add the following repository to your project element:</p>
<pre class='code_xml'>
    &lt;repositories&gt;
        &lt;repository&gt;
            &lt;id&gt;meschbach.com-oss&lt;/id&gt;
            &lt;releases&gt;
                &lt;enabled/&gt;
                &lt;checksumPolicy&gt;fail&lt;/checksumPolicy&gt;
            &lt;/releases&gt;
            &lt;url&gt;http://meschbach.com/dist/m2/oss/&lt;/url&gt;
        &lt;/repository&gt;
    &lt;/repositories&gt;
</pre>
		</li>
		<li>
			<h3>Add the dependencies</h3>
			<p>Add the following Maven dependencies to your new project:</p>
			<table>
				<caption>Project dependencies</caption>
				<thead>
					<tr>
						<th>Group ID</th>
						<th>Artifact ID</th>
						<th>Version</th>
						<th>Type</th>
						<th>Reason</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td>Group ID</td>
						<td>Artifact ID</td>
						<td>Version</td>
						<td>Type</td>
						<td>Reason</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td>com.meschbach.psi</td>
						<td>psi</td>
						<td>2.2.0</td>
						<td>jar</td>
						<td>Pulls in the testing harness (both Tomcat and Jetty) and the associated utilties.</td>
					</tr>
					<tr>
						<td>org.testng</td>
						<td>testng</td>
						<td>5.14.6</td>
						<td>jar</td>
						<td><a href='http://testng.org/'>TestNG</a> is my perfered testing framework.  Feel free to translate to JUnit if you prefer.</td>
					</tr>
					<tr>
						<td>com.meschbach.psi.example</td>
						<td>salutator</td>
						<td>1.0-SNAPSHOT</td>
						<td>war</td>
						<td>The artifact for the web application we will be testing.</td>
					</tr>
				</tbody>
			</table>
		</li>
		<li>
			<h3>Use Java 5 and copy the dependnecies</h3>
			<p>You need to instruct Maven to pull in the dependencies so PSI may locate and deploy your WAR.  In addition the testing we'll be using utilizes annotations in Java 5.  Add the following under the root project element:</p>
<pre>
    &lt;build&gt;
        &lt;plugins&gt;
            &lt;plugin&gt;
                &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
                &lt;artifactId&gt;maven-compiler-plugin&lt;/artifactId&gt;
                &lt;configuration&gt;
                    &lt;source&gt;1.5&lt;/source&gt;
                    &lt;target&gt;1.5&lt;/target&gt;
                &lt;/configuration&gt;
            &lt;/plugin&gt;
            &lt;plugin&gt;
                &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
                &lt;artifactId&gt;maven-dependency-plugin&lt;/artifactId&gt;
                &lt;executions&gt;
                    &lt;execution&gt;
                        &lt;id&gt;copy-dependencies&lt;/id&gt;
                        &lt;phase&gt;compile&lt;/phase&gt;
                        &lt;goals&gt;
                            &lt;goal&gt;copy-dependencies&lt;/goal&gt;
                        &lt;/goals&gt;
                    &lt;/execution&gt;
                &lt;/executions&gt;
            &lt;/plugin&gt;
        &lt;/plugins&gt;
    &lt;/build&gt;
</pre>
		</li>
		<li>
			<h3>Code the tests</h3>
			<p>Create a new test class: <pre class='object_name'>com.example.psi.salutator.SalutatorTest</pre>.  Now we are going to setup the test class:</p>
			<pre id='salutator_test_template' class='code_java'>
package com.example.psi.salutator;

import com.meschbach.psi.jetty.LocalJettyBuilder;
import com.meschbach.psi.util.WebAppHarness;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.meschbach.psi.util.RESTClient.*;

public class SalutatorTest {

    WebAppHarness harness;

    @BeforeMethod
    public void setupHarness() throws Throwable {
        harness = new WebAppHarness(new LocalJettyBuilder(), "salutator-webapp", "/salutator");
        harness.start();
    }

    @AfterMethod
    public void shutdownHarness() throws Throwable {
        harness.shutdown();
    }

    public String getServiceURL() {
        return harness.getURL() + "/dci/salutator/";
    }
}
			</pre>
			<p>We now have our basic test fixture for testing our web application.  The instance variable <code>harness</code> contains a refernce to the object mediating our container.  So lets look at the individual code segments.</p>
			<p>The <strong>@BeforeMethod</strong> instructs TestNG to run the <code>setupHarness()</code> method prior to each test, and the <strong>@AfterMethod</strong> instructs TestNG to run the <code>shutdownHarness()</code> method after each test.  As we are allocating network ports ensuring the tests are shutdown is important as you only have 2^16th in the TCP port space, some are reserved, you may be using some, etc.</p>
			<p>Lets explore <code>setupHarness()</code> as this is where the automagic happens.  For testing the class <code>com.meschbach.psi.util.WebAppHarness</code> is a facade for around the various PSI components to provide a simplified interface. The first argument provides the harness with a factory for creating the web container, in this case Jetty.  The second argument is the name of the artifact, usually the maven <strong>artifactId</strong>.  The harness will attempt to locate the artifact under the directory (relative to the project base directory) <code class='path_name'>target/dependency/${artifact}*</code>.  The last argument is the context path the web application will be deployed too.</p>
			<p>The <code>harness.start()</code> method instructs the harness to realize our configuration by constructing the container, the locating and deploying the artifact to our context path.</p>
			<p>Now that we have configured our testing harness we can move on to testing.  First we should ensure our web application deploys.  We imported the nessecery utility functions from psi-util in the class RESTClient, so let us code our test.  Add the following to the class body.</p>
<pre>

    @Test
    public void testDeployment() {
        assertGetStatus(harness.getURL(), HttpStatusCode.Ok);
    }

</pre>
			<p>The <code>RESTClient.assertGetStatus(String,HttpStatusCode)</code> will issue an HTTP GET request to the specified resource and ensure the exepcted HTTP 200 OK response status code is returned.  The Salutator WAR contains an index file which will be returned as a result, but we are only interested in ensuring we recieved a 200.</p>
			<p>Now that we have a test to ensure our service actually works, lets ensure we recieve a 404 if our greeting doesn't exist.  Lets add the following test:</p>
			<pre>

    @Test(dependsOnMethods={"testDeployment"})
    public void test404OnNoGreeting() {
        assertGetStatus(getServiceURL() + "doesNotExist", HttpStatusCode.NotFound);
    }

			</pre>
			<p>The <code>Test</code> annotation parameter <code>dependsOnMethods</code> contains a list of methods which must be tested and pass prior to this test being invoked.  This will ensure we have successfully deployed our application before we attempt to run this test.  This time we are testing our Salutator's JAX-RS service, attempting to retrieve the resource 'doesNotExist', expecting our Salutator to return a 404 Not Found status.</p>
			<p>HTTP GET requests are fine and dandy, however they are a little boring.  Let's spice it with some ground executives!  Our Salutator will remember greetings if we issue an HTTP PUT request with the greeting to remember.  Following the HTTP/1.1 specification we expect an HTTP/1.1 201 Created response.  Bam!</p>
<pre>

    @Test(dependsOnMethods={"testDeployment"})
    public void testCreateGreeting() {
        assertPutStatus(getServiceURL() + "brian", "Hey!", HttpStatusCode.Created);
    }

</pre>
			<p>But wait!  Thats not all.  Let's ensure we can retrieve the value out of service with a GET.</p>
<pre>

    @Test(dependsOnMethods={"testCreateGreeting"})
    public void testGetSetGreeting() {
        final String resource = getServiceURL() + "mario";
        final String greeting = getServiceURL() + "Whats up slacker?";
        assertPutStatus(resource, greeting, HttpStatusCode.Created);
        assertGet(resource, greeting);
    }

</pre>
		</li>
		<li>
			<h3>Run the tests</h3>
			<p>To run the tests invoke the mvn lifecycle goal <code>test</code>.  Maven will then pull in all of the dependencies and run the test.</p>
		</li>
	</ol>
	<?php
});
?>
