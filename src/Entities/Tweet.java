package Entities;

import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

public class Tweet {
    private User usuario;
    private Long id;
    private String content;
    private String source;
    private Boolean isRetweet;
    private HashTableCerradoImpl<Long, HashTag> listHastag;
    public Tweet(User usuario, Long id, String content, String source, Boolean isRetweet) {
        this.usuario = usuario;
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
    }
    public User getUsuario() {
        return usuario;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public Boolean getRetweet() {
        return isRetweet;
    }

    public HashTableCerradoImpl<Long, HashTag> getListHastag() {
        return listHastag;
    }
}
