#!/bin/bash

# Clean project
mvn clean
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	git reset --hard  
	echo "Aborted due to error while cleaning project"
	exit $EXIT_CODE
fi

# Compile project
mvn compile -DskipTests
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	git reset --hard  
	echo "Aborted due to error while compiling project"
	exit $EXIT_CODE
fi
