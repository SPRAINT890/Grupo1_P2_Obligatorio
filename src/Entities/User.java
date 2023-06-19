package Entities;

import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

public class User {
    private Long id;
    private String name;
    private boolean verified;
    private Integer like;
    private HashTableCerradoImpl<Long, Tweet> listTweets = new HashTableCerradoImpl<>(500);
    public User(Long id, String name, boolean verified, Integer like) {
        this.id = id;
        this.name = name;
        this.verified = verified;
        this.like = like;
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

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public HashTableCerradoImpl<Long, Tweet> getListTweets() {
        return listTweets;
    }
}
