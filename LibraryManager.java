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

    public void addOnlineTrackedItem(String title, String currentOnline, String maxOnline, int rating, String review, boolean isStarred, String notes, String link) {
        if (!isFull()) {
            library[itemCount] = new OnlineItem(title, currentOnline, maxOnline, rating, review, isStarred, notes, link);
            itemCount++;
        }
    }

    public void addOfflineTrackedItem(String title, String currentOffline, String maxOffline, int rating, String review, boolean isStarred, String notes, String link) {
        if (!isFull()) {
            library[itemCount] = new OfflineItem(title, currentOffline, maxOffline, rating, review, isStarred, notes, link);
            itemCount++;
        }
    }

    public ReadingItem getItem(int index) {
        if (index >= 0 && index < itemCount) {
            return library[index];
        }
        return null;
    }

    public ReadingItem getItem(String searchTitle) {
        for (int i = 0; i < itemCount; i++) {
            if (library[i].getTitle().equalsIgnoreCase(searchTitle.trim())) {
                return library[i]; 
            }
        }
        return null;
    }

}