package ee.rihardliiva.dictionaryback.language.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Language name cannot be empty")
public class LanguageValidationException extends Throwable {

    public LanguageValidationException(String message) {
        super(message);
    }
}
