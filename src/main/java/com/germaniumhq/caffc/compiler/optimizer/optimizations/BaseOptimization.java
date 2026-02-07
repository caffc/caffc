package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.model.Function;

public abstract class BaseOptimization {
    public abstract boolean optimize(Function function);
}
