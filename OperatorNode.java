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
    public float visit(){ // Operador receberá especialização, portanto aqui só retorno NaN
        return Float.NaN;
    }
}