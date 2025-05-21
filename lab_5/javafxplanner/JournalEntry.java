package javafxplanner;

public class JournalEntry {
    private int entryId;
    private int authorId;
    private String title;
    private String content;
    private String creationDate;

    public JournalEntry(int entryId, int authorId, String title,
                        String content, String creationDate) {
        this.entryId = entryId;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public int getEntryId() { return entryId; }
    public int getAuthorId() { return authorId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCreationDate() { return creationDate; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}