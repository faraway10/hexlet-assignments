package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String sequence;
    public ReversedSequence(String sequence) {
        this.sequence = new StringBuilder(sequence).reverse().toString();
    }

    public int length() {
        return sequence.length();
    }

    public char charAt(int index) {
        return sequence.charAt(index);
    }

    public String subSequence(int start, int end) {
        return sequence.substring(start, end);
    }

    @Override
    public String toString() {
        return sequence;
    }
}
// END
