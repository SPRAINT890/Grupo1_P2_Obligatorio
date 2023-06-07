package Entities;

public class Tweet {
    private User usuario;
    private long id;

    private String content;

    private String source;

    private boolean isRetweet;
    public Tweet(User usuario, long id, String content, String source, boolean isRetweet) {
        this.usuario = usuario;
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
    }
    public User getUsuario() {
        return usuario;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }
}
