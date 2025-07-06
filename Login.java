import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); 
        panel.add(loginButton);

        frame.add(panel, BorderLayout.CENTER);

       
        loginButton.addActionListener(new LoginButtonListener(frame, usernameField, passwordField));

        frame.setVisible(true);
    }
}


class LoginButtonListener implements ActionListener {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginButtonListener(JFrame frame, JTextField usernameField, JPasswordField passwordField) {
        this.frame = frame;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(frame, "Welcome Admin!");
            new DigitalSignatureGUI("admin");
            frame.dispose();
        } else if (username.equals("client") && password.equals("client123")) {
            JOptionPane.showMessageDialog(frame, "Welcome Client!");
            new DigitalSignatureGUI("client");
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
        }
    }
}
