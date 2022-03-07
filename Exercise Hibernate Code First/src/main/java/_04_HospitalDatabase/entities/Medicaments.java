package _04_HospitalDatabase.entities;

import javax.persistence.*;

@Entity
@Table(name = "_04_medicaments")
public class Medicaments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public Medicaments() {
    }

    public Medicaments(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
