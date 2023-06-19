package Entities;

import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

public class User {
    private Long id;
    private String name;
    private boolean verified;
    private HashTableCerradoImpl<Long, Tweet> listTweets = new HashTableCerradoImpl<>(500);
    public User(Long id, String name, boolean verified) {
        this.id = id;
        this.name = name;
        this.verified = verified;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isVerified() {
        return verified;
    }

    public HashTableCerradoImpl<Long, Tweet> getListTweets() {
        return listTweets;
    }
}
