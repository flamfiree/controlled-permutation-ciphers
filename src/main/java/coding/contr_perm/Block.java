package coding.contr_perm;

public class Block {
    private String left;
    private String right;
    private String block;

    public String getBlock() {
        return block;
    }

    public String getBlock(String left, String right) {
        return left.concat(right);
    }

    public String getLeft() {
        return left;
    }
    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Block(String block){
        this.block = block;
        left = block.substring(0, block.length()/2);
        right = block.substring(block.length()/2, block.length());
    }

    // public Block(String block){
    //     left = block.substring(0, block.length()/2);
    //     right = block.substring(block.length()/2, block.length());
    // }
}
