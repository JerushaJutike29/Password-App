import org.mindrot.jbcrypt.BCrypt;
import java.io.*;
import java.util.*;

public class PasswordUtil {
    private static Map<String, String> users = new HashMap<>();
    private static final String FILE_NAME = "users.txt";

    public static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine())!= null) {
                String[] parts = line.split(":");
                if (parts.length == 2) users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {}
    }

    public static void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(username, hashed);
        return true;
    }

    public static boolean login(String username, String password) {
        String hashed = users.get(username);
        if (hashed == null) return false;
        return BCrypt.checkpw(password, hashed);
    }
}
