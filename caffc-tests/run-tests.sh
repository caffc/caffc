#!/usr/bin/env bash
#
set -e
CURDIR=$(readlink -f "$(dirname "$0")")

run_single_test() {
    local test_folder=$1

    echo "######### $test_folder"
    cd $test_folder
    ./make.sh
    target/main
    cd $CURDIR
}

run_local_folders_as_tests() {
    for file_name in `ls`; do
        if [ ! -d $file_name ]; then
            # not a folder == not a test
            continue
        fi

        run_single_test $file_name
    done
}

print_success() {
    echo "#############################################################################"
    echo "# ALL GOOD"
    echo "#############################################################################"
}

main() {
    if [[ "$1" == "" ]]; then
        run_local_folders_as_tests
        print_success
        exit 0
    fi # [[ "$1" == "" ]]

    for test_name in $@; do
        run_single_test $test_name
    done

    print_success
}

main "$@"

