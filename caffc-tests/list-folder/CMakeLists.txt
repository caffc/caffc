cmake_minimum_required(VERSION 4.0)
project(new_object C)

set(CMAKE_C_STANDARD 99)

add_definitions("-Wall" "-g")
file(GLOB SRC_FILES CONFIGURE_DEPENDS
        ${PROJECT_SOURCE_DIR}/target/caffc-c/*.c)

add_executable(list-folder ${SRC_FILES})
