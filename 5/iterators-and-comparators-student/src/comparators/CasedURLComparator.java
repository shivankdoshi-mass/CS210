package comparators;

import java.util.Comparator;

public class CasedURLComparator implements Comparator<WebPageRecord> {
    final boolean ignoreCase;

    public CasedURLComparator(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public int compare(WebPageRecord x, WebPageRecord y) {
        if (ignoreCase) {
            return x.URL.compareToIgnoreCase(y.URL); // Case-insensitive comparison
        } else {
            return x.URL.compareTo(y.URL); // Case-sensitive comparison
        }
    }
}