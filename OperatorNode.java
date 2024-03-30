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
    public float visit(){
        return Float.NaN;
    }
}