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

    public List<DictionaryEntry> findEntriesByUserInput(String word) {
        return dictionaryEntryRepository.findByWordContaining(word);
    }

    public List<DictionaryEntry> findEntriesByUserInputAndLanguage(String word, Long languageId) {
        return dictionaryEntryRepository.findByWordContainingAndOriginatingLanguageId(word, languageId);
    }
}
