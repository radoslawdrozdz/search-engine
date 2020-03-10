package rdrozdz.searchengine.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordCleaner {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[.!@#$%&*()_+=|<>?{}\\[\\]~-]");

    String clean(String input) {
        Matcher matcher = SPECIAL_CHARACTERS.matcher(input);
        if (matcher.find()) {
            return reduceSpecialCharacters(input);
        } else {
            return input;
        }
    }

    private String reduceSpecialCharacters(String input) {
        StringBuilder buffer = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            Matcher matcher = SPECIAL_CHARACTERS.matcher(String.valueOf(c));
            if (!matcher.find()) {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }
}
