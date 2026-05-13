public class VolumeItem extends ReadingItem {
    private String currentVolume;
    private String maxVolumes;

    public VolumeItem(String title, String currentVolume, String maxVolumes, int rating, String review, boolean isStarred, String notes, String link) {
        super(title, rating, review, isStarred, notes, link);
        this.currentVolume = (currentVolume == null || currentVolume.trim().isEmpty()) ? "unknown" : currentVolume;
        this.maxVolumes = (maxVolumes == null || maxVolumes.trim().isEmpty()) ? "ongoing" : maxVolumes;
        autoCheckCompletion(); 
    }

    private void autoCheckCompletion() {
        if (!currentVolume.equals("unknown") && currentVolume.equalsIgnoreCase(maxVolumes)) {
            setCompleted(true);
        } else {
            setCompleted(false);
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("\n--- Volume-Tracked Entry ---");
        printCommonDetails();
        System.out.println("Progress: Volume " + currentVolume + " / " + maxVolumes);
        System.out.println("----------------------------");
    }

    @Override
    public void updateProgress(String newProgress) {
        this.currentVolume = (newProgress == null || newProgress.trim().isEmpty()) ? "unknown" : newProgress;
        autoCheckCompletion(); 
    }

    @Override
    public void updateMaxProgress(String newMax) {
        this.maxVolumes = (newMax == null || newMax.trim().isEmpty()) ? "ongoing" : newMax;
        autoCheckCompletion(); 
    }
}