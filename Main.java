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

                    if (ExpressionAnalyzer.analyze(expression)) { // analiso a expressão para se prosseguir se for
                                                                  // válida
                        System.out.println("Expressão válida!");
                        expressionRead = true;
                    } else
                        System.out.println("Expressão inválida! Por favor, insira uma expressão válida.");

                    System.out.println();

                    break;
                case "2":
                    if (!expressionRead) { // só é possível criar a árvore se a expressão for lida e for correta
                        System.out.println(
                                "Expressão ainda não lida! Digite 1 para inserir uma expressão na forma infixa");
                        System.out.println();
                        continue;
                    }

                    // divido a expressão em tokens
                    Tokenizer auxTokenizer = new Tokenizer(expression);
                    ArrayList<String> tokens = new ArrayList<String>(auxTokenizer.tokenize());
                    System.out.print("Gerando árvore binária da expressão aritmética ");

                    for (String token : tokens)
                        System.out.printf("%s ", token);
                    System.out.println("... ");

                    expTree.buildTree(tokens); // e construo a BT a partir deles
                    tokensGenerated = true;

                    System.out.println("Árvore binária construída!\n");

                    break;
                case "3":
                    if (!expressionRead) { // só é possível exibir uma BT de uma expressão que foi digitada, analisada e
                                           // armazenada previamente
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
                    // só é possível calcular o resultado de uma BT de uma expressão que foi
                    // digitada, analisada e armazenada previamente
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

                    float result = expTree.calculate();     // calculo o resultado da expressão

                    if (!Float.isNaN(result))   // se ela gerar um resultado válido, imprimo o resultado
                        System.out.printf("O resultado da expressão é: %.2f\n\n", result);
                    else
                        System.out.println("Operação inválida! Há alguma divisão por zero ou a árvore está vazia\n");

                    // restauro a configuração inicial para inserir e calcular outras expressões
                    expressionRead = false;
                    tokensGenerated = false;
                    expTree.clear();

                    break;
                case "5":
                    endOfProgram = true;    // encerro o programa

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