#!/usr/bin/env bash

CURDIR=$(readlink -f "$(dirname "$0")/..")
cd $CURDIR

antlr caffc.g4 -package com.germaniumhq.caffc.generated -o src/main/java/com/germaniumhq/caffc/generated/
