#include <stdio.h>

typedef struct {
    int x;
    int y;
    void (*data)();
} A;

void a_data() {
    printf("a data called\n");
}

A a = {
    3, 4, a_data
};

typedef struct {
    int a;
    A* parent;
} B;

B b = {
    11,
    &a
};

int main(int this, char** argv) {
    printf("%d %d\n", a.x, a.y);
    return 0;
}

