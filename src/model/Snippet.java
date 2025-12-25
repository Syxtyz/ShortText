package model;

public class Snippet {
    private int id;
    private String trigger;
    private String content;
    private String description;

    public Snippet(int id, String trigger, String content, String description) {
        this.id = id;
        this.trigger = trigger;
        this.content = content;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTrigger() { return trigger; }
    public String getContent() { return content; }
    public String getDescription() { return description; }

    public void setTrigger(String trigger) { this.trigger = trigger; }
    public void setContent(String content) { this.content = content; }
    public void setDescription(String description) { this.description = description; }
}
