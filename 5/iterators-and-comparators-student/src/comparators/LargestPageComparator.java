package comparators;

import java.util.Comparator;

public class LargestPageComparator implements Comparator<WebPageRecord> {
    @Override
    public int compare(WebPageRecord x, WebPageRecord y) {
        if (x.length != y.length) {
            return Integer.compare(y.length, x.length); // Larger length comes first
        }
        if (x.firstLine.length() != y.firstLine.length()) {
            return Integer.compare(y.firstLine.length(), x.firstLine.length()); // Larger firstLine length comes first
        }
        return x.URL.compareTo(y.URL); // Lexicographic ordering of URLs
    }
}