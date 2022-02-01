public class Game {

    private Display display;
    public int widht, height;

    public Game(String title, int width, int height) {
        this.widht = width;
        this.height = height;
        display = new Display(title, width, height);
    }
}
