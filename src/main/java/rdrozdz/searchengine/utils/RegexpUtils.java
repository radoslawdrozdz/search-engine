package rdrozdz.searchengine.utils;

import java.util.regex.Pattern;

public class RegexpUtils {
    public static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[.!@#$%&*()_+=|<>?{}\\[\\]~-]");
    public static final Pattern LETTER = Pattern.compile("[a-zA-z]");

    private RegexpUtils(){}
}
