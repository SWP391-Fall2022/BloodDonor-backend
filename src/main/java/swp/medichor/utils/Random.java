package swp.medichor.utils;

public class Random {
    public static int randomCode(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}
