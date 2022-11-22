import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Utils {

    public static BufferedReader input;
    public static Random randomNumber;

    static {
        input = new BufferedReader(new InputStreamReader(System.in));
        randomNumber = new Random();
    }
}
