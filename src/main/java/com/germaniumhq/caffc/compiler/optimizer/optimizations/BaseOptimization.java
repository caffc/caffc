package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.model.Function;

public interface BaseOptimization {
    boolean optimize(Function function);
}
