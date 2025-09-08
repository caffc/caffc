#!/usr/bin/env bash

set -e

C_FLAGS="-std=c89 -g"
LD_FLAGS="-std=c89 -g"

echo "#############################################################################"
echo "# caffc compile"
echo "#############################################################################"

rm -fr target
mkdir -p target/caffc-c

cfc -o target/caffc-c/ $(find src -name \*.caffc)

echo "#############################################################################"
echo "# c compile"
echo "#############################################################################"

rm -fr target/caffc-o

mkdir -p target/caffc-o
for f in `find target/caffc-c -name \*.c`; do
  c_file_name=$(basename $f)
  o_file_name=target/caffc-o/${c_file_name%%.*}.o
  echo "cc: ${f} -> ${o_file_name}"
  gcc $C_FLAGS -c $f -o $o_file_name
done

echo "#############################################################################"
echo "# linking"
echo "#############################################################################"

echo ld: target/caffc-o/*.o "->" target/main
gcc $LD_FLAGS target/caffc-o/*.o -o target/main

