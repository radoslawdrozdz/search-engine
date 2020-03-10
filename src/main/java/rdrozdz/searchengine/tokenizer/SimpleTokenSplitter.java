package rdrozdz.searchengine.tokenizer;

import rdrozdz.searchengine.model.Document;

import java.util.Arrays;
import java.util.List;

public class SimpleTokenSplitter implements TokenSplitter {
    //    https://en.wikipedia.org/wiki/Lexical_analysis#Tokenization
    private static final String SPLITED_BY = " ";

    @Override
    public List<String> split(Document document) {
        String content = document.getContent();
        String[] split = content.split(SPLITED_BY);
        return Arrays.asList(split);
    }
}
