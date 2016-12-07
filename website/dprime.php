<?php
require 'site.php';

renderTemplate( "DPrime - Examples", function() {
	?>
	<div>
		<h2>PSI Example: DPrime</h2>
		<p>I&apos; workin' on it!</p>
	</div>
	<ol>
		<li>
			<h3>Create a new Maven project</h3>
			<p>Create a new Maven project with the group ID 'com.example', artifact ID 'dprime-sys-test', and version '1.0-SNAPSHOT'.</p>
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
						<th>Reason</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td>Group ID</td>
						<td>Artifact ID</td>
						<td>Version</td>
						<td>Reason</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td>com.meschbach.psi</td>
						<td>psi</td>
						<td>2.2.0</td>
						<td>Pulls in the testing harness (both Tomcat and Jetty) and the associated utilties.</td>
					</tr>
					<tr>
						<td>org.testng</td>
						<td>testng</td>
						<td>5.14.6</td>
						<td><a href='http://testng.org/'>TestNG</a> is my perfered testing framework.  Feel free to substitute JUnit if you prefer.</td>
					</tr>
				</tbody>
			</table>
		</li>
		<li>
			<h3>Code the tests</h3>
			<p>TODO: Document</p>
		</li>
		<li>
			<h3>Run the tests</h3>
			<p>TODO: Document</p>
		</li>
	</ol>
	<?php
});
?>
