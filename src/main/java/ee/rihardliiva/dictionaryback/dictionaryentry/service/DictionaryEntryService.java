package ee.rihardliiva.dictionaryback.dictionaryentry.service;

import ee.rihardliiva.dictionaryback.dictionaryentry.repository.DictionaryEntryRepository;
import ee.rihardliiva.dictionaryback.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DictionaryEntryService {

    private final DictionaryEntryRepository dictionaryEntryRepository;
    private final LanguageRepository languageRepository;

}
