#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "caffco.h"

/*
 * Allocates an object with the given type.
 */
caffc_object_template* caffc_new(caffc_class_header* object_type) {
    if (!object_type) {
        return NULL;
    }

    caffc_object_template* result = malloc(object_type->_size);

    memset(result, 0, object_type->_size);
    result->_caffc_header.header = object_type;

    return result;
}

void caffc_string_constructor(caffc_string* _this, ptr data, i32 len) {
    _this->buffer = data;
    _this->len = len;
}

void caffc_string_display(caffc_string* _this) {
    printf("%s\n", _this->buffer);
}


int main(int argc, const char * argv[]) {
    caffc_string* str = (caffc_string*) caffc_new(&caffc_string_type);
    caffc_string_constructor(str, "abc", 4);
    caffc_string_display(str);

    return 0;
}
