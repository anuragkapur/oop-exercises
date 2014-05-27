import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class HelloWorld {

    public static void write() {
        try {
            Writer writer = new FileWriter("temp.txt", true);
            writer.write("Test line added");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("temp.txt"), StandardCharsets.UTF_8);
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println("Read from file :: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        write();
        read();
    }

}
