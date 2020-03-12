package rdrozdz.searchengine.repository;

import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.vo.DocumentId;

import java.util.Collection;

public interface DocumentRepository {
    void save(Document document);

    long allCount();

    String find(DocumentId id);

    Collection<Document> findAll();
}
