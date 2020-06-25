package ee.rihardliiva.dictionaryback.dictionaryentry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "entry already exists")
public class DictionaryEntryAlreadyExistsException extends RuntimeException {
}
