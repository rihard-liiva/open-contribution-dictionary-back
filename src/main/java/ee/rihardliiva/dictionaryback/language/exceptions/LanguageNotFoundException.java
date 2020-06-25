package ee.rihardliiva.dictionaryback.language.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Language with given ID doesn't exist")
public class LanguageNotFoundException extends RuntimeException {

}
