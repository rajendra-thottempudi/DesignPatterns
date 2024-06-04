package Examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

abstract class BracketService {
    abstract char getOpenBracket(char ch);
    abstract boolean isValidOpen(char ch);
    abstract boolean isValidClose(char ch);
}

class RegularBracket extends BracketService {
    private final Map<Character, Character> mapping;
    private final Stack<Character> openBracket;

    public RegularBracket() {
        mapping = new HashMap<>();
        openBracket = new Stack<>();
    }

    public void addBracketPair(char open, char close) {
        openBracket.push(open);
        mapping.put(close, open);
    }

    public boolean isValidClose(char ch) {
        return mapping.containsKey(ch);
    }

    public char getOpenBracket(char ch) {
        return mapping.get(ch);
    }

    public boolean isValidOpen(char ch) {
        return openBracket.contains(ch);
    }
}

class ValidParanthesis {
    private final Stack<Character> stack;
    private final BracketService bracketService;

    public ValidParanthesis(BracketService bracketService) {
        this.stack = new Stack<>();
        this.bracketService = bracketService;
    }

    public boolean isValid(String input) {
        for (char ch : input.toCharArray()) {
            if (bracketService.isValidOpen(ch)) {
                stack.push(ch);
                continue;
            }

            if (stack.isEmpty() || !bracketService.isValidClose(ch) ||
                    bracketService.getOpenBracket(ch) != stack.peek()) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }
}

public class Main_ValidParenthesis {
    public static void main(String[] args) {
        RegularBracket bracketService = new RegularBracket();
        bracketService.addBracketPair('(', ')');
        bracketService.addBracketPair('{', '}');
        bracketService.addBracketPair('[', ']');
        bracketService.addBracketPair('A', 'B');
        ValidParanthesis validParanthesis = new ValidParanthesis(bracketService);

        String[] testCases = {"((])", "", "(())", "{[()]}", "{A)}", "ABABBA", "(AB)"};
        for (String test : testCases) {
            validParanthesis.clear();
            System.out.println("answer for string " + test + " is " + validParanthesis.isValid(test));
        }
    }
}

