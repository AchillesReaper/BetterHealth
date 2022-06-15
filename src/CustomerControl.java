import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class CustomerControl extends JPanel implements ActionListener {
    public JPanel PnlControl = new JPanel();
    public JPanel PnlContent;
    public JTextField customerIdTF, firstNameTF, lastNameTF, mobileTF, emailTF, addressTF;
    public JComboBox genderCB, DOBDayCB, DOBMonthCB, DOBYearCB;
    public String gender, DOBDay, DOBMonth, DOBYear, queryString;
    public JButton addNewBtn, searchBtn, clearForm, deleteBtn, addCardBtn;
    public JTable cstmTable;
    public JScrollPane scrollPan;

    public Customer targetCustomer;

    public CustomerControl(){
        setBounds(10,70,1080,700);
        setLayout(null);

        constructControlPanel();
        constructContent("select * from customer");

        add(PnlControl);
        add(PnlContent);
        setVisible(true);
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

        if(e.getSource() == addNewBtn){
            if (inputValidation()) {
                DB_CRUD.addCstmToDB(targetCustomer);
                constructContent("select * from customer");
                add(PnlContent);
                setVisible(true);
            } else{
                System.out.println("sth wrong");
            }
        }

        if(e.getSource() == searchBtn){
            if(searchValidation()){
                constructContent(queryString);
                add(PnlContent);
                setVisible(true);
            }
        }
    }
    public void constructControlPanel(){
        PnlControl.setBounds(730,0,350,600);
        PnlControl.setLayout(null);
        PnlControl.setBackground(Color.lightGray);

        // Create elements for the control panel
        JLabel cstmIDLabel = new JLabel("Customer ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel genderLabel = new JLabel("Gender");
        JLabel DOBLabel = new JLabel("Date of Birth:");
        JLabel mobileLabel = new JLabel("Mobile");
        JLabel emailLabel = new JLabel("email:");
        JLabel addressLabel = new JLabel("Address:");

        customerIdTF = new JTextField(6);
        customerIdTF.setEditable(false);
        firstNameTF = new JTextField(6);
        lastNameTF = new JTextField(6);
        mobileTF = new JTextField(6);
        emailTF = new JTextField(6);
        addressTF = new JTextField(6);

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

        addNewBtn = new JButton("Add New Customer");
        searchBtn = new JButton("Search");
        clearForm = new JButton("Clear");
        deleteBtn = new JButton("Delete Customer");
        addNewBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        clearForm.addActionListener(this);
        deleteBtn.addActionListener(this);

        //setting constrains for the elements
        cstmIDLabel.setBounds(10,0,90,30);
        firstNameLabel.setBounds(10,40,90,30);
        lastNameLabel.setBounds(10,80,90,30);
        mobileLabel.setBounds(10,120,90,30);
        emailLabel.setBounds(10,160,90,30);
        addressLabel.setBounds(10,200,90,30);
        genderLabel.setBounds(10,240,90,30);
        DOBLabel.setBounds(10,280,90,30);

        customerIdTF.setBounds(100,0,240,30);
        firstNameTF.setBounds(100,40,240,30);
        lastNameTF.setBounds(100,80,240,30);
        mobileTF.setBounds(100,120,240,30);
        emailTF.setBounds(100,160,240,30);
        addressTF.setBounds(100,200,240,30);
        genderCB.setBounds(100,240,240,30);
        DOBPanel.setBounds(100,280,240,30);

        addNewBtn.setBounds(10,350,160,36);
        searchBtn.setBounds(10,400,160,36);
        clearForm.setBounds(10,450,160,36);
        deleteBtn.setBounds(10,500,160,36);

        //add all element to the control panel
        PnlControl.add(cstmIDLabel);
        PnlControl.add(customerIdTF);
        PnlControl.add(firstNameLabel);
        PnlControl.add(firstNameTF);
        PnlControl.add(lastNameLabel);
        PnlControl.add(lastNameTF);
        PnlControl.add(mobileLabel);
        PnlControl.add(mobileTF);
        PnlControl.add(genderLabel);
        PnlControl.add(genderCB);
        PnlControl.add(DOBLabel);
        PnlControl.add(DOBPanel);
        PnlControl.add(emailLabel);
        PnlControl.add(emailTF);
        PnlControl.add(addressLabel);
        PnlControl.add(addressTF);
        PnlControl.add(addNewBtn);
        PnlControl.add(searchBtn);
        PnlControl.add(clearForm);
        PnlControl.add(deleteBtn);
    }

    public void constructContent(String queryString){
        if(PnlContent != null){remove(PnlContent);}
        PnlContent = new JPanel();
        PnlContent.setBounds(0,0,720,600);
        PnlContent.setLayout(null);
        PnlContent.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Customer");
        contentTitle.setBounds(10,0,200,30);

        Object[][] data = DB_CRUD.searchCstm(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No Record is found.","",JOptionPane.PLAIN_MESSAGE);
        }
        String[] colName = {"Cus. ID", "First Name", "Last Name","Mobile","email","Address","Gender","DOB_Y","DOB_M","DOB_D"};
        cstmTable = new JTable(data, colName);
        cstmTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        cstmTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

        scrollPan = new JScrollPane(cstmTable);
        scrollPan.setBounds(10,40,700,500);
        cstmTable.setFillsViewportHeight(true);

        PnlContent.add(contentTitle);
        PnlContent.add(scrollPan);
    }

    public Boolean inputValidation(){
        if (firstNameTF.getText().length() < 2 ||
        lastNameTF.getText().length() < 2 ||
        mobileTF.getText().length() != 10){
            JOptionPane.showMessageDialog(null,"Please check following input:\nFirst Name \nLast Name \nmobile","Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            targetCustomer = new Customer();
            targetCustomer.setCustomerID(customerIdTF.getText());
            targetCustomer.setFirstName(firstNameTF.getText());
            targetCustomer.setLastName(lastNameTF.getText());
            targetCustomer.setMobile(mobileTF.getText());
            targetCustomer.setEmail(emailTF.getText());
            targetCustomer.setAddress(addressTF.getText());
            targetCustomer.setGender(gender);
            targetCustomer.setDOB_Y(DOBYear);
            targetCustomer.setDOB_M(DOBMonth);
            targetCustomer.setDOB_D(DOBDay);
            System.out.println("target created");
            return true;
        }
    }

    public Boolean searchValidation(){
        ArrayList<String> conditions = new ArrayList<>();
        queryString = "select * from customer";
        if (firstNameTF.getText().length() > 0){
            conditions.add(" where firstName LIKE '%"+firstNameTF.getText()+"%'");
        }
        if (lastNameTF.getText().length() > 0){
            conditions.add(" where lastName LIKE '%"+lastNameTF.getText()+"%'");
        }
        if (mobileTF.getText().length() > 0){
            conditions.add(" where mobile LIKE '%"+mobileTF.getText()+"%'");
        }
        if (emailTF.getText().length() > 0){
            conditions.add(" where email LIKE '%"+emailTF.getText()+"%'");
        }
        if (addressTF.getText().length() > 0){
            conditions.add(" where address LIKE '%"+addressTF.getText()+"%'");
        }
        if (gender != null){
            conditions.add(" where gender LIKE '"+gender+"'");
        }
        if (DOBYear != null){
            conditions.add(" where DOB_Y LIKE '"+DOBYear+"'");
        }
        if (DOBMonth != null){
            conditions.add(" where DOB_M LIKE '"+DOBMonth+"'");
        }
        if (DOBDay != null){
            conditions.add(" where DOB_D LIKE '"+DOBDay+"'");
        }
        switch (conditions.size()) {
            case 0 -> {
                JOptionPane.showMessageDialog(null, "No condition is set", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 1 -> {
                queryString += conditions.get(0);
                System.out.println(queryString);
                return true;
            }
            default -> {
                queryString += conditions.get(0);
                for (int i = 1; i < conditions.size(); i++) {
                    queryString += " and" + conditions.get(i);
                }
                System.out.println(queryString);
                return true;
            }
        }
    }
}
