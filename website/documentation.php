<?php
require 'site.php';

renderTemplate("Documentation", function(){
	?>
	<div>
		<h2>What type of documentation are you searching for?</h2>
		<ul>
			<li><a href='#javadoc'>Javadoc</a></li>
			<li><a href='/examples/'>Examples</a></li>
			<li>Add more :-)</li>
		</ul>
	</div>
	<div id='javadoc'>
		<h3>Javadoc Documentation</h3>
		<p>Please choose a module</p>
		<ul>
			<li><a href='/documentation/core/'>Core</a></li>
			<li><a href='/documentation/jetty6/'>Jetty 6 implementation</a></li>
			<li><a href='/documentation/tomcat6/'>Tomcat 6 implementation</a></li>
			<li><a href='/documentation/util/'>Utilities</a></li>
		</ul>
	</div>
	<?php
});

?>
