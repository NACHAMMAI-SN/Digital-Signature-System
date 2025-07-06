import javax.swing.*;
import java.awt.*;

public class DigitalSignatureGUI {
    private String userRole;

    public DigitalSignatureGUI(String userRole) {
        this.userRole = userRole;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Digital Signature System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JTabbedPane tabbedPane = new JTabbedPane();

            if (userRole.equals("admin")) {
                tabbedPane.addTab("🔑 Key Generation", createKeyGenPanel());
                tabbedPane.addTab("📁 File Hash", createHashFileadminPanel());
                tabbedPane.addTab("✍ Sign File", createSignFilePanel());
            } else if (userRole.equals("client")) {
                tabbedPane.addTab("📁 File Hash", createHashFileclientPanel());
                tabbedPane.addTab("✔ Verify Signature", createVerifyPanel());
            }

            frame.add(tabbedPane);
            frame.setVisible(true);
        });
    }

    private static JPanel createKeyGenPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Key Generation"));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton generateButton = new JButton("Generate Keys");
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setToolTipText("Click to generate public and private keys.");
        generateButton.addActionListener(e -> outputArea.setText(KeyGeneration.generateKeys()));

        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(generateButton, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel createHashFileadminPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("File Hashing"));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton selectFileButton = new JButton("Select and Hash File");
        selectFileButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectFileButton.setToolTipText("Select a file to compute its hash.");
        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                outputArea.setText(FileHashadmin.hashFile(fileChooser.getSelectedFile().getAbsolutePath()));
            }
        });

        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(selectFileButton, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel createHashFileclientPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("File Hashing"));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton selectFileButton = new JButton("Select and Hash File");
        selectFileButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectFileButton.setToolTipText("Select a file to compute its hash.");
        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                outputArea.setText(FileHashclient.hashFile(fileChooser.getSelectedFile().getAbsolutePath()));
            }
        });

        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(selectFileButton, BorderLayout.SOUTH);
        return panel;
    }


    private static JPanel createSignFilePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("File Signing"));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton signFileButton = new JButton("Sign File");
        signFileButton.setFont(new Font("Arial", Font.BOLD, 14));
        signFileButton.setToolTipText("Sign a file using a private key.");
        signFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            outputArea.setText("Select Hashed File...");
            if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                String hashFilePath = fileChooser.getSelectedFile().getAbsolutePath();

                outputArea.setText("Select Private Key File...");
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String privateKeyPath = fileChooser.getSelectedFile().getAbsolutePath();
                    outputArea.setText(SignFile.signFile(hashFilePath, privateKeyPath));
                }
            }
        });

        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(signFileButton, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel createVerifyPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Signature Verification"));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton verifyButton = new JButton("Verify Signature");
        verifyButton.setFont(new Font("Arial", Font.BOLD, 14));
        verifyButton.setToolTipText("Verify a file's signature using a public key.");
        verifyButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            outputArea.setText("Select Signature File...");
            if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                String signatureFilePath = fileChooser.getSelectedFile().getAbsolutePath();

                outputArea.setText("Select Public Key File...");
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String publicKeyPath = fileChooser.getSelectedFile().getAbsolutePath();
                    outputArea.setText(VerifySignature.verifySignature(signatureFilePath, publicKeyPath));
                }
            }
        });

        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(verifyButton, BorderLayout.SOUTH);
        return panel;
    }
}
