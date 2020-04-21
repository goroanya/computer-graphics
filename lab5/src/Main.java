import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Pumpkin window = new Pumpkin();

            window.setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
