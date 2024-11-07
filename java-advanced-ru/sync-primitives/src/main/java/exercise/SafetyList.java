package exercise;

class SafetyList {
    // BEGIN
    private int size = 0;
    private int[] arr = new int[10];

    private void increaseSize() {
        int[] newArr = new int[(arr.length * 3) / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    public synchronized void add(int elem) {
        if (arr.length == size) {
            increaseSize();
        }

        arr[size] = elem;
        size++;
    }

    public int get(int index) {
        if (index >= size) {
            throw new RuntimeException("Index " + index + " is out of list size");
        }

        return arr[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
