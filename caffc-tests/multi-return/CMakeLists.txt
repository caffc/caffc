cmake_minimum_required(VERSION 4.0)
project(new_object C)

set(CMAKE_C_STANDARD 99)

add_definitions("-Wall" "-g")
file(GLOB SRC_FILES CONFIGURE_DEPENDS
target/caffc-c/caffca.c
target/caffc-c/caffcgcpl.c
target/caffc-c/caffcgcps.c
target/caffc-c/caffc_io.c
target/caffc-c/caffcmem.c
target/caffc-c/caffcms.c
target/caffc-c/caffc_obj_arr.c
target/caffc-c/caffc_obj.c
target/caffc-c/caffc_program_constants.c
target/caffc-c/caffcstk.c
target/caffc-c/caffc_str.c
target/caffc-c/caffc_sys.c
target/caffc-c/caffc_tags.c
target/caffc-c/caffc_u8_arr.c
target/caffc-c/main_main.c
target/caffc-c/module_caffc.c
target/caffc-c/module_main.c
)

add_executable(new-object ${SRC_FILES})