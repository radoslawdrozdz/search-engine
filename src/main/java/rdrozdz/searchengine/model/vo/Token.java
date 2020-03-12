package rdrozdz.searchengine.model.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

@EqualsAndHashCode
@ToString
public class Token {

    public static final Token EMPTY = new Token("");
    private static final String EMPTY_STRING = "";

    private String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token of(String token) {
        Objects.requireNonNull(token);
        if (token.equals(EMPTY_STRING)){
            return EMPTY;
        } else {
            return new Token(token);
        }
    }
}
