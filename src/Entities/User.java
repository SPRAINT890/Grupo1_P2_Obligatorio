package Entities;

import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

public class User {
    private Long id;

    private String name;

    private HashTableCerradoImpl<Long, Tweet> listTweets = new HashTableCerradoImpl<>(3);
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashTableCerradoImpl<Long, Tweet> getListTweets() {
        return listTweets;
    }
}
