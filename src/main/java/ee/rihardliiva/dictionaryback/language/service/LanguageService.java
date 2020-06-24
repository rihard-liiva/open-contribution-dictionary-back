package ee.rihardliiva.dictionaryback.language.service;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.dictionaryentry.service.DictionaryEntryService;
import ee.rihardliiva.dictionaryback.language.exceptions.LanguageHasUsagesException;
import ee.rihardliiva.dictionaryback.language.exceptions.LanguageNotFoundException;
import ee.rihardliiva.dictionaryback.language.exceptions.LanguageValidationException;
import org.apache.commons.lang3.StringUtils;
import ee.rihardliiva.dictionaryback.language.model.Language;
import ee.rihardliiva.dictionaryback.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final DictionaryEntryService dictionaryEntryService;

    public List<Language> findAllLanguages() {
        return languageRepository.findAll();
    }

    public Language findLanguageById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException());
    }

    public Language createNewLanguage(Language language) throws LanguageValidationException {
        if (StringUtils.isBlank(language.getLanguageName())) {
            throw new LanguageValidationException();
        }
        return languageRepository.save(language);
    }

    public void deleteEntryById(Long id) {
        Language language = findLanguageById(id);
        List<DictionaryEntry> dictionaryEntries = dictionaryEntryService.findAll();
        for (DictionaryEntry dictEntry : dictionaryEntries) {
            if (dictEntry.getEquivalentLanguage().getId().equals(language.getId()) || dictEntry.getOriginatingLanguage().getId().equals(language.getId())) {
                throw new LanguageHasUsagesException();
            }
        }
        languageRepository.delete(findLanguageById(id));
    }

}
