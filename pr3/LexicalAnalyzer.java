import java.io.*;
import java.util.*;

public class LexicalAnalyzer {
    static final Set<String> keywords = new HashSet<>(Arrays.asList(
        "int", "char", "return", "if", "else", "while", "for", "float", "double", "void"
    ));
    
    static final Set<String> operators = new HashSet<>(Arrays.asList(
        "=", "+", "-", "*", "/", "%", "++", "--", "==", "!=", "<=", ">=", "<", ">"
    ));
    
    static final Set<Character> punctuation = new HashSet<>(Arrays.asList(
        '(', ')', '{', '}', ';', ',', '\''
    ));
    
    static Map<String, Integer> symbolTable = new TreeMap<>();
    static int symbolIndex = 1;

    public static void main(String[] args) {
        try {
            File file = new File("/workspaces/DLP/pr3/sample.c");
            if (!file.exists()) {
                System.out.println("Error: sample.c not found. Please make sure the file exists.");
                return;
            }
    
            Scanner scanner = new Scanner(file);
            System.out.println("+-----------------+------------------------------+");
            System.out.println("| Token Type     | Lexeme                       |");
            System.out.println("+-----------------+------------------------------+");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
    
                // Ignore single-line comments
                if (line.startsWith("//")) continue;
    
                // Handle multi-line comments (e.g., /* ... */)
                if (line.contains("/*")) {
                    while (!line.contains("*/") && scanner.hasNextLine()) {
                        line = scanner.nextLine(); // Skip lines with multi-line comment
                    }
                    continue;
                }
    
                line = line.replaceAll("([(){};,])", " $1 "); // Adds spaces around punctuation
                String[] tokens = line.split("\\s+"); // Split by whitespace
    
                for (String token : tokens) {
                    if (token.isEmpty()) continue;
    
                    if (keywords.contains(token)) {
                        System.out.printf("| %-15s | %-28s |\n", "Keyword", token);
                    } else if (operators.contains(token)) {
                        System.out.printf("| %-15s | %-28s |\n", "Operator", token);
                    } else if (token.length() == 1 && punctuation.contains(token.charAt(0))) {
                        System.out.printf("| %-15s | %-28s |\n", "Punctuation", token);
                    } else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) { // Valid identifier
                        System.out.printf("| %-15s | %-28s |\n", "Identifier", token);
                        if (!symbolTable.containsKey(token)) {
                            symbolTable.put(token, symbolIndex++);
                        }
                    } else if (token.matches("\\d+")) { // Integer constants
                        System.out.printf("| %-15s | %-28s |\n", "Constant", token);
                    } else if (token.matches("\\d+\\.\\d+")) { // Floating-point constants
                        System.out.printf("| %-15s | %-28s |\n", "Floating Constant", token);
                    } else if (token.startsWith("'") && token.endsWith("'") && token.length() == 3) { // Char literals
                        System.out.printf("| %-15s | %-28s |\n", "Character", token);
                    } else if (token.startsWith("\"") && token.endsWith("\"")) { // String literals
                        System.out.printf("| %-15s | %-28s |\n", "String", token);
                    } else {
                        System.out.printf("| %-15s | %-28s |\n", "Invalid Lexeme", token);
                    }
                }
            }
    
            System.out.println("+-----------------+------------------------------+");
    
            // Sorting Symbol Table alphabetically by identifier
            System.out.println("\nSYMBOL TABLE");
            System.out.println("+-------------------+--------+");
            System.out.println("| Identifier       | Index  |");
            System.out.println("+-------------------+--------+");
            
            // Sorted order for symbol table
            for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
                System.out.printf("| %-17s | %-6d |\n", entry.getKey(), entry.getValue());
            }
            System.out.println("+-------------------+--------+");

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
