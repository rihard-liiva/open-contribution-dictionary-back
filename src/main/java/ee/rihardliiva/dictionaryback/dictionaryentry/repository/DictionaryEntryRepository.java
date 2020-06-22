package ee.rihardliiva.dictionaryback.dictionaryentry.repository;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, Long> {

    List<DictionaryEntry> findByWordLike(String word);

}
