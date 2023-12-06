package coding.contr_perm;

public class Decrypt {
    public static Block decrypt(Block block, String key1, String key2) {
        return step5(
                step4(
                        step3(
                                step2(
                                        step1(
                                                block, key1, key2)))));
    }

    /**
     * два XOR-а
     * @param block
     * @param key1
     * @param key2
     * @return 
     */
    private static Block step1(Block block, String key1, String key2) {
        // block.setLeft(LogicalOperations.xor(block.getLeft(), key1));
        // block.setRight(LogicalOperations.xor(block.getRight(), key2, 16));
        return block;
    }

    /**
     * блок управ перестановки, левый подблок - входной сигнал, правый блок - управляющий
     * @param block
     * @return
     */
    private static Block step2(Block block) {
        // block.setLeft(LogicalOperations.permutation(block.getLeft(), block.getRight()));
        return block;
    }

    /** сложение по модулю 2 (xor)
     * @param block
     * @return
     */
    private static Block step3(Block block) {
        // block.setRight(LogicalOperations.xor(block.getLeft(), block.getRight()));
        return block;
    }

    /**
     * блок управ перестановки, правый подблок - входной сигнал, левый блок - управляющий
     * @param block
     * @return
     */
    private static Block step4(Block block) {
        // block.setRight(LogicalOperations.permutation(block.getRight(), block.getLeft()));
        return block;
    }

    /**
     * сложение по модулю 2^16
     * @param block
     * @return
     */
    private static Block step5(Block block) {
        // block.setLeft(LogicalOperations.xor(block.getLeft(), block.getRight(), 16));
        return block;
    }
}
