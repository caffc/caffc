struct io_data_Rectangle {
    ptr _caffc_type;
    ptr _caffc_moveto;

    // data fields to other CAFFC objects
    ptr start;
    ptr end;
};

struct _caffc_frame {
    _caffc_frame* prev;
    const char* name;
    const char* file;
    int line;
    int root_pointer_count; // root pointers
}

_caffc_frame* stack = NULL;


void other_function() {
    _caffc_frame* _caffc_current_frame = _caffc_start_frame("main", 1);

    _caffc_end_frame(_caffc_current_frame);
}

int main(int argc, const char** argv) {
    _caffc_frame* _caffc_current_frame = _caffc_start_frame("main", 0);


    return 0;
}
