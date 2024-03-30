import java.util.*;

public class Tokenizer {
    private char[] input;
    private int index;

    public Tokenizer(String exp) {
        input = exp.toCharArray();
        index = 0;
    }

    private char getNextChar() {
        if (index >= input.length)
            return '\0';
        return input[index++];
    }

    public ArrayList<String> tokenize() {
        ArrayList<String> tokens = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        char currChar = getNextChar();

        boolean isTokenizing = true;
        while (isTokenizing) {
            while (Character.isWhitespace(currChar))
                currChar = getNextChar();

            if (Character.isDigit(currChar)) {
                sb.setLength(0);
                while (Character.isDigit(currChar) || currChar == '.' || Character.isWhitespace(currChar)) {
                    if (!Character.isWhitespace(currChar))
                        sb.append(currChar);
                    currChar = getNextChar();
                }
                if ((tokens.size() >= 2
                        && (tokens.get(tokens.size() - 1).equals("+") || tokens.get(tokens.size() - 1).equals("-"))
                        && tokens.get(tokens.size() - 2).equals("("))
                        || (tokens.size() > 0 && tokens.size() < 2 && (tokens.get(tokens.size() - 1).equals("+")
                                || tokens.get(tokens.size() - 1).equals("-")))) {
                    if (tokens.get(tokens.size() - 1).equals("-"))
                        tokens.set(tokens.size() - 1, "-" + sb.toString());
                    else
                        tokens.set(tokens.size() - 1, sb.toString());
                } else {
                    tokens.add(sb.toString());
                }
            } else if (currChar == '+') {
                tokens.add("+");
                currChar = getNextChar();
            } else if (currChar == '-') {
                tokens.add("-");
                currChar = getNextChar();
            } else if (currChar == '*') {
                tokens.add("*");
                currChar = getNextChar();
            } else if (currChar == '/') {
                tokens.add("/");
                currChar = getNextChar();
            } else if (currChar == '(') {
                tokens.add("(");
                currChar = getNextChar();
            } else if (currChar == ')') {
                tokens.add(")");
                currChar = getNextChar();
            } else if (currChar == '\0') {
                isTokenizing = false;
            } else {
                tokens = new ArrayList<String>();
                isTokenizing = false;
            }
        }

        return tokens;
    }
}
