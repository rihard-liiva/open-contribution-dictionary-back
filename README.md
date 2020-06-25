# Open Contribution Dictionary Back End

This project was generated with Spring-Boot version 2.3.1 using Java 11 and Gradle Project Template.

## API Information

### Dictionary Entry Endpoints
```
GET /api/entry - type: List<DictionaryEntry>, returns all the entries in the database
GET /api/entry/id/{id} - type: DictionaryEntry, returns the entry with given id
GET /api/entry/search/{word}/{languageId} - type: List<DictionaryEntry>, returns all the entries in the database that match the search parameters
GET /api/entry/search/{word}/{fromLanguageId}/{inLanguageId} - type: List<DictionaryEntry>, returns all the entries in the database that match the search parameters
POST /api/entry - type: DictonaryEntry, returns the created dictionary object
DELETE /api/entry/delete/{id} type: void, deletes the object with given id
```
Example DictionaryEntry Object
```
{
    "id": 0,
    "word": "ülemus",
    "equivalent": "supervisor",
    "originatingLanguage": {
        "id": 2,
        "languageName": "Estonian"
    },
    "equivalentLanguage": {
        "id": 1,
        "languageName": "English"
    }
}
```

### Language Endpoints
```
GET /api/language - type: List<Language>, returns all the languages in the database
GET /api/language/id/{id} - type: Language, returns the language with given id
POST /api/language - type: Language, returns the created language object
DELETE /api/language/delete/{id} type: void, deletes the object with given id
```
Example DictionaryEntry Object
```
{
    "id": 0,
    "languageName": "Polish"
}
```

## Tests

Run tests with gradle task to execute the tests.

Test coverage with the master branch <br>
![alt text](https://cdn.discordapp.com/attachments/453224989383196673/725740905885073448/test_coverage.png "Test coverage percentage with master branch")
<br>
Individual test results
![alt text](https://cdn.discordapp.com/attachments/453224989383196673/725740910108737636/test_results.png "Test coverage percentage with master branch")
