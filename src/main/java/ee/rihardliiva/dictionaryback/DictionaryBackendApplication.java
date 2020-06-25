package ee.rihardliiva.dictionaryback;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.dictionaryentry.repository.DictionaryEntryRepository;
import ee.rihardliiva.dictionaryback.language.model.Language;
import ee.rihardliiva.dictionaryback.language.repository.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
public class DictionaryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryBackendApplication.class, args);
	}

	//Create dummy data
	@Bean
	public CommandLineRunner createEntries(DictionaryEntryRepository dictionaryEntryRepository) {

		Language est = new Language("Estonian");
		Language eng = new Language("English");
		Language rus = new Language("Russian");

		return (args) -> {
			List<DictionaryEntry> words = List.of(
					new DictionaryEntry("tere", "hello", est, eng),
					new DictionaryEntry("laud", "table", est, eng),
					new DictionaryEntry("laud", "ctol", est, rus),
					new DictionaryEntry("tool", "chair", est, eng),
					new DictionaryEntry("diivan", "couch", est, eng),
					new DictionaryEntry("bed", "voodi", eng, est),
					new DictionaryEntry("table", "laud", eng, est),
					new DictionaryEntry("keyboard", "klaviatuur", eng, est),
					new DictionaryEntry("piano", "klaver", eng, est),
					new DictionaryEntry("car", "auto", eng, est),
					new DictionaryEntry("truck", "veoauto", eng, est),
					new DictionaryEntry("sun", "päike", eng, est));
			dictionaryEntryRepository.saveAll(words);
		};
	}

}
