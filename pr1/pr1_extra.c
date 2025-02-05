// Code to accept a*abb string

#include <stdio.h>
#include <string.h>

int main() {
    char string[10];
        int len1;
            
                printf("Enter String: ");
                    scanf("%s", string);
                        
                            len1 = strlen(string);
                                
                                    if (len1 < 3) { 
                                            printf("Invalid String.\n");
                                                    return 0;
                                                        }
                                                            
                                                                if (string[len1 - 1] != 'b' || string[len1 - 2] != 'b') {
                                                                        printf("Invalid String.\n");
                                                                                return 0;
                                                                                    }
                                                                                        
                                                                                            for (int i = 0; i < len1 - 2; i++) {
                                                                                                    if (string[i] != 'a') {
                                                                                                                printf("Invalid String.\n");
                                                                                                                            return 0;
                                                                                                                                    }
                                                                                                                                        }
                                                                                                                                            
                                                                                                                                                printf("String accepted.\n");
                                                                                                                                                    return 0;
                                                                                                                                                    }