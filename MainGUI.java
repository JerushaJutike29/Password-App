import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
        PasswordUtil.loadUsers(); // load from users.txt

        JFrame frame = new JFrame("Password App");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10,20,80,25);
        frame.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        frame.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        frame.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        frame.add(passwordText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 120, 25);
        frame.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 80, 120, 25);
        frame.add(loginButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 110, 300, 25);
        frame.add(resultLabel);

        registerButton.addActionListener(e -> {
            String u = userText.getText();
            String p = new String(passwordText.getPassword());
            if(PasswordUtil.register(u,p)) resultLabel.setText("Registered!");
            else resultLabel.setText("User exists!");
        });

        loginButton.addActionListener(e -> {
            String u = userText.getText();
            String p = new String(passwordText.getPassword());
            if(PasswordUtil.login(u,p)) resultLabel.setText("Login Success!");
            else resultLabel.setText("Login Failed!");
        });

        frame.setVisible(true);
    }
}