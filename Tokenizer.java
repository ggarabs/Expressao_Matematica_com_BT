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
            while (Character.isWhitespace(currChar)) // se for um espaço, basta pegar o próximo caractere
                currChar = getNextChar();

            if (Character.isDigit(currChar)) { // se for algarismo
                sb.setLength(0);
                // enquanto o número tiver mais casas, ponto flutuante ou for separado por
                // espaço
                while (Character.isDigit(currChar) || currChar == '.' || Character.isWhitespace(currChar)) {
                    if (!Character.isWhitespace(currChar)) // armazeno no SB
                        sb.append(currChar);
                    currChar = getNextChar(); // pego o próximo
                }
                // se o numero for acompanhado de um sinal, incorporo o token anterior '+' ou
                // '-' no número e substituo no lugar do anterior
                if ((tokens.size() >= 2
                        && (tokens.get(tokens.size() - 1).equals("+") || tokens.get(tokens.size() - 1).equals("-"))
                        && tokens.get(tokens.size() - 2).equals("("))
                        || (tokens.size() > 0 && tokens.size() < 2 && (tokens.get(tokens.size() - 1).equals("+")
                                || tokens.get(tokens.size() - 1).equals("-")))) {
                    if (tokens.get(tokens.size() - 1).equals("-"))
                        tokens.set(tokens.size() - 1, "-" + sb.toString());
                    else
                        tokens.set(tokens.size() - 1, sb.toString());
                } else {    // se não tiver nenhum sinal antes, armazeno o número normalmente como token
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
            } else {    // se encontro um operador inválido, zero a lista e a retorno vazia
                tokens = new ArrayList<String>();
                isTokenizing = false;
            }
        }

        return tokens;
    }
}
