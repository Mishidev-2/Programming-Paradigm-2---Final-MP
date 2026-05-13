public class ChapterItem extends ReadingItem {
    private String currentChapter;
    private String maxChapters;

    public ChapterItem(String title, String currentChapter, String maxChapters, int rating, String review, boolean isStarred, String notes, String link) {
        super(title, rating, review, isStarred, notes, link);
        this.currentChapter = (currentChapter == null || currentChapter.trim().isEmpty()) ? "unknown" : currentChapter;
        this.maxChapters = (maxChapters == null || maxChapters.trim().isEmpty()) ? "ongoing" : maxChapters;
        autoCheckCompletion(); 
    }

    private void autoCheckCompletion() {
        if (!currentChapter.equals("unknown") && currentChapter.equalsIgnoreCase(maxChapters)) {
            setCompleted(true);
        } else {
            setCompleted(false);
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("\n--- Chapter-Tracked Entry ---");
        printCommonDetails();
        System.out.println("Progress: Chapter " + currentChapter + " / " + maxChapters);
        System.out.println("-----------------------------");
    }

    @Override
    public void updateProgress(String newProgress) {
        this.currentChapter = (newProgress == null || newProgress.trim().isEmpty()) ? "unknown" : newProgress;
        autoCheckCompletion(); 
    }

    @Override
    public void updateMaxProgress(String newMax) {
        this.maxChapters = (newMax == null || newMax.trim().isEmpty()) ? "ongoing" : newMax;
        autoCheckCompletion(); 
    }
}