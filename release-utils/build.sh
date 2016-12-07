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
proj_ver=$2
proj_build=$3

ensure_set proj_name "Project name (arg 1) must be set"
ensure_set proj_ver "Project version (arg 2) must be set"
ensure_set proj_build "Project build (arg 3) must be set"

#
# Subversion information
#
svn_base="https://phunctional-system-integration.googlecode.com/svn"
svn_branches="$svn_base/branches"
svn_tags="$svn_base/tags"

#
# Project paths
#
proj_branch="$svn_branches/$proj_name-$proj_ver"
build_tag="$svn_tags/$proj_name-$proj_ver.$proj_build"

#
# Subversion copy
#
svn copy $proj_branch $build_tag -m "Tagging build $build_tag of series $proj_ver of project $proj_name"
