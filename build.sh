#!/usr/bin/env bash
set -euo pipefail

if ! command -v mise &> /dev/null; then
    echo "mise required: https://mise.jdx.dev/getting-started.html" >&2
    exit 1
fi

mise install
export JAVA_HOME="$(mise where java)"
export PATH="$JAVA_HOME/bin:$PATH"
ant -Dplatforms.JDK_1.6.home="$JAVA_HOME" -Djava.executable="$JAVA_HOME/bin/java" clean jar
