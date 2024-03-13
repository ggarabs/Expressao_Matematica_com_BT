public class OperatorNode extends Node {
    OperatorNode(String data, Node parent, Node left, Node right) {
        super(data, parent, left, right);
    }

    OperatorNode(String data) {
        super(data, null, null, null);
    }

    OperatorNode() {
        super("");
    }

    @Override
    public float visit() {
        String operator = this.getData();
        Float left = this.getLeft().visit(), right = this.getRight().visit(), answer = 0.0f;

        switch (operator) {
            case "+":
                answer = left + right;
                break;
            case "-":
                answer = left - right;
                break;
            case "*":
                answer = left * right;
                break;
            case "/":
                try {
                    answer = left / right;
                } catch (ArithmeticException err) {
                    System.out.println("Divisão por zero");
                }
                break;
            default:
                System.out.println("Operador inválida!");
                break;
        }
        return answer;
    }
}