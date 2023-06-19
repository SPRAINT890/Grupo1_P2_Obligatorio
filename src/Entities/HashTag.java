package Entities;

import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

public class HashTag {
    private Long id;
    private String text;
    private HashTableCerradoImpl<Long, Tweet> listTweetUsed = new HashTableCerradoImpl<>(1000);
    public HashTag(Long id, String text) {
        this.id = id;
        this.text = text;
    }
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public HashTableCerradoImpl<Long, Tweet> getListTweetUsed() {
        return listTweetUsed;
    }
}
