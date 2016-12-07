#!/bin/sh -e
#
# $HeadURL: https://meschbach.com/svn/websites/personal/trunk/com.meschbach.psi/install.sh $
# $Id: install.sh 402 2011-02-03 17:55:12Z meschbach $

################################################################################
# Configuration
################################################################################
#
# cfg_set(vname,default)
# Where
#	vname = to the variable name to set
#	default = the default value to set the variable too
# Description:
#	Sets the given variable to the given default value
#	if and only if the current value of the named variable
#	is not set.
#
cfg_set(){
	vname=$1
	default=$2

	eval v="\$$vname"
	if [ "x$v" = "x" ] ; then
		eval $vname="$default"
	fi
}

#
# Target directory
#
cfg_set target "~/websites/com.meschbach.psi/htdocs"

#
# Project subversion repository
#
cfg_set svn "svn"
cfg_set svn_base "http://phunctional-system-integration.googlecode.com/svn/trunk" 

#
# Maven
#
cfg_set mvn "/usr/org/apache-maven-2.2.1/bin/mvn"

################################################################################
# Functions
################################################################################
gen_page(){
	msg=$1
	src=$2
	dst=$target/$3

	echo -n "$msg"
	echo -n ".."
#	echo -n ".($dst)."
	php $src >$dst
	echo ".done." 
}

gen_javadoc(){
	msg=$1
	prj_name=$2
	doc_name=$3

	rm -fR $prj_name
	echo "Checking out '$msg'..."
	$svn export $svn_base/$prj_name
	echo "..($msg)..done."

	echo "Generating Javadoc for '$msg'"
	cd $prj_name 
	$mvn javadoc:javadoc
	mv target/site/apidocs $target/$3
	cd ..
	rm -fR $prj_name
	echo "..($msg)..done."
}

################################################################################
# Actual installation
################################################################################
# Root directory
########################################
gen_page "Welcome" index.php index.html

########################################
# Obtaining
########################################
mkdir -p $target/obtaining
gen_page "Obtaining" obtaining.php obtaining/index.html

########################################
# Contributing/Contributors
########################################
mkdir -p $target/contributing
gen_page "Contributing" contributing.php contributing/index.html

mkdir -p $target/contributors
gen_page "Contributors" contributors.php contributors/index.html

########################################
# Documentation
########################################
#
# Install the documentation landing page
#
mkdir -p $target/documentation
gen_page "Documentation" documentation.php documentation/index.html

#
# Pull the core module
#
if [ "x$NO_JAVADOC" = "x" ] ; then
	gen_javadoc "psi-core" psi documentation/core
	gen_javadoc "psi-jetty" psi-jetty documentation/jetty6
	gen_javadoc "psi-tomcat" psi-tomcat documentation/tomcat6
	gen_javadoc "psi-util" psi-util documentation/util
fi

########################################
# Examples
########################################
mkdir -p $target/examples
gen_page "Examples" examples.php examples/index.html

#
# Salutator
#
mkdir -p $target/examples/salutator
gen_page "Salutator" salutator.php examples/salutator/index.html

#
# DPrime
#
mkdir -p $target/examples/dprime
gen_page "DPrime" dprime.php examples/dprime/index.html
