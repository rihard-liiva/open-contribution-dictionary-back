package ee.rihardliiva.dictionaryback.dictionaryentry.service;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.dictionaryentry.repository.DictionaryEntryRepository;
import ee.rihardliiva.dictionaryback.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DictionaryEntryService {

    private final DictionaryEntryRepository dictionaryEntryRepository;
    private final LanguageRepository languageRepository;

    public List<DictionaryEntry> findEntryByUserInput(String word) {
        return null;
    }
}
