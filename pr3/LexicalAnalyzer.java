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
    
    static Map<String, Integer> symbolTable = new LinkedHashMap<>();
    static int symbolIndex = 1;

    public static void main(String[] args) {
        try {
            File file = new File("/workspaces/DLP/pr3/sample.c");
            if (!file.exists()) {
                System.out.println("Error: sample.c not found. Please make sure the file exists.");
                return;
            }
    
            Scanner scanner = new Scanner(file);
            System.out.println("TOKENS");
            System.out.println("LEXICAL ERRORS");
    
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
                        System.out.println("Keyword: " + token);
                    } else if (operators.contains(token)) {
                        System.out.println("Operator: " + token);
                    } else if (token.length() == 1 && punctuation.contains(token.charAt(0))) {
                        System.out.println("Punctuation: " + token);
                    } else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) { // Valid identifier
                        System.out.println("Identifier: " + token);
                        if (!symbolTable.containsKey(token)) {
                            symbolTable.put(token, symbolIndex++);
                        }
                    } else if (token.matches("\\d+")) { // Integer constants
                        System.out.println("Constant: " + token);
                    } else if (token.matches("\\d+\\.\\d+")) { // Floating-point constants
                        System.out.println("Floating Constant: " + token);
                    } else if (token.startsWith("'") && token.endsWith("'") && token.length() == 3) { // Char literals
                        System.out.println("Character: " + token);
                    } else if (token.startsWith("\"") && token.endsWith("\"")) { // String literals
                        System.out.println("String: " + token);
                    } else {
                        System.out.println(token + " invalid lexeme");
                    }
                }
            }
    
            System.out.println("\nSYMBOL TABLE");
            System.out.println("ENTRIES");
            for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
                System.out.println("Identifier " + entry.getValue() + ") " + entry.getKey());
            }
    
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    
}