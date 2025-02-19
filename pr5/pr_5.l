%{
#include <stdio.h>
#include <string.h>

// Token categories
#define KEYWORD 1
#define IDENTIFIER 2
#define CONSTANT 3
#define STRING 4
#define PUNCTUATION 5
#define OPERATOR 6

void print_token(int type, char* token);
%}

/* Regular definitions */
DIGIT       [0-9]
LETTER      [a-zA-Z]
ID          {LETTER}({LETTER}|{DIGIT})*
NUMBER      {DIGIT}+|{DIGIT}*\.{DIGIT}+|{DIGIT}+\.{DIGIT}*
WHITESPACE  [ \t\n]+

%%

"auto"|"break"|"case"|"char"|"const"|"continue"|"default"|"do"|"double"|"else"|"enum"|"extern"|"float"|"for"|"goto"|"if"|"int"|"long"|"register"|"return"|"short"|"signed"|"sizeof"|"static"|"struct"|"switch"|"typedef"|"union"|"unsigned"|"void"|"volatile"|"while" {
    print_token(KEYWORD, yytext);
}

{NUMBER}    { print_token(CONSTANT, yytext); }
{ID}        { print_token(IDENTIFIER, yytext); }

\"([^\"\\]|\\.)*\"  { print_token(STRING, yytext); }  /* String literal */
\'([^\'\\]|\\.)*\'  { print_token(STRING, yytext); }  /* Character literal */

"+"|"-"|"*"|"*."|\."*"|"/"|"%"|"="|"<"|">"|"!"|"&"|"|"|"^"|"+="|"-="|"*="|"/="|"%="|"=="|"<="|">="|"!="|"&&"|"||"|"++"|"--" {
    print_token(OPERATOR, yytext);
}

"["|"]"|"("|")"|"{"|"}"|","|";" { print_token(PUNCTUATION, yytext); }

"//".*      { /* Single line comment - ignore */ }
"/*"([^*]|"*"+[^*/])*"*"+"/"  { /* Multi-line comment - ignore */ }

{WHITESPACE}    { /* Ignore whitespace */ }

.           { printf("Error: Unexpected character '%s'\n", yytext); }

%%

void print_token(int type, char* token) {
    switch(type) {
        case KEYWORD:
            printf("Keyword: %s\n", token);
            break;
        case IDENTIFIER:
            printf("Identifier: %s\n", token);
            break;
        case CONSTANT:
            printf("Constant: %s\n", token);
            break;
        case STRING:
            printf("String: %s\n", token);
            break;
        case PUNCTUATION:
            printf("Punctuation: %s\n", token);
            break;
        case OPERATOR:
            printf("Operator: %s\n", token);
            break;
    }
}

int main() {
    printf("C Language Lexical Analyzer\n");
    printf("Enter C code (Ctrl+D to end on Unix, Ctrl+Z on Windows):\n");
    printf("TOKENS:\n");
    yylex();
    return 0;
}

int yywrap() {
    return 1;
}