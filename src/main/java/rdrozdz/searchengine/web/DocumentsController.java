package rdrozdz.searchengine.web;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.repository.DocumentRepository;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/documents")
class DocumentsController {

    private DocumentIdSequence sequence = new DocumentIdSequence();

    private SearchEngine searchEngine;
    private DocumentRepository documentRepository;

    DocumentsController(SearchEngine searchEngine, DocumentRepository documentRepository) {
        this.searchEngine = searchEngine;
        this.documentRepository = documentRepository;
    }

    @PostMapping
    ResponseEntity create(@RequestBody String content){
        String documentId = sequence.next();
        searchEngine.indexDocument(documentId, content);
        URI location = createdDocumentURI(documentId);
        return ResponseEntity.created(location).build();
    }

    private URI createdDocumentURI(String documentId) {
        return MvcUriComponentsBuilder.fromMethodCall(
                MvcUriComponentsBuilder.on(DocumentsController.class).get(documentId))
                .build()
                .toUri();
    }

    @GetMapping(value = {"/{id}"})
    ResponseEntity get(@PathVariable String id) {
        DocumentId documentId = DocumentId.of(id);
        String result = documentRepository.find(documentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value ="/search")
    ResponseEntity find(@RequestParam(name = "term") String term){
        List<IndexEntry> indexEntries = searchEngine.search(term);
        return ResponseEntity.ok(indexEntries);
    }

    @GetMapping
    ResponseEntity getAll(){
        Collection<Document> all = documentRepository.findAll();
        return ResponseEntity.ok(all);
    }

    private static class DocumentIdSequence{

        private AtomicInteger sequence = new AtomicInteger(0);

        private String next() {
            int nextId = sequence.incrementAndGet();
            return String.valueOf(nextId);
        }
    }

}
