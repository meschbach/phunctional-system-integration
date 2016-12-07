<?php

function renderTemplate( $title, $renderingClosure ){
?>
<html>
	<head>
		<title><?php echo $title ?> - PSI - Mark Eschbach</title>
	</head>
	<body>
		<a href='#page_content' class='501s_link'>Skip To Content</a>
		<h1>PSI</h1>
		<h2><?php echo $title; ?></h2>
		<ul class='site_nav'>
			<li><a href='/'>Welcome</a></li>
			<li><a href='/obtaining/'>Obtaining</a></li>
			<li><a href='/contributing/'>Contributing</a></li>
			<li><a href='/contributors/'>Contributors</a></li>
			<li><a href='/examples/'>Examples</a></li>
			<li><a href='/documentation/'>Documentation</a></li>
			<li><a href='http://code.google.com/p/phunctional-system-integration/issues/list'>Bugs</a></li>
			<li><a href='http://code.google.com/p/phunctional-system-integration'>Google Code Project</a></li>
		</ul>
		<div id='page_content'>
			<?php $renderingClosure(); ?>
		</div>
		<?php injectGA(); ?>
	</body>
</html>
<?php
}

function injectGA(){
?>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-3786744-4']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<?php
}

function siteLink( $link, $text ){
	$links = array();
	$links['examples'] = '/examples/';

	echo "<a href='".$links[$link]."'>".$text."</a>";
}

?>
