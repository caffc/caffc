
CURDIR=$(readlink -f "$(dirname "$0")")

pushd $CURDIR/..
mvn clean install
util/generate-reflect-config.py \
  --jar-file target/caffc-*.jar \
  --out-file src/main/resources/META-INF/native-image/reflect-config.json

$VERSION=$(cat BUILD | grep 'version =' | cut -f2 -d\")

echo "Ensure the version is the next version to release (BUILD + pom.xml)."
echo "Current version is: $VERSION"
read

$VERSION=$(cat BUILD | grep 'version =' | cut -f2 -d\")
git commit -a -m "deps upgrade" # it's fine if there's no changes and it fails
git tag -m "$VERSION" $VERSION

git push
git push --tags

plz build :caffc-java.tgz
plz build :caffc-native.tgz