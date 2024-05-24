package hamspam;

public class HamSpam {
    private final int hamNumber;
    private final int spamNumber;

    public HamSpam(int hamNumber, int spamNumber) {
        this.hamNumber = hamNumber;
        this.spamNumber = spamNumber;
    }

    public String getValue(int n) {
        boolean isHam = (n % hamNumber == 0);
        boolean isSpam = (n % spamNumber == 0);

        if (isHam && isSpam) {
            return "hamspam";
        } else if (isHam) {
            return "ham";
        } else if (isSpam) {
            return "spam";
        } else {
            return Integer.toString(n);
        }
    }

    public String[] getValues(int start, int end) {
        String[] values = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            values[i - start] = getValue(i);
        }
        return values;
    }
}
