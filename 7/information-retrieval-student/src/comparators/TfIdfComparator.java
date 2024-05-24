package comparators;

import documents.DocumentId;
import index.SearchEngine;
import java.util.Comparator;

public class TfIdfComparator implements Comparator<DocumentId> {
    private final SearchEngine searchEngine;
    private final String term;

    public TfIdfComparator(SearchEngine searchEngine, String term) {
        this.searchEngine = searchEngine;
        this.term = term;
    }

    @Override
    public int compare(DocumentId o1, DocumentId o2) {
        double tfidf1 = searchEngine.tfIdf(o1, term);
        double tfidf2 = searchEngine.tfIdf(o2, term);

        // Compare based on TF-IDF values only, no tie-breaker
        return Double.compare(tfidf2, tfidf1); // Descending order
    }
}
