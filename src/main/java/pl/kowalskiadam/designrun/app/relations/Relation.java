package pl.kowalskiadam.designrun.app.relations;

import javax.persistence.*;

@Entity
@Table(name = "relations")
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
