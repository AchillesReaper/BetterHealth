import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class Pop_AddCustomer extends JFrame implements ActionListener {

    public JTextField firstNameTF, lastNameTF, mediCareTF, mobileTF, emailTF, addressTF;
    public JComboBox genderCB, DOBDayCB, DOBMonthCB, DOBYearCB;
    public String gender, DOBDay, DOBMonth, DOBYear;
    public JButton addToDB;



    public Pop_AddCustomer(){
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel mediCareLabel = new JLabel("Medi-Care:");
        JLabel mobileLabel = new JLabel("Mobile");
        JLabel genderLabel = new JLabel("Gender");
        JLabel DOBLabel = new JLabel("Date of Birth:");
        JLabel emailLabel = new JLabel("email:");
        JLabel addressLabel = new JLabel("Address:");

        firstNameTF = new JTextField(10);
        lastNameTF = new JTextField(10);
        mediCareTF = new JTextField(10);
        mobileTF = new JTextField(10);

        String[] genArr = {"Female","Male","NS"};
        genderCB = new JComboBox(genArr);
        genderCB.addActionListener(this);

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
        DOBPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,10));
        DOBPanel.add(DOBDayCB);
        DOBPanel.add(DOBMonthCB);
        DOBPanel.add(DOBYearCB);

        emailTF = new JTextField(10);
        addressTF = new JTextField(10);

        addToDB = new JButton("Add New Customer");
        addToDB.addActionListener(this);

        firstNameLabel.setBounds(10,10,100,40);
        lastNameLabel.setBounds(10,60,100,40);
        mediCareLabel.setBounds(10,110,100,40);
        mobileLabel.setBounds(10,160,100,40);
        genderLabel.setBounds(10,210,100,40);
        DOBLabel.setBounds(10,260,100,40);
        emailLabel.setBounds(10,310,100,40);
        addressLabel.setBounds(10,360,100,40);

        firstNameTF.setBounds(120,10,260,40);
        lastNameTF.setBounds(120,60,260,40);
        mediCareTF.setBounds(120,110,260,40);
        mobileTF.setBounds(120,160,260,40);
        genderCB.setBounds(120,210,260,40);
        DOBPanel.setBounds(120,260,260,40);
        emailTF.setBounds(120,310,260,40);
        addressTF.setBounds(120,360,260,40);

        addToDB.setBounds(125,420,150,30);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add New Customer");
        setSize(400,500);
        setResizable(false);
        setLayout(null);
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
        add(addToDB);

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
        if(e.getSource() == addToDB){
            String DOB = DOBDay + "/" + DOBMonth + "/" + DOBYear;
            DB_CRUD.addCstmToDB(firstNameTF.getText(), lastNameTF.getText(),
                    mediCareTF.getText(),mobileTF.getText(),gender,DOB,
                    emailTF.getText(), addressTF.getText());
            this.dispose();
        }

    }

}
