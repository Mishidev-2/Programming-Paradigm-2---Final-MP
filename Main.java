public class Main {
    public static void main(String[] args) {
        
        LibraryManager manager = new LibraryManager(11);
        UserInterface ui = new ConsoleUI(manager);
        ui.start();
    }
}