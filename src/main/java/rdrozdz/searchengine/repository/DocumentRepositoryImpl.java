package rdrozdz.searchengine.repository;

import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.repository.exception.DocumentNotFoundExcpetion;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentRepositoryImpl implements DocumentRepository {

    private Map<DocumentId, Document> db = new ConcurrentHashMap<>();

    @Override
    public void save(Document document) {
        DocumentId documentId = document.getDocumentId();
        db.put(documentId, document);
    }

    @Override
    public long allCount() {
        return db.size();
    }

    @Override
    public String find(DocumentId documentId) {
        Document document = db.get(documentId);
        if (document == null) {
            throw new DocumentNotFoundExcpetion(documentId);
        } else {
            return document.getContent();
        }
    }

    @Override
    public Collection<Document> findAll() {
        return db.values();
    }
}
