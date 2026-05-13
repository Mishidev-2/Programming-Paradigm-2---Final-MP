public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager(100);
        UserInterface ui = new ConsoleUI(manager);
        ui.start();
    }
}