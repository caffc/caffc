cmake_minimum_required(VERSION 4.0)
project(stack_dump C)

set(CMAKE_C_STANDARD 99)

add_executable(stack_dump target/caffc-c/*.c)