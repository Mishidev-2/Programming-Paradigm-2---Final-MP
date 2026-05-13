import java.util.Scanner;

public class ConsoleUI implements UserInterface {
    private LibraryManager manager;
    private Scanner scanner;

    public ConsoleUI(LibraryManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("\n=== PERSONAL READING TRACKER ===");
            System.out.println("1. New Entry");
            System.out.println("2. View Library");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addNewEntry();
            } else if (choice.equals("2")) {
                viewLibrary();
            } else if (choice.equals("3")) {
                System.out.println("Closing tracker...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addNewEntry() {
        if (manager.isFull()) {
            System.out.println("Library is full! Cannot add more items.");
            return;
        }

        System.out.println("\n-- Add New Entry --");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Track progress by (1) Chapter or (2) Volume? : ");
        String progressType = scanner.nextLine();

        System.out.print("Current Progress (Number/Name, or press Enter to skip): ");
        String progress = scanner.nextLine();
        
        System.out.print("Maximum/Final Progress (Number, or press Enter for 'ongoing'): ");
        String maxProgress = scanner.nextLine();

        System.out.print("Rating (1-5): ");
        int rating = 1;
        try { 
            rating = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException e) { 
            System.out.println("Invalid, defaulting to 1."); 
        }

        System.out.print("Review (Optional, press Enter to skip): ");
        String review = scanner.nextLine();

        System.out.print("Star this? (y/n): ");
        boolean isStarred = scanner.nextLine().trim().equalsIgnoreCase("y");

        System.out.print("Notes (Optional, press Enter to skip): ");
        String notes = scanner.nextLine();

        System.out.print("Link to read (Optional, press Enter to skip): ");
        String link = scanner.nextLine();

        if (progressType.equals("2")) {
            manager.addVolumeTrackedItem(title, progress, maxProgress, rating, review, isStarred, notes, link);
        } else {
            manager.addChapterTrackedItem(title, progress, maxProgress, rating, review, isStarred, notes, link);
        }
        System.out.println("Entry saved successfully!");
    }

    private void viewLibrary() {
        if (manager.isEmpty()) {
            System.out.println("\nYour library is empty.");
            return;
        }

        while (true) {
            System.out.println("\n--- Your Library ---");
            for (int i = 0; i < manager.getItemCount(); i++) {
                ReadingItem item = manager.getItem(i);
                String star = item.isStarred() ? " ★" : "";
                System.out.println((i + 1) + ". " + item.getTitle() + star);
            }
            System.out.println("0. Go Back");
            System.out.print("Enter the number to view, or 0 to go back: ");

            int index = -1;
            try { 
                index = Integer.parseInt(scanner.nextLine()) - 1; 
            } catch (NumberFormatException e) { 
                // Ignore invalid format and loop
            }

            if (index == -1) break;

            ReadingItem selectedItem = manager.getItem(index);
            if (selectedItem != null) {
                viewItemDetails(selectedItem); 
            } else {
                System.out.println("Invalid number.");
            }
        }
    }

    private void viewItemDetails(ReadingItem item) {
        while (true) {
            item.displayDetails();
            
            System.out.println("\n-- Options --");
            System.out.println("1. Edit this entry");
            System.out.println("2. Back to Library list");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                editItemMenu(item);
            } else if (choice.equals("2")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void editItemMenu(ReadingItem item) {
        while (true) {
            System.out.println("\n-- Editing: " + item.getTitle() + " --");
            System.out.println("1. Update Current Progress");
            System.out.println("2. Update Maximum/Final Progress");
            System.out.println("3. Update Rating");
            System.out.println("4. Edit Notes");
            System.out.println("5. Edit Link");
            System.out.println("6. Done Editing (Go Back)");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter new current progress: ");
                item.updateProgress(scanner.nextLine());
                System.out.println("Progress updated.");
            } else if (choice.equals("2")) {
                System.out.print("Enter new maximum/final progress: ");
                item.updateMaxProgress(scanner.nextLine());
                System.out.println("Max progress updated.");
            } else if (choice.equals("3")) {
                System.out.print("Enter new rating (1-5): ");
                try { 
                    item.setRating(Integer.parseInt(scanner.nextLine())); 
                    System.out.println("Rating updated.");
                } catch (NumberFormatException e) { 
                    System.out.println("Invalid input."); 
                }
            } else if (choice.equals("4")) {
                System.out.print("Enter new notes: ");
                item.setNotes(scanner.nextLine());
                System.out.println("Notes updated.");
            } else if (choice.equals("5")) {
                System.out.print("Enter new link: ");
                item.setLink(scanner.nextLine());
                System.out.println("Link updated.");
            } else if (choice.equals("6")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}