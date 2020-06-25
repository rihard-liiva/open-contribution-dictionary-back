package ee.rihardliiva.dictionaryback.language.controller;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.language.model.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguageControllerTest {

    public static final ParameterizedTypeReference<Language> LANGUAGE = new ParameterizedTypeReference<>() {};
    public static final ParameterizedTypeReference<List<Language>> LANGUAGE_LIST = new ParameterizedTypeReference<>() {};

    public static final Long LANGUAGE_1_ID = 1L;
    public static final String LANGUAGE_1_LANGUAGE_NAME = "English";
    public static final Long LANGUAGE_2_ID = 2L;
    public static final String LANGUAGE_2_LANGUAGE_NAME = "Estonian";

    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void searchReturnsListOfLanguages() {
        ResponseEntity<List<Language>> entity = restTemplate.exchange("/language", HttpMethod.GET, null, LANGUAGE_LIST);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<Language> languages = entity.getBody();
        assertTrue(isNotEmpty(languages));
        assertEquals(languages.get(0).getId(), LANGUAGE_1_ID);
        assertEquals(languages.get(1).getLanguageName(), LANGUAGE_2_LANGUAGE_NAME);
    }

    @Test
    public void searchLanguageById() {
        ResponseEntity<Language> entity = restTemplate.exchange("/language/id/{id}", HttpMethod.GET, null, LANGUAGE, 1);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Language language = entity.getBody();
        assertNotNull(language);
        assertEquals(language.getId(), LANGUAGE_1_ID);
        assertEquals(language.getLanguageName(), LANGUAGE_1_LANGUAGE_NAME);
        assertNotEquals(language.getId(), LANGUAGE_2_ID);
    }

    @Test
    public void createNewLanguage() {
        Language newLanguage = new Language("Polish");
        ResponseEntity<List<Language>> entity = restTemplate.exchange("/language", HttpMethod.GET, null, LANGUAGE_LIST);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<Language> entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        int numberOfEntries = entries.size();
        restTemplate.exchange("/language", HttpMethod.POST, new HttpEntity<>(newLanguage), Language.class);
        entity = restTemplate.exchange("/language", HttpMethod.GET, null, LANGUAGE_LIST);
        entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        assertEquals(entries.size() - 1, numberOfEntries);
    }

    @Test
    public void deleteLanguage() {
        ResponseEntity<List<Language>> entity = restTemplate.exchange("/language", HttpMethod.GET, null, LANGUAGE_LIST);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<Language> entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        int numberOfEntries = entries.size();
        Language newLanguage = new Language("TurkishBlaBlaBlaBla");
        restTemplate.exchange("/language", HttpMethod.POST, new HttpEntity<>(newLanguage), Language.class);
        String url = "/language/delete/" + (numberOfEntries + 1);
        restTemplate.delete(url, HttpMethod.DELETE, null);
        System.out.println(numberOfEntries + 1);
        entity = restTemplate.exchange("/language", HttpMethod.GET, null, LANGUAGE_LIST);
        entries = entity.getBody();
        assertEquals(numberOfEntries, entries.size());
    }
}
