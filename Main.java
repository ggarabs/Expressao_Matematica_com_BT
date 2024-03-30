import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Menu.show();

        BinaryTree expTree = new BinaryTree();
        String command, expression = "";
        boolean endOfProgram = false, expressionRead = false, tokensGenerated = false;

        // Tratamento de erros
        do {
            System.out.print("Digite um comando: ");
            command = input.next();
            input.nextLine();

            System.out.println();

            switch (command) {
                case "1":
                    System.out.printf("Digite uma expressão matemática: ");

                    expression = input.nextLine();

                    if (ExpressionAnalyzer.analyze(expression)) {
                        System.out.println("Expressão válida!");
                        expressionRead = true;
                    } else
                        System.out.println("Expressão inválida! Por favor, insira uma expressão válida.");

                    System.out.println();

                    break;
                case "2":
                    if (!expressionRead) {
                        System.out.println(
                                "Expressão ainda não lida! Digite 1 para inserir uma expressão na forma infixa");
                        System.out.println();
                        continue;
                    }

                    Tokenizer auxTokenizer = new Tokenizer(expression);
                    ArrayList<String> tokens = new ArrayList<String>(auxTokenizer.tokenize());
                    System.out.print("Gerando árvore binária da expressão aritmética ");

                    for (String token : tokens)
                        System.out.printf("%s ", token);
                    System.out.println("... ");

                    expTree.buildTree(tokens);
                    tokensGenerated = true;

                    System.out.println("Árvore binária construída!\n");

                    break;
                case "3":
                    if (!expressionRead) {
                        System.out.println(
                                "Expressão ainda não lida! Digite 1 para inserir uma expressão na forma infixa");
                        System.out.println();
                        continue;
                    }

                    if (!tokensGenerated) {
                        System.out.println(
                                "Árvore binária ainda não gerada! Digite 2 para gerar a àrvore a partir da expressão aritmética");
                        System.out.println();
                        continue;
                    }

                    System.out.println("Percorrendo a àrvore binária... ");

                    expTree.printNodeInformation();

                    break;
                case "4":

                    if (!expressionRead) {
                        System.out.println(
                                "Expressão ainda não lida! Digite 1 para inserir uma expressão na forma infixa");
                        System.out.println();
                        continue;
                    }

                    if (!tokensGenerated) {
                        System.out.println(
                                "Árvore binária ainda não gerada! Digite 2 para gerar a àrvore a partir da expressão aritmética");
                        System.out.println();
                        continue;
                    }

                    float result = expTree.calculate();

                    if(!Float.isNaN(result)) System.out.printf("O resultado da expressão é: %.2f\n\n", result);
                    else System.out.println("Operação inválida! Há alguma divisão por zero ou a árvore está vazia\n");

                    expressionRead = false;
                    tokensGenerated = false;
                    expTree.clear();

                    break;
                case "5":
                    endOfProgram = true;

                    break;
                default:
                    System.out.println("Opção inválida!");
                    Menu.show();
                    break;
            }

        } while (!endOfProgram);

        input.close();
    }
}
