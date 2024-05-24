package index;

import comparators.TfIdfComparator;
import documents.DocumentId;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class SearchEngine {
    // Example data structures
    private Map<DocumentId, Map<String, Integer>> documentTermFrequency;
    private Map<String, Set<DocumentId>> termDocumentIndex;
    private Set<DocumentId> documentSet;

    public SearchEngine() {
        documentTermFrequency = new HashMap<>();
        termDocumentIndex = new HashMap<>();
        documentSet = new HashSet<>();
    }

    public void addDocument(DocumentId documentId, Reader reader) throws IOException {
        if (documentSet.contains(documentId)) {
            return; // Document already added
        }

        Map<String, Integer> termFrequency = new HashMap<>();
        Scanner scanner = new Scanner(reader).useDelimiter("\\W+");
        while (scanner.hasNext()) {
            String term = scanner.next().toLowerCase();
            termFrequency.put(term, termFrequency.getOrDefault(term, 0) + 1);
            termDocumentIndex.computeIfAbsent(term, k -> new HashSet<>()).add(documentId);
        }
        documentTermFrequency.put(documentId, termFrequency);
        documentSet.add(documentId);
        scanner.close();
    }

    public Set<DocumentId> indexLookup(String term) {
        return termDocumentIndex.getOrDefault(term.toLowerCase(), Collections.emptySet());
    }

    public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException {
        if (!documentSet.contains(documentId)) {
            throw new IllegalArgumentException("DocumentId has not been added to the engine");
        }
        return documentTermFrequency.get(documentId).getOrDefault(term.toLowerCase(), 0);
    }

    public double inverseDocumentFrequency(String term) {
        int N = documentSet.size();
        int M = termDocumentIndex.getOrDefault(term.toLowerCase(), Collections.emptySet()).size();
        return Math.log((1.0 + N) / (1.0 + M));
    }

    public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException {
        if (!documentSet.contains(documentId)) {
            throw new IllegalArgumentException("DocumentId has not been added to the engine");
        }
        int tf = termFrequency(documentId, term);
        double idf = inverseDocumentFrequency(term);
        return tf * idf;
    }

    public List<DocumentId> relevanceLookup(String term) {
        Set<DocumentId> relevantDocuments = indexLookup(term);
        List<DocumentId> sortedDocuments = new ArrayList<>(relevantDocuments);
        sortedDocuments.sort(new TfIdfComparator(this, term));
        return sortedDocuments;
    }
}
