module main

int main() {
    printf("yay");

    gen_value_int igen = new range(10); // FIXME
    while (igen.hasNext()) {
        io.out.print("number: "
    }

    printf("nay");
}

void simple(context* ctx) {
    switch (ctx.state) {
        case 0:
            if x < 3 {
                io.out.print("first");

                ctx.state = 1;
                ctx.has_value = 1; // yield 3
                ctx.value = 3;
                return;
            }
        case 1:
            io.out.print("next");

            ctx.has_value = 1; // yield 3
            ctx.value = 4;
            ctx.state = END;
            return;
        case END:
            ctx.has_value = 0;
            return;
    }
}

void range(context* ctx) {
    while (1) {
        switch (context.state) {
            case 0:
                context.i = 0;
                context.state = 1;
                continue;
            case 1:
                if (i < 5) {
                    context.state = 3;
                    continue;
                }

                context.state = END;
                continue;
            case 2:
                context.i++;
                context.state = 1;
                continue;
            case 3:
                if (context.i < end) {
                    context.state = 4;
                    continue;
                }
                context.state = 4;
                continue;
            case 4:
                ...
                context.state = 2;
                continue;
        }
    }
}

void range(context* ctx) {
    case 0:
        while i < end {
            ctx.has_value = 1; // yield 3
            ctx.value = 4;
            ctx.state = END;
        }
}


