package com.example.demo.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntry{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private int age;

    @OneToMany(targetEntity = Account.class, mappedBy = "user")
    private Set<Account> accountSet;

    public User() {
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;

        this.accountSet = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccountSet() {
        return Collections.unmodifiableSet(accountSet);
    }

    public void setAccountSet(Set<Account> accountSet) {
        this.accountSet = accountSet;
    }
}
