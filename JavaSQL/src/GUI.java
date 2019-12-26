import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GUI extends JFrame {
    Database db;
    Vector<Employe> employes;
    JLabel id_label;
    JTextField id_tf;

    JLabel fname_label;
    JTextField fname_tf;

    JLabel lname_label;
    JTextField lname_tf;

    JLabel email_label;
    JTextField email_tf;

    JLabel phone_label;
    JTextField phone_tf;

    JButton new_btn;
    JButton update_btn;
    JButton delete_btn;
    JButton first_btn;
    JButton prev_btn;
    JButton next_btn;
    JButton last_btn;
    JButton save_btn;
    Employe currentEmp;

    public static void main(String[] args) {
        GUI myGUI = new GUI();
        myGUI.setSize(600, 600);
        myGUI.setVisible(true);
    }

    public GUI() {
        this.setLayout(new FlowLayout());
        id_label = new JLabel("ID");
        id_tf = new JTextField(32);
        id_tf.setEditable(false);
        fname_label = new JLabel("First Name");
        fname_tf = new JTextField(32);

        lname_label = new JLabel("Last Name");
        lname_tf = new JTextField(32);

        email_label = new JLabel("Email");
        email_tf = new JTextField(32);

        phone_label = new JLabel("Phone");
        phone_tf = new JTextField(32);

        new_btn = new JButton("NEW");
        update_btn = new JButton("UPDATE");
        delete_btn = new JButton("DELETE");
        first_btn = new JButton("FIRST");
        prev_btn = new JButton("PREVIOUS");
        next_btn = new JButton("NEXT");
        last_btn = new JButton("LAST");
        save_btn = new JButton("SAVE");
        save_btn.setEnabled(false);
        add(id_label);
        add(id_tf);
        add(fname_label);
        add(fname_tf);
        add(lname_label);
        add(lname_tf);
        add(email_label);
        add(email_tf);
        add(phone_label);
        add(phone_tf);

        add(new_btn);
        add(update_btn);
        add(delete_btn);
        add(first_btn);
        add(prev_btn);
        add(next_btn);
        add(last_btn);
        add(save_btn);

        db = new Database();
        getEmployees();
        showFirstEmp();

        next_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextEmp();
            }
        });

        prev_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevEmp();
            }
        });

        last_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastEmp();
            }
        });

        first_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFirstEmp();
            }
        });

        new_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new_btn.setEnabled(false);
                save_btn.setEnabled(true);
                clearTextFields();
            }
        });

        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getNewEmp() != null) {
                    db.insertEmp(getNewEmp());
                    save_btn.setEnabled(false);
                    new_btn.setEnabled(true);
                    getEmployees();
                    lastEmp();
                }
            }
        });

        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.deleteEmp(currentEmp);
                getEmployees();
                nextEmp();
            }
        });

        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employe updatedEmp = getNewEmp();
                db.updateEmp(currentEmp.getId(), updatedEmp);
                getEmployees();
            }
        });
    }


    public void getEmployees() {
        employes = db.getEmps();
    }

    public void showFirstEmp() {
        currentEmp = employes.firstElement();
        updateTextFields(currentEmp);
    }

    public void updateTextFields(Employe e) {
        id_tf.setText(String.valueOf(e.getId()));
        fname_tf.setText(e.getFirstName());
        lname_tf.setText(e.getLastName());
        email_tf.setText(e.getEmail());
        phone_tf.setText(e.getPhone());
    }

    public void nextEmp() {
        if (employes.indexOf(currentEmp) < employes.size() - 1) {
            currentEmp = employes.get(employes.indexOf(currentEmp) + 1);
            updateTextFields(currentEmp);
        }
    }

    public void prevEmp() {
        if (employes.indexOf(currentEmp) > 0) {
            currentEmp = employes.get(employes.indexOf(currentEmp) - 1);
            updateTextFields(currentEmp);
        }
    }

    public void lastEmp() {
        currentEmp = employes.lastElement();
        updateTextFields(currentEmp);
    }

    public void clearTextFields() {
        id_tf.setText("");
        fname_tf.setText("");
        lname_tf.setText("");
        email_tf.setText("");
        phone_tf.setText("");
    }

    public Employe getNewEmp() {
        Employe e = null;
        if (!fname_tf.getText().equals("") && !lname_tf.getText().equals("") &&
                !email_tf.getText().equals("") && !phone_tf.getText().equals("")) {
            e = new Employe();
            e.setFirstName(fname_tf.getText());
            e.setLastName(lname_tf.getText());
            e.setEmail(email_tf.getText());
            e.setPhone(phone_tf.getText());
        }
        return e;
    }
}
