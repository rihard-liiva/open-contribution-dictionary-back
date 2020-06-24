package ee.rihardliiva.dictionaryback.language.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Language has usages so it cannot be deleted")
public class LanguageHasUsagesException extends RuntimeException {

    public LanguageHasUsagesException(String message) {
        super(message);
    }
}
