#!/usr/bin/env bash
set -euo pipefail

JAR_FILE="target/dfEditor-1.0.0-jar-with-dependencies.jar"

[ -f "$JAR_FILE" ] || { echo "JAR not found. Run ./build.sh first" >&2; exit 1; }

if command -v mise &> /dev/null; then
    export JAVA_HOME="$(mise where java)"
    export PATH="$JAVA_HOME/bin:$PATH"
fi

java -jar "$JAR_FILE"
