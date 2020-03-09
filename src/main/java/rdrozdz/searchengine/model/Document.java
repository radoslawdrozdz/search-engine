package rdrozdz.searchengine.model;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[.!@#$%&*()_+=|<>?{}\\[\\]~-]");
    private static final Pattern LETTER = Pattern.compile("[a-zA-z]");

    private DocumentId documentId;
    private String content;

    public Document(DocumentId documentId, String content) {
        this.documentId = DicumentIdVerifier.verify(documentId);
        this.content = ContentVerifier.verify(content);
    }

    private static class DicumentIdVerifier {
        private static DocumentId verify(DocumentId documentId) {
            if (documentId == null) {
                throw new IllegalArgumentException("can not create Document with null documentId id");
            }
            return documentId;
        }
    }

    private static class ContentVerifier {
        static String verify(String content) {
            if (content == null) {
                throw new IllegalArgumentException("can not create Document with null content");
            } else if (StringUtils.isEmpty(content)) {
                throw new IllegalArgumentException("can not create Document with empty content");
            } else if (StringUtils.isBlank(content)) {
                throw new IllegalArgumentException("can not create Document with only white spaces content");
            }
            Matcher hasSpecial = SPECIAL_CHARACTERS.matcher(content);
            Matcher hasLetter = LETTER.matcher(content);
            if (hasSpecial.find() && !hasLetter.find()) {
                throw new IllegalArgumentException("can not create Document with only special characters content");
            }
            return content;
        }
    }
}
