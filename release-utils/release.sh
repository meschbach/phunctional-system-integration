#!/bin/sh -e
#
# This script eases the process of branching and tagging a project within the repository
#

project_name=$1
project_version=$2

if [ "x$project_name" = "x" ]; then
	echo "Project name not set (arg 1) "
	exit -1
fi

if [ "x$project_version" = "x" ]; then
	echo "Project version not set (arg 2)"
	exit -2
fi

#
# Establish our base repository
#
svn_base="https://phunctional-system-integration.googlecode.com/svn"
svn_trunk="$svn_base/trunk"
svn_branches="$svn_base/branches"

proj_trunk="$svn_trunk/$project_name"
proj_b="$project_name-$project_version"
proj_branch="$svn_branches/$proj_b"
proj_tag="$svn_tags/$proj_b.0"

#
# Copy the project
#
svn copy "$proj_trunk" "$proj_branch" -m "Branching $project_name for maintaince of the $project_version series"
./build.sh $proj_name $proj_version 0

echo "Edit metadata for release."
