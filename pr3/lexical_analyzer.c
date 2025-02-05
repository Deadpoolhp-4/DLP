#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main() {
    char code[] = "int main() { int a = 5, 7H; char b = 'x'; return a + b; }";
    printf("C Code:\n%s\n", code);
    return 0;
}
