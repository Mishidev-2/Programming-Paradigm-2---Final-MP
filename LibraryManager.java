public class LibraryManager {
    private ReadingItem[] library;
    private int itemCount;

    public LibraryManager(int capacity) {
        this.library = new ReadingItem[capacity];
        this.itemCount = 0;
    }

    public boolean isFull() { return itemCount >= library.length; }
    public boolean isEmpty() { return itemCount == 0; }
    public int getItemCount() { return itemCount; }

    public void addChapterTrackedItem(String title, String progress, String maxProgress, int rating, String review, boolean isStarred, String notes, String link) {
        if (!isFull()) {
            library[itemCount] = new ChapterItem(title, progress, maxProgress, rating, review, isStarred, notes, link);
            itemCount++;
        }
    }

    public void addVolumeTrackedItem(String title, String progress, String maxProgress, int rating, String review, boolean isStarred, String notes, String link) {
        if (!isFull()) {
            library[itemCount] = new VolumeItem(title, progress, maxProgress, rating, review, isStarred, notes, link);
            itemCount++;
        }
    }

    public ReadingItem getItem(int index) {
        if (index >= 0 && index < itemCount) {
            return library[index];
        }
        return null;
    }
}