<?php
require 'site.php';

renderTemplate( "Examples", function() {
	?>
	<div>
		<h2>Examples of PSI</h2>
		<ul>
			<li><a href='/examples/salutator/'>Salutator: Testing a simple JAX-RS application.</a></li>
			<li><a href='/examples/dprime/'>DPrime: Testing a distributed prime number calculator</a></li>
		</ul>
	</div>
	<?php
});
?>
