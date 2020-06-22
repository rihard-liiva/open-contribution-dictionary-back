package ee.rihardliiva.dictionaryback;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.dictionaryentry.repository.DictionaryEntryRepository;
import ee.rihardliiva.dictionaryback.language.model.Language;
import ee.rihardliiva.dictionaryback.language.repository.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DictionaryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryBackendApplication.class, args);
	}

	//Create dummy data
	@Bean
	public CommandLineRunner createEntries(DictionaryEntryRepository dictionaryEntryRepository) {

		Language est = new Language("EST");
		Language eng = new Language("ENG");
		Language rus = new Language("RUS");

		return (args) -> {
			List<DictionaryEntry> words = List.of(
					new DictionaryEntry("Tere", "Hello", est),
					new DictionaryEntry("Tere", "Hello", est),
					new DictionaryEntry("Laud", "Table", est),
					new DictionaryEntry("Tool", "Chair", est));
			dictionaryEntryRepository.saveAll(words);
		};
	}

}
