public abstract class ReadingItem {
    private String title;
    private int rating; 
    private String review;
    private boolean isStarred;
    private String notes;
    private boolean isCompleted;
    private String link;

    public ReadingItem(String title, int rating, String review, boolean isStarred, String notes, String link) {
        this.title = title;
        setRating(rating); 
        this.review = review;
        this.isStarred = isStarred;
        this.notes = notes;
        this.link = (link == null) ? "" : link.trim();
        this.isCompleted = false; 
    }

    public void setRating(int rating) {
        if (rating < 1) {
            this.rating = 1;
        } else if (rating > 5) {
            this.rating = 5;
        } else {
            this.rating = rating;
        }
    }

    public void setNotes(String notes) { this.notes = notes; }
    public void setCompleted(boolean status) { this.isCompleted = status; }
    public void setLink(String link) { this.link = (link == null) ? "" : link.trim(); }

    public String getTitle() { return title; }
    public boolean isStarred() { return isStarred; }

    public abstract void displayDetails();
    public abstract void updateProgress(String newProgress);
    public abstract void updateMaxProgress(String newMax); 

    protected void printCommonDetails() {
        String star = isStarred ? "[* FAVORITE]" : "";
        String status = isCompleted ? "[COMPLETED]" : "[ONGOING]"; 
        
        System.out.println("Title: " + title + " " + star + " " + status);
        System.out.println("Rating: " + rating + "/5");
        
        if (!link.isEmpty()) System.out.println("Link: " + link);
        if (!review.trim().isEmpty()) System.out.println("Review: " + review);
        if (!notes.trim().isEmpty()) System.out.println("Notes: " + notes);
    }
}