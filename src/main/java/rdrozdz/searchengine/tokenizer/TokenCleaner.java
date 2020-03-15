package rdrozdz.searchengine.tokenizer;

import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.regex.Matcher;

import static rdrozdz.searchengine.utils.RegexpUtils.SPECIAL_CHARACTERS;

public class TokenCleaner {

    private static final String STRING_IDENTITY = "";
    private static final Predicate<String> IS_NOT_SPECIAL_CHARACTER = s -> !SPECIAL_CHARACTERS.matcher(s).find();
    private static final IntFunction<String> CHAR_TO_STRING = c -> String.valueOf((char) c);

    String clean(String input) {
        Matcher matcher = SPECIAL_CHARACTERS.matcher(input);
        if (matcher.find()) {
            return reduceSpecialCharacters(input);
        } else {
            return input;
        }
    }

    private String reduceSpecialCharacters(String input) {
        return input.codePoints()
                .mapToObj(CHAR_TO_STRING)
                .filter(IS_NOT_SPECIAL_CHARACTER)
                .reduce(STRING_IDENTITY, (a, b) -> a + b);

    }
}
