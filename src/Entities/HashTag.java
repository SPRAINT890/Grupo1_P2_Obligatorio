package Entities;

public class HashTag {
    private Long id;
    private String text;
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

}
