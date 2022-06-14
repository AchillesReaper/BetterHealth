import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CustomerControl extends JPanel implements ActionListener {
    public JLabel getCstmIDLabel;
    public JTextField firstNameTF, lastNameTF, mobileTF, emailTF, addressTF;
    public JComboBox genderCB, DOBDayCB, DOBMonthCB, DOBYearCB;
    public String gender, DOBDay, DOBMonth, DOBYear;
    public JButton addNewBtn, searchBtn, deleteBtn, addCardBtn;

    public CustomerControl(){
        this.setBounds(750,70,340,600);
        this.setLayout(null);

        // Create elements for the control panel
        JLabel cstmIDLabel = new JLabel("Customer: ");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel genderLabel = new JLabel("Gender");
        JLabel DOBLabel = new JLabel("Date of Birth:");
        JLabel mobileLabel = new JLabel("Mobile");
        JLabel emailLabel = new JLabel("email:");
        JLabel addressLabel = new JLabel("Address:");

        getCstmIDLabel = new JLabel();
        firstNameTF = new JTextField(6);
        lastNameTF = new JTextField(6);
        mobileTF = new JTextField(6);

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
        DOBPanel.setLayout(null);
        DOBPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,5));
        DOBPanel.add(DOBDayCB);
        DOBPanel.add(DOBMonthCB);
        DOBPanel.add(DOBYearCB);

        emailTF = new JTextField(10);
        addressTF = new JTextField(10);

        addNewBtn = new JButton("Add New Customer");
        searchBtn = new JButton("Search");
        deleteBtn = new JButton("Delete Customer");
        addNewBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        //setting constrains for the elements
        cstmIDLabel.setBounds(10,0,90,30);
        firstNameLabel.setBounds(10,40,90,30);
        lastNameLabel.setBounds(10,80,90,30);
        mobileLabel.setBounds(10,120,90,30);
        genderLabel.setBounds(10,160,90,30);
        DOBLabel.setBounds(10,200,90,30);
        emailLabel.setBounds(10,240,90,30);
        addressLabel.setBounds(10,280,90,30);

        getCstmIDLabel.setBounds(100,0,240,30);
        firstNameTF.setBounds(100,40,240,30);
        lastNameTF.setBounds(100,80,240,30);
        mobileTF.setBounds(100,120,240,30);
        genderCB.setBounds(100,160,240,30);
        DOBPanel.setBounds(100,200,240,30);
        emailTF.setBounds(100,240,240,30);
        addressTF.setBounds(100,280,240,30);

        addNewBtn.setBounds(10,350,160,36);
        searchBtn.setBounds(10,400,160,36);
        deleteBtn.setBounds(10,450,160,36);

        //add all element to the control panel
        this.add(cstmIDLabel);
        this.add(getCstmIDLabel);
        this.add(firstNameLabel);
        this.add(firstNameTF);
        this.add(lastNameLabel);
        this.add(lastNameTF);
        this.add(mobileLabel);
        this.add(mobileTF);
        this.add(genderLabel);
        this.add(genderCB);
        this.add(DOBLabel);
        this.add(DOBPanel);
        this.add(emailLabel);
        this.add(emailTF);
        this.add(addressLabel);
        this.add(addressTF);
        this.add(addNewBtn);
        this.add(searchBtn);
        this.add(deleteBtn);

        this.setVisible(true);
    }

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

        if(e.getSource() == searchBtn){
            Customer cstm = new Customer();
            cstm.setCustomerID(getCstmIDLabel.getText());
            cstm.setFirstName(firstNameTF.getText());
            cstm.setLastName(lastNameTF.getText());
            cstm.setMobile(mobileTF.getText());
            cstm.setGender(gender);
            cstm.setDOB(DOBDay+'/'+DOBMonth+"/"+DOBYear);
            cstm.setEmail(emailTF.getText());
            cstm.setAddress(addressTF.getText());

//            JPanel searchResult = new CustomerContent();
            new CustomerContent().searchCustomer(cstm);
        }
    }
}
