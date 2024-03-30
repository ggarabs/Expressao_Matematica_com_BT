import java.util.*;

public class ExpressionAnalyzer {
    private static ArrayList<String> LexicalAnalysis(String expression) {
        Tokenizer auxTokenizer = new Tokenizer(expression);

        ArrayList<String> tokens = auxTokenizer.tokenize();

        return tokens;
    }

    private static boolean SyntaxAnalysis(ArrayList<String> expression) {
        Stack<String> aux = new Stack<String>();

        /*
         * LÓGICA DOS ESTADOS: Se baseiam no último elemento analisado
         * ESTADO = 0 => ABRE PARENTESIS
         * ESTADO = 1 => OPERANDO
         * ESTADO = 2 => OPERADOR
         * ESTADO = 3 => FECHA PARENTESIS
        */

        int state = 0;

        for (String symbol : expression) { // percorre os tokens
            if (state != 0 && state != 3) { // Se o simbolo anterior não for um '(' ou ')'
                if (!Utils.isNumeric(symbol)) { // se o atual for um operador ou "(" ou ")"
                    if (!symbol.equals("(") && !symbol.equals(")") && state == 2)
                        return false; // operador depois de operador
                } else { // É operando
                    if (state == 1)
                        return false; // operando depois de operando
                }
            }

            if (symbol.equals("(")) {
                if (state == 1 || state == 3)
                    return false; // anterior é operando ou ')' e atual é '('
                aux.push(symbol);
                state = 0;
            } else if (symbol.equals(")")) {
                if (aux.isEmpty() || state == 2)
                    return false; // parentesis fechando sem ser aberto
                aux.pop();
                state = 3;
            } else if (Utils.isNumeric(symbol)){
                if(state == 3) return false; // operando depois de parentesis fechado
                state = 1;
            }else{
                if((symbol.equals("/") || symbol.equals("*")) && state == 0) return false; // operador / ou * precedido por ( ou nada
                state = 2;
            }
        }

        return aux.isEmpty() && state != 2;
    }

    public static boolean analyze(String expression) {
        ArrayList <String> lexicallyValid = LexicalAnalysis(expression);
        if (!lexicallyValid.isEmpty()) return SyntaxAnalysis(lexicallyValid);

        return false;
    }
}