version = "0.0.3"

subrepo(
  name="gbs",
  path="/home/raptor/projects/gbs/",
)

subinclude("@gbs//toolchain/java:java.plz")
subinclude("@gbs//util:util.plz")

ge_java_maven_fatjar(
  name="caffc.jar",
  maven_jar="target/caffc-*.jar",
  out="caffc.jar",
  help="""\
Fat jar distribution (a runnable jar that includes all its dependencies) for caffc.

This is the preferred way to use `caffc` since it won't have issues with
reflection, or anything else that GraalVM usually has. All the integration
tests as well are ran against this jar.
"""
)

ge_java_native_binary(
  name="caffc",
  deps=[":caffc.jar"],
  out="caffc",
  help="caffc native binary (native via graalvm)"
)

ge_filegroup(
  name="templates",
  srcs=glob(["templates/**/*"]),
)

ge_genrule(
  name="caffc-native.tgz",
  cmd=dedent(f"""\
    mkdir -p target/bin
    cp $(location :caffc) target/bin
    cp -R ./templates target/

    cd target
    tar -zcf ../caffc-native-{version}.tar.gz .
  """),
  out=f"caffc-native-{version}.tar.gz",
  deps=[":caffc", ":templates"],
  help=dedent("""\
    caffc compiler + templates - native binary tgz release
  """),
)

ge_filegroup(
  name="java-launcher.sh",
  srcs=["bin/caffc"],
  help=dedent("""\
    For the Java version, this is the shell script launcher that allows us to call `caffc` in the command line.
  """),
)

ge_genrule(
  name="caffc-java.tgz",
  cmd=dedent(f"""\
    mkdir -p target/bin
    cp $(location :caffc.jar) target/bin
    cp bin/caffc target/bin
    cp -R ./templates target/

    cd target
    tar -zcf ../caffc-java-{version}.tar.gz .
  """),
  out=f"caffc-java-{version}.tar.gz",
  deps=[":caffc.jar", ":java-launcher.sh", ":templates"],
  help=dedent("""\
    caffc compiler + templates - java fatjar tgz release
  """),
)