package shorttext;

public class ShortText {
    public static void main(String[] args) {
        ApplicationGUI window = new ApplicationGUI();
        new SystemTray(window);
    }
}