#!/usr/bin/env bash

set -e

CAFFC_JAR=$(find ../../target -name 'caffc-*.jar' | head -1)

if [ -z "$CAFFC_JAR" ]; then
    echo "Error: caffc jar not found in ../../target"
    exit 1
fi

cfc() {
    java -jar "$CAFFC_JAR" "$@"
}

build_test() {
    local test_folder=$1
    local test_name=$(basename "$test_folder")
    
    echo "#############################################################################"
    echo "# Building: $test_name"
    echo "#############################################################################"
    
    cd "$test_folder"
    
    rm -fr target
    mkdir -p target/caffc-c
    
    if [ -f "main.c" ]; then
        echo "Using -onefile mode"
        cfc -onefile main.c -o target/caffc-c $(find src -name '*.caffc')
    else
        cfc -o target/caffc-c/ $(find src -name '*.caffc')
    fi
    
    rm -fr target/caffc-o
    mkdir -p target/caffc-o
    
    for f in $(find target/caffc-c -name '*.c'); do
        c_file_name=$(basename "$f")
        o_file_name=target/caffc-o/${c_file_name%%.*}.o
        echo "cc: ${f} -> ${o_file_name}"
        gcc -Wall -g -ggdb -c "$f" -o "$o_file_name"
    done
    
    echo "ld: target/caffc-o/*.o -> target/main"
    gcc -g target/caffc-o/*.o -o target/main
    
    cd - > /dev/null
}

run_test() {
    local test_folder=$1
    local test_name=$(basename "$test_folder")
    
    build_test "$test_folder"
    
    echo "#############################################################################"
    echo "# Running: $test_name"
    echo "#############################################################################"
    
    cd "$test_folder"
    ./target/main
    cd - > /dev/null
}

print_success() {
    echo "#############################################################################"
    echo "# ALL GOOD"
    echo "#############################################################################"
}

run_all_tests() {
    for folder in */; do
        if [ -f "$folder/make.sh" ]; then
            run_test "$folder"
        fi
    done
}

main() {
    if [ $# -eq 0 ]; then
        run_all_tests
        print_success
        exit 0
    fi
    
    for test_name in "$@"; do
        if [ -d "$test_name" ] && [ -f "$test_name/make.sh" ]; then
            run_test "$test_name"
        else
            echo "Error: Test folder '$test_name' not found or invalid"
            exit 1
        fi
    done
    
    print_success
}

main "$@"
