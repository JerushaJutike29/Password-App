import java.util.*;
import java.io.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static final String FILE = "users.txt";

    public static void main(String[] args) throws Exception {
        while(true) {
            System.out.println("\n====== PASSWORD APP ======");
            System.out.println("1. Register 2. Login 3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            if(ch == 1) register();
            else if(ch == 2) login();
            else if(ch == 3) { System.out.println("Exiting..."); break; }
            else System.out.println("Invalid choice");
        }
    }

    static void register() throws Exception {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        String encrypted = PasswordUtil.encrypt(pass);
        FileWriter fw = new FileWriter(FILE, true);
        fw.write(user + ":" + encrypted + "\n");
        fw.close();
        System.out.println("Registered Successfully!");
    }

    static void login() throws Exception {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        String line; boolean found = false;
        while((line = br.readLine())!= null) {
            String[] parts = line.split(":");
            if(parts[0].equals(user) && PasswordUtil.verify(pass, parts[1])) {
                System.out.println("Login Successful! Welcome " + user);
                found = true; break;
            }
        }
        br.close();
        if(!found) System.out.println("Invalid username or password!");
    }
}