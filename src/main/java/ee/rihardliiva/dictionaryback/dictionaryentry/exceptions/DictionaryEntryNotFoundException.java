package ee.rihardliiva.dictionaryback.dictionaryentry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "id not found")
public class DictionaryEntryNotFoundException extends RuntimeException {
}
