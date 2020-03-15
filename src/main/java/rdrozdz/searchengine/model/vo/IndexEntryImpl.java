package rdrozdz.searchengine.model.vo;

import com.findwise.IndexEntry;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Comparator;

@EqualsAndHashCode
@ToString
public class IndexEntryImpl implements IndexEntry {

    public static final Comparator<IndexEntry> INDEX_ENTRY_COMPARATOR = Comparator.comparing(IndexEntry::getScore).reversed();

    private DocumentId id;
    private BigDecimal score;

    public IndexEntryImpl(DocumentId id, BigDecimal score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public String getId() {
        return id.getId();
    }

    @Override
    public void setId(String id) {
        this.id = DocumentId.of(id);
    }

    @Override
    public double getScore() {
        return score.doubleValue();
    }

    @Override
    public void setScore(double score) {
        this.score = BigDecimal.valueOf(score);
    }
}
