public class OperandNode extends Node {
    OperandNode(String data, Node parent, Node left, Node right){
        super(data, parent, left, right);
    }

    OperandNode(String data){
        super(data, null, null, null);
    }

    OperandNode(){
        super("");
    }

    @Override
    public float visit(Node root){
        System.out.println("retornei " + root.getData());
        return Float.parseFloat(root.getData());
    }
}