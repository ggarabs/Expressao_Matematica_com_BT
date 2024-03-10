public class OperatorNode extends Node{
    OperatorNode(String data, Node parent, Node left, Node right){
        super(data, parent, left, right);
    }

    OperatorNode(String data){
        super(data, null, null, null);
    }

    OperatorNode(){
        super("");
    }

    @Override
    public float visit(Node root){
        System.out.println("entrei no operador " + root.getData());
        if(this.getData().equals("+")) return visit(this.getLeft()) + visit(this.getRight());
        else if(this.getData().equals("-")) return visit(this.getLeft()) - visit(this.getRight());
        else if(this.getData().equals("*")) return visit(this.getLeft()) * visit(this.getRight());
        return visit(this.getLeft())/visit(this.getRight());
    }
}