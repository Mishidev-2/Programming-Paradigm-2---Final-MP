public class OnlineItem extends ReadingItem {
    private String currentOnline;
    private String maxOnline;

    public OnlineItem(String title, String currentOnline, String maxOnline, int rating, String review, boolean isStarred, String notes, String link) {
        super(title, rating, review, isStarred, notes, link);
        this.currentOnline = (currentOnline == null || currentOnline.trim().isEmpty()) ? "unknown" : currentOnline;
        this.maxOnline = (maxOnline == null || maxOnline.trim().isEmpty()) ? "ongoing" : maxOnline;
        autoCheckCompletion(); 
    }

    private void autoCheckCompletion() {

            if (currentOnline.equals("unknown")) {
                setCompleted(false);
                return; 
            }

            // Strips all non-numeric characters to safely parse integers
            String cleanCurrent = currentOnline.replaceAll("[^0-9]", "");
            String cleanMax = maxOnline.replaceAll("[^0-9]", "");

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
        System.out.println("\n--- Online-Tracked Entry ---");
        printCommonDetails();
        System.out.println("Progress: Online " + currentOnline + " / " + maxOnline);
        System.out.println("-----------------------------");
    }

    @Override
    public void updateProgress(String newProgress) {
        this.currentOnline = (newProgress == null || newProgress.trim().isEmpty()) ? "unknown" : newProgress;
        autoCheckCompletion(); 
    }

    @Override
    public void updateMaxProgress(String newMax) {
        this.maxOnline = (newMax == null || newMax.trim().isEmpty()) ? "ongoing" : newMax;
        autoCheckCompletion(); 
    }
}