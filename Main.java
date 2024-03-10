import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Menu.show();

        BinaryTree expTree = new BinaryTree();
        String command, expression = "";
        boolean endOfProgram = false;

        // Adicionar flags para induzir o fluxo de funcionamento do programa
        // Tratamento de erros
        // Estética do programa
        do {
            System.out.print("Digite um comando: ");
            command = input.next();
            input.nextLine();

            switch (command) {
                case "1":
                    System.out.printf("Digite uma expressão matemática: ");

                    expression = input.nextLine();

                    if (ExpressionAnalyzer.analyze(expression)) {
                        System.out.println("Expressão válida!");
                    } else
                        System.out.println("Expressão inválida! Por favor, insira uma expressão válida.");

                    break;
                case "2":
                    Tokenizer auxTokenizer = new Tokenizer(expression);
                    ArrayList<String> tokens = new ArrayList<String>(auxTokenizer.tokenize());
                    System.out.print("Gerando árvore binária da expressão aritmética: ");

                    for(String token: tokens) System.out.printf("%s ", token);
                    System.out.println("... ");

                    expTree.buildTree(tokens);

                    System.out.println("Árvore binária construída!");

                    break;
                case "3":
                    System.out.printf("Pré-Ordem: ");
                    expTree.preOrderTraversal(expTree.getRoot());
                    System.out.println();

                    System.out.printf("Em Ordem: ");
                    expTree.inOrderTraversal(expTree.getRoot());
                    System.out.println();

                    System.out.printf("Pós-Ordem: ");
                    expTree.postOrderTraversal(expTree.getRoot());
                    System.out.println();

                    break;
                case "4":
                    System.out.printf("O resultado da expressão é: %.2f\n", expTree.getRoot().visit(expTree.getRoot()));
                
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
