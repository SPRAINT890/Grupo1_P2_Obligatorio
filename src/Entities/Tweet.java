package Entities;

import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

public class Tweet {
    private User usuario;
    private Long id;
    private String content;
    private String source;
    private Boolean isRetweet;
    private Integer year;
    private Integer mes;
    private Integer day;
    private HashTableCerradoImpl<Long, HashTag> listHastag = new HashTableCerradoImpl<>(3);
    public Tweet(Long id, String content, String source, Boolean isRetweet, Integer year, Integer month, Integer day) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.year = year;
        this.mes = month;
        this.day = day;
    }
    public void setUsuario(User usuario) {
        this.usuario = usuario;
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

    public Integer getYear() {
        return year;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getDay() {
        return day;
    }

    public HashTableCerradoImpl<Long, HashTag> getListHastag() {
        return listHastag;
    }
}
