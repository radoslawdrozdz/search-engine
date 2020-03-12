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

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    private SearchEngine searchEngine;
    private DocumentRepository documentRepository;

    DocumentsController(SearchEngine searchEngine, DocumentRepository documentRepository) {
        this.searchEngine = searchEngine;
        this.documentRepository = documentRepository;
    }

    @PostMapping
    ResponseEntity create(@RequestBody String content){
        int id = atomicInteger.getAndIncrement();
        String idAsString = String.valueOf(id);
        searchEngine.indexDocument(idAsString, content);
        URI location = createUri(idAsString);
        return ResponseEntity.created(location).build();
    }

    private URI createUri(String id) {
        return MvcUriComponentsBuilder.fromMethodCall(
                MvcUriComponentsBuilder.on(DocumentsController.class).get(id))
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
    ResponseEntity find(@RequestParam(name = "term", required = false) String term){
        List<IndexEntry> indexEntries = searchEngine.search(term);
        return ResponseEntity.ok(indexEntries);
    }

    @GetMapping
    ResponseEntity getAll(){
        Collection<Document> all = documentRepository.findAll();
        return ResponseEntity.ok(all);
    }

}
