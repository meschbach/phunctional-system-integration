<?php
require "site.php";

renderTemplate("Obtaining", function(){
	?>
	<div>
		<h2>Obtaining PSI</h2>
		<p>Currently there are two methods of obtaining PSI:</p>
		<ul>
			<li><a href='#maven'>via Maven as a dependency. (easiest)</a></li>
			<li><a href='#compile'>compliation of the modules from source.</a></li>
		</ul>
	</div>
	<div id='maven'>
		<h3>Obtaining with Maven as a dependency</h3>
		<p>You must add Mark Eschbach's repository to your Maven configuration.  With a <code>pom.xml</code> file add the following:</p>
		<pre>
        &lt;repository&gt;
            &lt;id&gt;meschbach.com-oss&lt;/id&gt;
            &lt;url&gt;http://meschbach.com/dist/m2/oss&lt;/url&gt;
        &lt;/repository&gt;</pre>
		<p>Next add the dependency:</p>
		<pre>
        &lt;dependency&gt;
            &lt;groupId&gt;com.meschbach.psi&lt;/groupId&gt;
            &lt;artifactId&gt;psi&lt;/artifactId&gt;
            &lt;version&gt;2.2.0&lt;/version&gt;
        &lt;/dependency&gt;</pre>
		<p>And now you are good too start using PSI.  I recommend checking out the <?php siteLink('examples', 'examples'); ?> if you are not yet familiar with PSI.</p>
	</div>
	<div id='compile'>
		<h3>Compiling the from source</h3>
		<ol>
			<li>Check out the desired modules from the <a href='https://code.google.com/p/phunctional-system-integration/source/checkout'>Google Code repository</a>.</li>
			<li>For each desired module type <pre>mvn install</pre>.</li>
		</ol>
		<p>NOTE: Ideally there are unit tests and system tests for each module, and these should take somewhere between 0 to 10 minutes to run for each project.</p>
	</div>
	<?php
});
?>
