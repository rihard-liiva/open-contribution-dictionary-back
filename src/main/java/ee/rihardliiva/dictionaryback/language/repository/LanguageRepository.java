package ee.rihardliiva.dictionaryback.language.repository;

import ee.rihardliiva.dictionaryback.language.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {



}
