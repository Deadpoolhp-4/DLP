#include <stdio.h>

void add(int x, int y); // Function prototype

void mam() {
    long bs, da, hra, gs;

    // Take basic salary as input
    printf("Enter Basic Salary: ");
    scanf("%ld", &bs);

    // Calculate allowances
    da = bs * 0.40; // Assuming DA is 40% of basic salary
    hra = bs * 0.20; // Assuming HRA is 20% of basic salary
    gs = bs + da + hra; // Gross Salary = Basic + DA + HRA

    // Display salary slip
    printf("Basic Salary: %ld\n", bs);
    printf("DA: %ld\n", da);
    printf("HRA: %ld\n", hra);
    printf("Gross Salary: %ld\n", gs);
}

void add(int x, int y) {
    printf("Addition of %d and %d is %d\n", x, y, x + y);
}

int main() {
    int a, b;

    // Taking input for the add function
    printf("Enter two numbers: ");
    scanf("%d %d", &a, &b);

    // Function call
    add(a, b);

    // User-defined data type example (struct)
    struct student {
        int id;
        float cgpa;
    };

    struct student s;
    s.id = 101;
    s.cgpa = 8.7;

    // Printing student details
    printf("Student ID: %d\n", s.id);
    printf("Student CGPA: %.2f\n", s.cgpa);

    return 0;
}
