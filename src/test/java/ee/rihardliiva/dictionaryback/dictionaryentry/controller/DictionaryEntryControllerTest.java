package ee.rihardliiva.dictionaryback.dictionaryentry.controller;

import ee.rihardliiva.dictionaryback.dictionaryentry.model.DictionaryEntry;
import ee.rihardliiva.dictionaryback.language.model.Language;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
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
public class DictionaryEntryControllerTest {

    public static final ParameterizedTypeReference<DictionaryEntry> DICTIONARY_ENTRY = new ParameterizedTypeReference<>() {};
    public static final ParameterizedTypeReference<List<DictionaryEntry>> DICTIONARY_ENTRY_LIST = new ParameterizedTypeReference<>() {};

    public static final String DICTIONARY_ENTRY_1_WORD = "tere";
    public static final String DICTIONARY_ENTRY_1_EQUIVALENT = "hello";
    public static final String DICTIONARY_ENTRY_2_WORD = "laud";
    public static final String DICTIONARY_ENTRY_2_EQUIVALENT = "table";
    public static final String TABLE_RUSSIAN_EQUIVALENT = "ctol";


    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void searchReturnsListOfEntries() {
        ResponseEntity<List<DictionaryEntry>> entity = restTemplate.exchange("/entry/search/laud", HttpMethod.GET, null, DICTIONARY_ENTRY_LIST);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<DictionaryEntry> entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        assertEquals(entries.get(0).getWord(), DICTIONARY_ENTRY_2_WORD);
        assertEquals(entries.get(1).getEquivalent(), TABLE_RUSSIAN_EQUIVALENT);
    }

    @Test
    public void searchEntryById() {
        ResponseEntity<DictionaryEntry> entity = restTemplate.exchange("/entry/id/{id}", HttpMethod.GET, null, DICTIONARY_ENTRY, 1);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        DictionaryEntry dictionaryEntry = entity.getBody();
        assertEquals(dictionaryEntry.getWord(), DICTIONARY_ENTRY_1_WORD);
        assertEquals(dictionaryEntry.getEquivalent(), DICTIONARY_ENTRY_1_EQUIVALENT);
    }

    @Test
    public void searchEntryByIdAndTargetLanguage() {
        ResponseEntity<List<DictionaryEntry>> entity = restTemplate.exchange("/entry/search/{word}/{id}", HttpMethod.GET, null, DICTIONARY_ENTRY_LIST, "tere", 2);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<DictionaryEntry> dictionaryEntries = entity.getBody();
        assertEquals(dictionaryEntries.get(0).getWord(), DICTIONARY_ENTRY_1_WORD);
        assertEquals(dictionaryEntries.get(0).getEquivalent(), DICTIONARY_ENTRY_1_EQUIVALENT);
    }

    @Test
    public void deleteEntry() {
        ResponseEntity<List<DictionaryEntry>> entity = restTemplate.exchange("/entry", HttpMethod.GET, null, DICTIONARY_ENTRY_LIST);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<DictionaryEntry> entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        int numberOfEntries = entries.size();
        restTemplate.delete("/entry/delete/7", HttpMethod.DELETE, null);
        entity = restTemplate.exchange("/entry", HttpMethod.GET, null, DICTIONARY_ENTRY_LIST);
        entries = entity.getBody();
        assertEquals(numberOfEntries - 1, entries.size());
    }

    @Test
    public void createEntry() {
        DictionaryEntry dictionaryEntry = new DictionaryEntry("kollane", "yellow", new Language("Polish"), new Language("Swedish"));
        ResponseEntity<List<DictionaryEntry>> entity = restTemplate.exchange("/entry", HttpMethod.GET, null, DICTIONARY_ENTRY_LIST);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<DictionaryEntry> entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        int numberOfEntries = entries.size();
        restTemplate.exchange("/entry", HttpMethod.POST, new HttpEntity<>(dictionaryEntry), DictionaryEntry.class);
        entity = restTemplate.exchange("/entry", HttpMethod.GET, null, DICTIONARY_ENTRY_LIST);
        entries = entity.getBody();
        assertTrue(isNotEmpty(entries));
        assertEquals(entries.size() - 1, numberOfEntries);
    }
}
