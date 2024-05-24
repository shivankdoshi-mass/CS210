package comparators;

import java.time.Instant;

public class WebPageRecord implements Comparable<WebPageRecord> {
    public final String URL;
    public final Instant lastRetrieved;
    public final int length;
    public final String firstLine;

    public WebPageRecord(String URL, Instant lastRetrieved, int length, String firstLine) {
        this.URL = URL;
        this.lastRetrieved = lastRetrieved;
        this.length = length;
        this.firstLine = firstLine;
    }

    // hashCode, equals, toString as before...

    @Override
    public int compareTo(WebPageRecord other) {
        int urlComparison = this.URL.compareTo(other.URL);
        if (urlComparison != 0) {
            return urlComparison; // URL comparison
        }
        return this.lastRetrieved.compareTo(other.lastRetrieved); // lastRetrieved comparison
    }
}