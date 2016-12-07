<?php
require 'site.php';

renderTemplate( "Contributors", function() {
	?>
	<div>
		<h2>Contributors</h2>
		<p>The following list contains the poeple or organizations who have contributed to the project and how they contributed.  If you have done something and you have feel left out feel free to tell us to update the page.  Looking for some work to get your name on this list?  <a href='/contributing/'>Check out the contributing page.</a></p>
	</div>
	<table>
		<thead>
			<tr>
				<th>Who</th>
				<th>Website</th>	
				<th>What</th>
				<th>From</th>
				<th>To</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td>Who</td>
				<td>Website</td>
				<td>What</td>
				<td>From</td>
				<td>To</td>
			</tr>
		</tfoot>
		<tbody>	
			<tr>
				<td>Mark Eschbach</td>
				<td><a href='http://meschbach.com/'>http://meschbach.com/</a></td>
				<td>Project Leader, Code Monkey, Originator</td>
				<td>September 2010</td>
				<td>Current</td>
			</tr>
			<tr>
				<td>Google</td>
				<td><a href='http://code.google.com/'>http://code.google.com/</a></td>
				<td>Project hosting, including RCS, Wiki, Issue Management.</td>
				<td>January 2011</td>
				<td>Current</td>
			</tr>
		</tbody>
	</table>
	<?php
});

?>
