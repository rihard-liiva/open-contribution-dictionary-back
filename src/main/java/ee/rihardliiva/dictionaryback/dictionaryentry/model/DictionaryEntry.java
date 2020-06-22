package ee.rihardliiva.dictionaryback.dictionaryentry.model;

import ee.rihardliiva.dictionaryback.language.model.Language;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class DictionaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String word;
    @NonNull
    private String equivalent;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Language originatingLanguage;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Language equivalentLanguage;
}
