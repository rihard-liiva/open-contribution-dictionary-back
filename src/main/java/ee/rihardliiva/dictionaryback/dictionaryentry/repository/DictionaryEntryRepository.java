package ee.rihardliiva.dictionaryback.dictionaryentry.repository;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, Long> {



}
