#!/bin/bash

# Define filenames
C_FILE="lexical_analyzer.c"
JAVA_FILE="LexicalAnalyzer.java"

# Create C file
cat <<EOF > "$C_FILE"
#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main() {
    char code[] = "int main() { int a = 5, 7H; char b = 'x'; return a + b; }";
    printf("C Code:\\n%s\\n", code);
    return 0;
}
EOF

# Create Java file
cat <<EOF > "$JAVA_FILE"
import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {
    static Set<String> keywords = new HashSet<>(Arrays.asList("int", "char", "return"));
    static Set<String> operators = new HashSet<>(Arrays.asList("=", "+", "-", "*", "/"));
    static Set<String> punctuation = new HashSet<>(Arrays.asList("(", ")", "{", "}", ",", ";"));
    static Map<String, String> symbolTable = new LinkedHashMap<>();

    public static void main(String[] args) {
        String code = "int main() { int a = 5, 7H; char b = 'x'; return a + b; }";
        System.out.println("Java Code:\\n" + code);
    }
}
EOF

# Print file names
echo "Created files:"
echo "$C_FILE"
echo "$JAVA_FILE"

# Display the contents of both files
echo -e "\\n--- C Code ---"
cat "$C_FILE"

echo -e "\\n--- Java Code ---"
cat "$JAVA_FILE"
