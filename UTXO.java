//Unspent Transaction Output
import java.util.Arrays;

public class UTXO implements Comparable<UTXO> {

    /** Hash of the transaction from which this UTXO originates */
    private byte[] txHash;

    /** Index of the corresponding output in said transaction */
    private int index;

    /**
     * Creates a new UTXO corresponding to the output with index <index> in the transaction whose
     * hash is {@code txHash}
     */
    public UTXO(byte[] txHash, int index) {
        this.txHash = Arrays.copyOf(txHash, txHash.length);
        this.index = index;
    }

    /** @return the transaction hash of this UTXO */
    public byte[] getTxHash() {
        return txHash;
    }

    /** @return the index of this UTXO */
    public int getIndex() {
        return index;
    }

    /**
     * Compares this UTXO to the one specified by {@code other}, considering them equal if they have
     * {@code txHash} arrays with equal contents and equal {@code index} values
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }

        UTXO utxo = (UTXO) other;
        byte[] hash = utxo.txHash;
        int in = utxo.index;
        if (hash.length != txHash.length || index != in)
            return false;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != txHash[i])
                return false;
        }
        return true;
    }

    /**
     * Simple implementation of a UTXO hashCode that respects equality of UTXOs // (i.e.
     * utxo1.equals(utxo2) => utxo1.hashCode() == utxo2.hashCode())
     */
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + index;
        hash = hash * 31 + Arrays.hashCode(txHash);
        return hash;
    }

    /** Compares this UTXO to the one specified by {@code utxo} */
    public int compareTo(UTXO utxo) {
        byte[] otherHash = utxo.txHash;
        int otherIndex = utxo.index;
        if (otherIndex > index)
            return -1;
        else if (otherIndex < index)
            return 1;
        else {
            int myHashLength = txHash.length;
            int otherHashLength = otherHash.length;
            if (otherHashLength > myHashLength)
                return -1;
            else if (otherHashLength < myHashLength)
                return 1;
            else {
                for (int i = 0; i < myHashLength; i++) {
                    if (otherHash[i] > txHash[i])
                        return -1;
                    else if (otherHash[i] < txHash[i])
                        return 1;
                }
                return 0;
            }
        }
    }
}
