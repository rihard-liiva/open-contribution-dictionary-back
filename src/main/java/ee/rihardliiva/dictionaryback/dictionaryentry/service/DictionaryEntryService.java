package ee.rihardliiva.dictionaryback.dictionaryentry.service;

import ee.rihardliiva.dictionaryback.dictionaryentry.exceptions.DictionaryEntryValidationException;
import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.dictionaryentry.repository.DictionaryEntryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DictionaryEntryService {

    private final DictionaryEntryRepository dictionaryEntryRepository;

    public List<DictionaryEntry> findEntriesByUserInput(String word) {
        return dictionaryEntryRepository.findByWordContaining(word);
    }

    public List<DictionaryEntry> findEntryByUserInputFromAnyLanguageToGivenLanguage(String word, Long languageId) {
        return dictionaryEntryRepository.findByWordContainingAndOriginatingLanguageId(word, languageId);
    }

    public List<DictionaryEntry> findEntryByUserInputFromGivenLanguageToGivenLanguage(String word, Long fromLanguageId, Long inLanguageId) {
        return dictionaryEntryRepository.findByWordContainingAndOriginatingLanguageIdAndEquivalentLanguageId(word, fromLanguageId, inLanguageId);
    }

    public DictionaryEntry createDictionaryEntry(DictionaryEntry dictionaryEntry) {
        if (StringUtils.isBlank(dictionaryEntry.getWord()) || StringUtils.isBlank(dictionaryEntry.getEquivalent())) {
            throw new DictionaryEntryValidationException();
        }
        return dictionaryEntryRepository.save(dictionaryEntry);
    }

    public void deleteEntryById(Long id) {
        dictionaryEntryRepository.deleteById(id);
    }
}
