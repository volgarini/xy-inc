NEW_VERSION="$1"
OUTPUT="$2"

INCORRECT_USAGE=2

# Updates the version inside the sdl.properties file
# sed -i "s/generator.app.version=.*/generator.app.version=${NEW_VERSION}/" sdl.properties

# Check whether the mininum number of arguments was passed
if [ -z "$NEW_VERSION" ] ; then
  echo \
  "command usage:
     ./changeversion.sh <VERSION>
  "
    exit $INCORRECT_USAGE
fi

# Clear version removing SNAPSHOT
VERSION_CLEAN=${NEW_VERSION/-SNAPSHOT/}


# change version inside Version.java
sed -i "s%VERSAO =.*%VERSAO = \"${VERSION_CLEAN}\";%" src/main/java/br/com/xyinc/br/com/xyinc/domain/Version.java

# change build date
NOW=$(date +%d\\/%m\\/%Y" "%H:%M)
sed -i "s/BUILDTIME =.*/BUILDTIME = \"$NOW\";/" src/main/java/br/com/xyinc/br/com/xyinc/domain/Version.java

# Updates artifacts to the next development version
echo "------------------- change root pom version -----------------"
mvn org.codehaus.mojo:versions-maven-plugin:2.1:set -DgenerateBackupPoms=false -DnewVersion=$NEW_VERSION