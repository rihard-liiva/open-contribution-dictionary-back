package ee.rihardliiva.dictionaryback.language.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
    }
}
