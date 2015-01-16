#!/bin/bash

#
echo "+++++++++++++++++++++++++++++++++++++"
echo "+env.sh                              "
echo "+++++++++++++++++++++++++++++++++++++"

# environment var major.version
_var=`cat version.properties | grep major.version | cut -d= -f2`
echo ">get property major.version from version.properties"
echo "$_var"

# environment var minor.version
_var=`cat version.properties | grep minor.version | cut -d= -f2`
echo ">get property minor.version from version.properties"
echo "$_var"

# environment var revision
_var=`cat version.properties | grep revision | cut -d= -f2`
echo ">get property revision from version.properties"
echo "$_var"

# environment var build
_var=`cat version.properties | grep build= | cut -d= -f2`
echo ">get property build from version.properties"
echo "$_var"

# environment var build.label
_var=`cat version.properties | grep build.label | cut -d= -f2`
echo ">get property build.label from version.properties"
echo "$_var"

function __get_property()
{
	echo ">process $0 __get_property is executing..." > stdout
	echo ">get property $2 from $1" > stdout
	
	_property_file=$1
	_property=$2;
	_var=`cat "$_property_file" | grep "$_property" | cut -d= -f2`
	
	echo ">property $2 value: $_var" > stdout
	
	echo "$_var"
}

# environment var major.version
__get_property version.properties major.version

# environment var major.version
__get_major_version()
{
	echo ">process $0 __get_major_version is executing..." > stdout
	echo "$(__get_property version.properties major.version)"
}

# environment var major.version
__get_major_version 

# environment var minor.version
__get_minor_version()
{
	echo ">process $0 __get_minor_version is executing..." > stdout
	echo "$(__get_property version.properties minor.version)"
}

# environment var minor.version
__get_minor_version 

# environment var revision
__get_revision() 
{
	echo ">process $0 __get_revision is executing..." > stdout
	echo "$(__get_property version.properties revision)"
}

# environment var revision
__get_revision 

# environment var build
__get_build() 
{
	echo ">process $0 __get_build is executing..." > stdout
	echo "$(__get_property version.properties build=)"
}

# environment var build
__get_build 

# environment var build.label
__get_build_label() 
{
	echo ">process $0 __get_build_label is executing..." > stdout
	echo "$(__get_property version.properties build.label)"
}

# environment var build.label
__get_build_label 

# export
export __get_property
export __get_major_version
export __get_minor_version
export __get_revision
export __get_build
export __get_build_label