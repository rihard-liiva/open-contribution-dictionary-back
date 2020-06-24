package ee.rihardliiva.dictionaryback.language.controller;

import ee.rihardliiva.dictionaryback.language.exceptions.LanguageHasUsagesException;
import ee.rihardliiva.dictionaryback.language.exceptions.LanguageNotFoundException;
import ee.rihardliiva.dictionaryback.language.exceptions.LanguageValidationException;
import ee.rihardliiva.dictionaryback.language.model.Language;
import ee.rihardliiva.dictionaryback.language.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/language")
public class LanguageController {

    @Resource
    private LanguageService languageService;

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.findAllLanguages();
    }

    @GetMapping("/id/{id}")
    public Language getLanguageById(@PathVariable Long id) {
        return languageService.findLanguageById(id);
    }

    @PostMapping
    public Language addLanguage(@RequestBody Language language) throws LanguageValidationException {
        return languageService.createNewLanguage(language);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteEntryById(id);
    }
}
