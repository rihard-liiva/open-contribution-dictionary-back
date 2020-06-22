package ee.rihardliiva.dictionaryback.dictionaryentry.controller;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.dictionaryentry.service.DictionaryEntryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/entry")
public class DictionaryEntryController {

    @Resource
    private DictionaryEntryService dictionaryEntryService;

    @GetMapping("/search/{word}")
    public List<DictionaryEntry> findEntryByUserInputAnyLanguage(@PathVariable String word) {
        return dictionaryEntryService.findEntriesByUserInput(word);
    }

    @GetMapping("/search/{word}/{languageId}")
    public List<DictionaryEntry> findEntryByUserInputAnyLanguage(@PathVariable String word, @PathVariable Long languageId) {
        return dictionaryEntryService.findEntriesByUserInputAndLanguage(word, languageId);
    }

    @PostMapping
    public DictionaryEntry addEntryToDictionary(@RequestBody DictionaryEntry dictionaryEntry) {
        return dictionaryEntryService.createDictionaryEntry(dictionaryEntry);
    }

}
