import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String source = args[0];
        CsvManager csvManager = new CsvManager(source);
        try {
            List<String> col = csvManager.extractCol(2);
            col.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
