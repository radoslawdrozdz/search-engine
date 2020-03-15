package rdrozdz.searchengine.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import rdrozdz.searchengine.model.exception.TermExceptions.InvalidTermException;

import java.util.regex.Matcher;

import static rdrozdz.searchengine.utils.RegexpUtils.LETTER;
import static rdrozdz.searchengine.utils.RegexpUtils.SPECIAL_CHARACTERS;

@Getter
@EqualsAndHashCode
public class Term {

    private String term;

    public Term(String term) {
        String validated = TermValidator.validate(term);
        this.term = validated.trim();
    }

    private static class TermValidator {
        private static String validate(String term) {
            if (term == null) {
                throw new InvalidTermException("can not create Term with null content");
            } else if (StringUtils.isEmpty(term)){
                throw new InvalidTermException("can not create Term with empty content");
            } else if (StringUtils.isBlank(term)){
                throw new InvalidTermException("can not create Term with blank content");
            }
            Matcher hasSpecial = SPECIAL_CHARACTERS.matcher(term);
            Matcher hasLetter = LETTER.matcher(term);
            if (hasSpecial.find() && !hasLetter.find()) {
                throw new InvalidTermException("can not create Term with only specialCharacters content");
            }
            return term;
        }
    }
}
