#!/bin/bash
#
#software version controll system
#
#@author lxy
#@version 1.0, 2008-3-16
#@since 1.0

source env.sh

#
echo "+++++++++++++++++++++++++++++++++++++"
echo "+scm.sh                              "
echo "+++++++++++++++++++++++++++++++++++++"

__get_major_version

function __sc_version()
{
	echo "process $0 __sc_version is executing..." > 1
	
	echo "+++++++++++++++++++++++++++++++++++++"
	echo ">orginal version information         "
	echo "+++++++++++++++++++++++++++++++++++++"
	
	_major_version=$(__get_major_version)
	echo ">major version: $_major_version"
	
	_minor_version=$(__get_minor_version)
	echo ">minor version: $_minor_version"
	
	revision=$(__get_revision)
	echo ">revision: $revision"
	
	build=$(__get_build)
	echo ">build: $build"
	
	_build_label=$(__get_build_label)
	echo ">build label: $_build_label"
	
	echo "+++++++++++++++++++++++++++++++++++++"
	echo ">version information         "
	echo "+++++++++++++++++++++++++++++++++++++"
	
	_major_version=$(__get_major_version)
	echo ">major version: $_major_version"
	
	_minor_version=$(__get_minor_version)
	echo ">minor version: $_minor_version"
	
	revision=$(__get_revision)
	echo ">revision: $revision"
	
	build=`expr ${build} + 1`
	echo ">build: $build"
	
	_build_label=$(__get_build_label)
	echo ">build label: $_build_label"
	
	echo "major.version=$_major_version" >> version.properties.tmp
	echo "minor.version=$_minor_version" >> version.properties.tmp
	echo "revision=$revision" >> version.properties.tmp
	echo "build=$build" >> version.properties.tmp
	echo "build.label=$_build_label" >> version.properties.tmp
	
	cat version.properties.tmp > version.properties
	rm version.properties.tmp
}

__sc_version