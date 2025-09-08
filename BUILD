version = "0.0.1"

subrepo(
  name="gbs",
  path="/home/raptor/projects/gbs/",
)

subinclude("@gbs//toolchain/java:java.plz")
subinclude("@gbs//util:util.plz")

ge_java_maven_fatjar(
  name="caffc-jar",
  maven_jar="target/caffc-*.jar",
)

ge_java_native_binary(
  name="caffc",
  deps=[":caffc-jar"],
  out="caffc",
)

filegroup(
  name="templates",
  srcs=glob(["src/main/templates/**/*"]),
)

ge_genrule(
  name="caffc-native-tgz",
  cmd=f"""
    mkdir -p target/bin
    cp $(location :caffc) target/bin
    cp -R src/main/templates target/

    cd target
    tar -zcf ../caffc-native-{version}.tar.gz .
  """,
  out=f"caffc-native-{version}.tar.gz",
  deps=[":caffc", ":templates"],
)

filegroup(
  name="java-launcher",
  srcs=["bin/caffc"],
)

ge_genrule(
  name="caffc-java-tgz",
  cmd=f"""
    mkdir -p target/bin
    cp $(location :caffc-jar) target/bin
    cp bin/caffc target/bin
    cp -R src/main/templates target/

    cd target
    tar -zcf ../caffc-java-{version}.tar.gz .
  """,
  out=f"caffc-java-{version}.tar.gz",
  deps=[":caffc-jar", ":java-launcher", ":templates"],
)
