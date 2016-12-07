#!/bin/sh -e
#
# Taggs a build for a specific project and version
#

ensure_set() {
	eval r=\$${1}
	if [ "x$r" = "x" ] ; then
		echo $2
		exit 1
	fi
}

proj_name=$1
proj_tag_name=$2

if [ "x$3" = "x" ] ; then
	summary="--summarize"
fi

ensure_set proj_name "Project name (arg 1) must be set"
ensure_set proj_tag_name "Project version tag (arg 2) must be set"

#
# Subversion information
#
svn_base="http://phunctional-system-integration.googlecode.com/svn"
svn_trunk="$svn_base/trunk"
svn_tags="$svn_base/tags"

#
# Project paths
#
proj_trunk="$svn_trunk/$proj_name"
proj_tag="$svn_tags/$proj_name-$proj_tag_name"

#
# Subversion copy
#
svn diff $summary $proj_tag $proj_trunk
