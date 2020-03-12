package rdrozdz.searchengine.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Term {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
    private static final Pattern LETTER = Pattern.compile("[a-zA-z]");

    private String term;

    public Term(String term) {
        String validated = TermValidator.validate(term);
        this.term = validated.trim();
    }

    private static class TermValidator {
        private static String validate(String term) {
            if (term == null) {
                throw new IllegalArgumentException("can not create Term with null content");
            } else if (StringUtils.isEmpty(term)){
                throw new IllegalArgumentException("can not create Term with empty content");
            } else if (StringUtils.isBlank(term)){
                throw new IllegalArgumentException("can not create Term with blank content");
            }
            Matcher hasSpecial = SPECIAL_CHARACTERS.matcher(term);
            Matcher hasLetter = LETTER.matcher(term);
            if (hasSpecial.find() && !hasLetter.find()) {
                throw new IllegalArgumentException("can not create Term with only specialCharacters content");
            }
            return term;
        }
    }
}
