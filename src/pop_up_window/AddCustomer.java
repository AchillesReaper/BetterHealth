package pop_up_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AddCustomer extends JFrame implements ActionListener {
    public JComboBox genderCB, DOBDayCB, DOBMonthCB, DOBYearCB;
    public String gender, DOBDay, DOBMonth, DOBYear;



    public AddCustomer(){
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel mediCareLabel = new JLabel("Medi-Care:");
        JLabel mobileLabel = new JLabel("Mobile");
        JLabel genderLabel = new JLabel("Gender");
        JLabel DOBLabel = new JLabel("Date of Birth:");
        JLabel emailLabel = new JLabel("email:");
        JLabel addressLabel = new JLabel("Address:");

        JTextField firstNameTF = new JTextField(10);
        JTextField lastNameTF = new JTextField(10);
        JTextField mediCareTF = new JTextField(10);
        JTextField mobileTF = new JTextField(10);

        String[] genArr = {"Female","Male","NS"};
        genderCB = new JComboBox(genArr);
        genderCB.addActionListener(this);

//        JTextField DOBTF = new JTextField(10); //date selector?
        String[] dayCB = new String[31];
        String[] monthCB = new String[12];
        String[] yearCB = new String[100];
        for (int i = 0; i < 31; i++){
            dayCB[i] = Integer.toString(i + 1);
        }
        for (int i = 0; i < 12; i++){
            monthCB[i] = Integer.toString(i + 1);
        }
        for (int i = 0; i < 100; i++) {
            yearCB[i] = Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - i);
        }
        DOBDayCB = new JComboBox(dayCB);
        DOBMonthCB = new JComboBox(monthCB);
        DOBYearCB = new JComboBox(yearCB);
        DOBDayCB.addActionListener(this);
        DOBMonthCB.addActionListener(this);
        DOBYearCB.addActionListener(this);

        JPanel DOBPanel = new JPanel();
        DOBPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        DOBPanel.add(DOBDayCB);
        DOBPanel.add(DOBMonthCB);
        DOBPanel.add(DOBYearCB);

        JTextField emailTF = new JTextField(10);
        JTextField addressTF = new JTextField(10);

//        JButton addToDB = new JButton("Add New Customer");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add New Customer");
        setSize(500,600);
        setLayout(new GridLayout(9,2,10,10));
        add(firstNameLabel);
        add(firstNameTF);
        add(lastNameLabel);
        add(lastNameTF);
        add(mediCareLabel);
        add(mediCareTF);
        add(mobileLabel);
        add(mobileTF);
        add(genderLabel);
        add(genderCB);
        add(DOBLabel);
        add(DOBPanel);
        add(emailLabel);
        add(emailTF);
        add(addressLabel);
        add(addressTF);


        setVisible(true);
    }

//method: add to database

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == genderCB){
            gender = (String) genderCB.getSelectedItem();
        }
        if(e.getSource() == DOBDayCB){
            DOBDay = (String) DOBDayCB.getSelectedItem();
        }
        if(e.getSource() == DOBMonthCB){
            DOBMonth = (String) DOBMonthCB.getSelectedItem();
        }
        if(e.getSource() == DOBYearCB){
            DOBYear = (String) DOBYearCB.getSelectedItem();
        }


    }
}
