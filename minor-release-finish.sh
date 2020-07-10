#!/bin/bash

#It gets aplication's name from the pom.xml for prefix version tag usage
PREFIX_VERSION_TAG=$(grep -oPm1 "(?<=<name>)[^<]+" pom.xml)

if [ -z "$PREFIX_VERSION_TAG" ] ; then
	echo '"<name>" not found in "pom.xml".'
	exit 1
fi

#It gets aplication's version from the pom.xml for prefix version tag usage
DEVELOPMENT_VERSION=$(grep -oPm1 "(?<=<version>)[^<]+" pom.xml)

if [ -z "$DEVELOPMENT_VERSION" ] ; then
	echo '"<version>" not found in "pom.xml".'
	exit 1
fi

#replace dot with dashes due to naming constraints
SNAPSHOT_TAG=${DEVELOPMENT_VERSION//\./-}
#transform to lower case
SNAPSHOT_TAG=${SNAPSHOT_TAG,,}
NOW=$(date +%Y%m%d%H%M%S%N)
SNAPSHOT_TAG=${SNAPSHOT_TAG/-snapshot/""}

# Get current branch
RELEASE_BRANCH=$(git branch | grep \* | cut -d ' ' -f2-)

echo '------------------- checkout branch -----------------'
git pull --all
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	echo "Aborted due to error while checking out branch"
	exit $EXIT_CODE
fi

bash compile.sh
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	git reset --hard  
	echo "Aborted due to error while compiling artifacts"
	exit $EXIT_CODE
fi

# change build date
NOW=$(date +%d\\/%m\\/%Y" "%H:%M)
sed -i "s/BUILDTIME =.*/BUILDTIME = \"$NOW\";/" src/main/java/br/com/xyinc/br/com/xyinc/domain/Version.java

git commit -a -m "Fechamento da versão: $DEVELOPMENT_VERSION"
git tag -a $SNAPSHOT_TAG -m "Fechamento da versão: $SNAPSHOT_TAG"
git push origin $SNAPSHOT_TAG

# MODIFICA VERSÃO PARA PROX

# Let's get the new patch version and increment to snapshot
IFS='.' read -a ARRAY_NEW_VERSION <<< "$DEVELOPMENT_VERSION"
NEW_VERSION_PATCH=$((ARRAY_NEW_VERSION[2]+1))

# Creating the snapshot version number
NEW_VERSION="${ARRAY_NEW_VERSION[0]}.${ARRAY_NEW_VERSION[1]}.$NEW_VERSION_PATCH-SNAPSHOT"

# validate the version number
if [ -z "$NEW_VERSION" ] ; then
    echo 'New version not defined"'
    exit $INCORRECT_USAGE
fi

bash changeversion.sh $NEW_VERSION $OUTPUT
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	git reset --hard
	echo "Aborted due to error while changing version"
	exit $EXIT_CODE
fi

echo "------------------- commit changes to new version  $NEW_VERSION -----------------"
git commit -a -m "Alterando para versao $NEW_VERSION"
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	git reset --hard
	echo "Aborted due to error while committing changes to new version"
	exit $EXIT_CODE
fi

echo "------------------- push commits  -----------------"
git push origin $RELEASE_BRANCH
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
	echo "Aborted due to error while pushing commits"
	exit $EXIT_CODE
fi

echo "$DEVELOPMENT_VERSION has been done successfully!"
