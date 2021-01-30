package Form;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Form implements ActionListener {
    JRadioButton male;
    JRadioButton female;
    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;
    JButton button = new JButton("Submit");

    // Here we create a hand shaped cursor!
    Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    JFrame frame;

    Form() {
        frame = new JFrame();
        frame.setSize(500, 450);
        frame.setTitle("Form GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xd1d8e0));
        frame.setLayout(null);
        // name Label
        JLabel name = new JLabel("Name");
        name.setBounds(100, 100, 100, 30);
        name.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        JLabel email = new JLabel("Email");
        email.setBounds(100, 150, 100, 30);
        email.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        JLabel phone = new JLabel("Phone");
        phone.setBounds(100, 200, 100, 30);
        phone.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        JLabel sex = new JLabel("Sex");
        sex.setBounds(100, 250, 100, 30);
        sex.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        male = new JRadioButton("Male");
        male.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        male.setBackground(new Color(0xd1d8e0));
        male.setBounds(180, 250, 100, 30);
        male.setFocusable(false);
        male.setSelected(true);

        female = new JRadioButton("FeMale");
        female.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        female.setBackground(new Color(0xd1d8e0));
        female.setBounds(280, 250, 100, 30);
        female.setFocusable(false);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);
        male.addActionListener(this);
        female.addActionListener(this);

        // name textfield
        nameField = new JTextField();
        nameField.setBounds(200, 100, 200, 30);
        nameField.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        emailField = new JTextField();
        emailField.setBounds(200, 150, 200, 30);
        emailField.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        phoneField = new JTextField(10);
        phoneField.setBounds(200, 200, 200, 30);
        phoneField.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        button.setBounds(200, 300, 100, 40);
        button.setFocusable(false);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        button.setBackground(new Color(0x4b7bec));
        button.setForeground(Color.white);
        button.setCursor(cursor);
        button.addActionListener(this);

        frame.add(name);
        frame.add(email);
        frame.add(phone);
        frame.add(male);
        frame.add(sex);
        frame.add(female);
        frame.add(nameField);
        frame.add(emailField);
        frame.add(phoneField);
        frame.add(button);
        frame.setVisible(true);

    }

    private boolean isValidName(String name) {
        if (name.startsWith("_") || name.length() < 3) {
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {

        if (email.length() < 3) {
            System.out.println("email.length() < 3");
            return false;
        } else if (email.indexOf("@") == -1) {
            System.out.println("email.indexOf('@') == -1");
            return false;
        } else if (email.indexOf("@") < 3) {
            System.out.println("email.indexOf('@') < 3");
            return false;
        }
        return true;
    }

    private boolean isValidPhone(String phone) throws NumberFormatException {
        if (phone.length() > 10 || phone.length() < 10) {
            return false;
        }

        return true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == male) {
            System.out.println(male.isSelected());
        }
        if (e.getSource() == female) {
            System.out.println(female.isSelected());
        }

        if (e.getSource() == button) {
            String nameValue = nameField.getText().trim();
            String emailValue = emailField.getText().trim();
            String phoneValue = phoneField.getText().trim();
            String sexValue = "Male";
            if (female.isSelected()) {
                sexValue = "Female";
            }
            if (isValidName(nameValue) && isValidEmail(emailValue) && isValidPhone(phoneValue)) {
                try {
                    FileWriter writer = new FileWriter("Form.txt");
                    writer.write("Name: " + nameValue + "\nEmail: " + emailValue + "\nPhone: " + phoneValue + "\nSex: "
                            + sexValue + "\n");
                    JOptionPane.showMessageDialog(null, "Form Submitted Successfully!");
                    writer.close();

                } catch (IOException d) {
                    System.out.println(d);
                } finally {
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
}
