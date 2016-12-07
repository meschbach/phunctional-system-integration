<?php
require "site.php";

renderTemplate("Welcome", function(){
	?>
	<div>
		<h2>What is PSI?</h2>
		<p>The Phunctional System Integration (PSI) framework provides a testing harness for Java Web Applications, allowing for your unit tests to dynamically configure your Servlet container and deploy your Web Application.  PSI doesn't require an external installation, a running external service to be running on a well known port, or any modifications to your Web Application.</p>
		<p>Currently PSI implements the components required to use Jetty 6 and Tomcat 6.  In the future we hope to add additional Servlet Containers and provide a similar set of features for Enterprise Systems.</p>
		<p>PSI is agnostic to your testing framework, with the goal of using the Java 5 <code class='java'>assert</code> keyword.  The two major testing frameworks, TestNG and JUnit, are both supported.  If you write your own or use another testing system then PSI should work as long as you catch <code class='java'>java.lang.AssertionError</code>.</p>
	</div>
	<div>
		<h3>How is PSI different from Selenium?</h3>
		<p>PSI's scope is to manage embedded and dynamically deployed Servlet Container, where Selenium's tests the Human-Computer HTML Interface (HCHI) produced by an application deployed within a Servlet Container.  If you were testing with Selenium then you could setup PSI to deploy the application prior to each set of Selenium tests.</p>
	</div>
	<div>
		<h3>How is PSI different from Jakarta Cactus?</h3>
		<p>The problem domain of Jakarta Cactus overlaps with that of PSI, in terms of allowing automated testing of web applications.  Jakarta Cactus is an excellent product, however Cactus requires a modified Servlet Container for testing.  PSI chooses to simplify the process by providing a pragmatic interface, providing an in-process application container.  An in-process container eases debugging becuase you don't have to cross the process boundry.</p>
	</div>
	<div>
		<h4>What is the current version of the artifacts?</h4>
		<p>As of tonight, March 10th, 2011 the artifacts are at the current verison:</p>
		<table>
			<thead>
				<tr>
					<th>GroupId</th>
					<th>ArtifactId</th>
					<th>Type</th>
					<th>Version</th>
					<th>Last Release Date</th>
				</tr>
			</thead>
			<tbody>
<?php
	$relDate = "march 10th, 2011";
	$pgid = "com.meschbach.psi";
	$cgid = "com.meschbach.psi";
	$rel = "2.4";
	$projects[] = array($pgid,"psi", 		"jar",$rel, $relDate);	
	$projects[] = array($pgid,"psi-build",		"pom",$rel, $relDate);
	$projects[] = array($cgid,"cise-oss",		"jar",$rel, $f7);
	$projects[] = array($pgid,"echo-webapp",	"war",$rel, $relDate);
	$projects[] = array($pgid,"psi-core",		"jar",$rel, $relDate);
	$projects[] = array($pgid,"psi-util",		"jar",$rel, $relDate);
	$projects[] = array($pgid,"psi-container-compliance", "jar",$rel, $relDate);
	$projects[] = array($pgid,"psi-jetty6",		"jar",$rel, $relDate);
	$projects[] = array($pgid,"psi-jetty7",		"jar",$rel, $relDate);
	$projects[] = array($pgid,"psi-tomcat6",	"jar",$rel, $relDate);
	$projects[] = array($pgid,"salutator", 		"war",$rel, $relDate);
	$projects[] = array($pgid,"salutator-test",	"jar", $rel, $relDate);

	foreach( $projects as $project ){
		echo "<tr>";
			echo "<td>".$project[0]."</td>";
			echo "<td>".$project[1]."</td>";
			echo "<td>".$project[2]."</td>";
			echo "<td>".$project[3]."</td>";
			echo "<td>".$project[4]."</td>";
		echo "</tr>";
	}
?>
			</tbody>
		</table>
	</div>
	<?php
});
?>
