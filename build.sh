#!/usr/bin/env bash
set -euo pipefail

if ! command -v mise &> /dev/null; then
    echo "mise required: https://mise.jdx.dev/getting-started.html" >&2
    exit 1
fi

mise install
mvn clean package
