/* FIXME: remove */
#include <stdlib.h>

#include "caffcgcpl.h"

/**
 * Create a pointer list.
 */
void caffc_gc_pointer_list_constructor(caffc_gc_pointer_list* _this, i32 default_capacity) {
    if (default_capacity <= 0) {
        default_capacity = 16;
    }

    _this->data = malloc(sizeof(ptr) * default_capacity);
    _this->_capacity = default_capacity;
    _this->len = 0;
}

/**
 * The pointer list destructors just frees the used memory.
 */
void caffc_gc_pointer_list_destructor(caffc_gc_pointer_list* _this) {
    if (_this->data) {
        free(_this->data);
        _this->data = null;
    }
}

/**
 * Add an item into the list. If the capacity is not enough, the
 * list will be expanded dynamically.
 */
void caffc_gc_pointer_list_add(caffc_gc_pointer_list* _this, ptr value) {
    if (_this->_capacity == _this->len) {
        if (_this->_capacity == 0) {
            _this->_capacity = 16;
        } else {
            _this->_capacity = _this->_capacity * 2;
        }

        _this->data = realloc(_this->data, sizeof(ptr) * _this->_capacity);
    }

    _this->data[_this->len] = value;
    _this->len++;
}

/**
 * Removes an item from the list. The last pointer from the list
 * will replace the removed index.
 */
ptr caffc_gc_pointer_list_remove(caffc_gc_pointer_list* _this, i32 index) {
    ptr result;

    if (index < 0 || index >= _this->len) {
        return null; /* FIXME: report this, since it's a bug? */
    }

    _this->len--;

    result = _this->data[index];

    /* it was the last item, we're done */
    if (index == _this->len) {
        return result;
    }

    /* we have still items, and we haven't removed the last item */
    if (_this->len) {
        _this->data[index] = _this->data[_this->len];
    }

    return result;
}

/**
 * Gets an item from the list.
 */
ptr caffc_gc_pointer_list_get(caffc_gc_pointer_list* _this, i32 index) {
    if (index < 0 || index >= _this->len) {
        return null;
    }

    return _this->data[index];
}

