#include <stdlib.h> /* FIXME: remove */

#include "caffcpt.h"
#include "caffcgcps.h"

void caffc_gc_pointer_set_add_to_index(caffc_gc_pointer_set* _this, caffc_ptr value, caffc_i32 index);

void caffc_gc_pointer_set_constructor(caffc_gc_pointer_set* _this, caffc_i32 default_capacity) {
    if (default_capacity <= 0) {
        default_capacity = 16;
    }

    _this->data = malloc(sizeof(caffc_ptr) * default_capacity);
    _this->_capacity = default_capacity;
    _this->len = 0;
}

void caffc_gc_pointer_set_destructor(caffc_gc_pointer_set* _this) {
    if (_this->data) {
        free(_this->data);
    }
}

/**
 * Add a pointer to the set.
 */
void caffc_gc_pointer_set_add(caffc_gc_pointer_set* _this, caffc_ptr value) {
    caffc_i32 start, end, index;

    if (!_this->len) {
        caffc_gc_pointer_set_add_to_index(_this, value, 0);
        return;
    }

    start = 0;
    end = _this->len - 1;

    /* binary search, however the addition is O(n). in the future this should be
       replaced with a hashmap */
    while (1) {
        index = (end - start) / 2 + start;

        if (_this->data[index] == value) {
            return;
        }

        if (start == end) {
            caffc_gc_pointer_set_add_to_index(_this, value, index);
            return;
        }

        if (value < _this->data[index]) {
            end = index - 1;
            continue;
        }

        if (value > _this->data[index]) {
            start = index + 1;
            continue;
        }

        /* unreachable */
    }
}

/**
 * Internal code to add a pointer to a given index.
 */
void caffc_gc_pointer_set_add_to_index(caffc_gc_pointer_set* _this, caffc_ptr value, caffc_i32 index) {
    caffc_i32 i;

    if (_this->_capacity == _this->len) {
        _this->_capacity <<= 1; /* multiply by 2 */
        _this->data = realloc(_this->data, _this->_capacity);
    }

    if (index < _this->len) {
        for (i = _this->len; i > index; i--) {
            _this->data[i] = _this->data[i - 1];
        }
    }

    _this->data[index] = value;
    _this->len++;
}

/**
 * Pop an item from the set, in our case it's always the last item.
 */
caffc_ptr caffc_gc_pointer_set_pop(caffc_gc_pointer_set* _this) {
    if (!_this->len) {
        return NULL;
    }

    _this->len--;

    return _this->data[_this->len];
}
