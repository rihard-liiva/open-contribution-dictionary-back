package ee.rihardliiva.dictionaryback.dictionaryentry.repository;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, Long> {

    List<DictionaryEntry> findByWordContaining(String word);
    List<DictionaryEntry> findByWordContainingAndOriginatingLanguageId(String word, Long id);
    List<DictionaryEntry> findByWordContainingAndOriginatingLanguageIdAndEquivalentLanguageId(
            String word,
            Long originatingLanguageId,
            Long EquivalentLanguageId
    );

}
