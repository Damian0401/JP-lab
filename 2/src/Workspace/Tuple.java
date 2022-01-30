package Workspace;

public class Tuple {
    /**
     * Key
     */
    private final int key;
    /**
     * Value
     */
    private int value;

    /**
     * Constructor
     * @param key Key
     * @param value Value
     */
    Tuple(int key, int value){
        this.key = key;
        this.value = value;
    }

    /**
     * Get key
     * @return Key
     */
    public int getKey(){ return key; }

    /**
     * Get value
     * @return Value
     */
    public int getValue(){ return value; }

    /**
     * Set value
     * @param value Value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
