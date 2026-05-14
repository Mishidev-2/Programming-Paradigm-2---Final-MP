public class OfflineItem extends ReadingItem {
    private String currentOffline;
    private String maxOffline;

    public OfflineItem(String title, String currentOffline, String maxOffline, int rating, String review, boolean isStarred, String notes, String link) {
        super(title, rating, review, isStarred, notes, link);
        this.currentOffline = (currentOffline == null || currentOffline.trim().isEmpty()) ? "unknown" : currentOffline;
        this.maxOffline = (maxOffline == null || maxOffline.trim().isEmpty()) ? "ongoing" : maxOffline;
        autoCheckCompletion(); 
    }

    private void autoCheckCompletion() {

            if (currentOffline.equals("unknown")) {
                setCompleted(false);
                return; 
            }

            // Strips all non-numeric characters to safely parse integers
            String cleanCurrent = currentOffline.replaceAll("[^0-9]", "");
            String cleanMax = maxOffline.replaceAll("[^0-9]", "");

            if (cleanCurrent.isEmpty() || cleanMax.isEmpty()) {
                setCompleted(false);
                return;
            }

            int current = Integer.parseInt(cleanCurrent);
            int max = Integer.parseInt(cleanMax);

            if (current == max) {
                setCompleted(true);
            } else {
                setCompleted(false);
            }
     }

    @Override
    public void displayDetails() {
        System.out.println("\n--- Offline-Tracked Entry ---");
        printCommonDetails();
        System.out.println("Progress: Offline " + currentOffline + " / " + maxOffline);
        System.out.println("----------------------------");
    }

    @Override
    public void updateProgress(String newProgress) {
        this.currentOffline = (newProgress == null || newProgress.trim().isEmpty()) ? "unknown" : newProgress;
        autoCheckCompletion(); 
    }

    @Override
    public void updateMaxProgress(String newMax) {
        this.maxOffline = (newMax == null || newMax.trim().isEmpty()) ? "ongoing" : newMax;
        autoCheckCompletion(); 
    }
}