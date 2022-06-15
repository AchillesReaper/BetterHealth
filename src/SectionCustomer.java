import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class SectionCustomer extends JPanel implements ActionListener {
    public JPanel PnlControl = new JPanel();
    public JPanel PnlContent;
    public JTextField customerIdTF, firstNameTF, lastNameTF, mobileTF, emailTF, addressTF;
    public JComboBox genderCB, DOBDayCB, DOBMonthCB, DOBYearCB;
    public String gender, DOBDay, DOBMonth, DOBYear, queryString;
    public JButton addNewBtn, searchBtn, clearFormBtn, deleteBtn, addTransactionBtn, addCardBtn, exportCsvBtn;
    public JTable cstmTable;
    public JScrollPane scrollPan;

    public Customer targetCustomer;

    public SectionCustomer(){
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
        PnlControl.setBounds(730,0,350,550);
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
        firstNameTF = new JTextField("",6);
        lastNameTF = new JTextField("",6);
        mobileTF = new JTextField("",6);
        emailTF = new JTextField("",6);
        addressTF = new JTextField("",6);

        String[] genArr = {"","Male","Female","Not Specified"};
        genderCB = new JComboBox(genArr);
        genderCB.addActionListener(this);

        String[] dayCB = new String[32];
        String[] monthCB = new String[13];
        String[] yearCB = new String[101];
        dayCB[0] = "";
        monthCB[0] = "";
        yearCB[0] = "";
        for (int i = 1; i < 32; i++){
            dayCB[i] = Integer.toString(i);
        }
        for (int i = 1; i < 13; i++){
            monthCB[i] = Integer.toString(i);
        }
        for (int i = 1; i < 101; i++) {
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
        searchBtn = new JButton("Search Customer");
        clearFormBtn = new JButton("Clear Form");
        deleteBtn = new JButton("Delete Customer");
        addTransactionBtn = new JButton("New Transaction");
        addCardBtn = new JButton("Add Medi Card");
        addNewBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        clearFormBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerIdTF.setText("");
                firstNameTF.setText("");
                lastNameTF.setText("");
                mobileTF.setText("");
                emailTF.setText("");
                addressTF.setText("");
                genderCB.setSelectedIndex(0);
                DOBYearCB.setSelectedIndex(0);
                DOBMonthCB.setSelectedIndex(0);
                DOBDayCB.setSelectedIndex(0);
            }
        });
        deleteBtn.addActionListener(this);
        addTransactionBtn.addActionListener(this);
        addCardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customerIdTF.getText().length()>0){
                    new Pop_Card(customerIdTF.getText(), firstNameTF.getText(), lastNameTF.getText());
                }else{
                    JOptionPane.showMessageDialog(null,"Please select a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //setting constrains for the elements
        cstmIDLabel.setBounds(10,10,90,30);
        firstNameLabel.setBounds(10,50,90,30);
        lastNameLabel.setBounds(10,90,90,30);
        mobileLabel.setBounds(10,130,90,30);
        emailLabel.setBounds(10,170,90,30);
        addressLabel.setBounds(10,210,90,30);
        genderLabel.setBounds(10,250,90,30);
        DOBLabel.setBounds(10,290,90,30);

        customerIdTF.setBounds(100,10,240,30);
        firstNameTF.setBounds(100,50,240,30);
        lastNameTF.setBounds(100,90,240,30);
        mobileTF.setBounds(100,130,240,30);
        emailTF.setBounds(100,170,240,30);
        addressTF.setBounds(100,210,240,30);
        genderCB.setBounds(100,250,240,30);
        DOBPanel.setBounds(100,290,240,30);

        addNewBtn.setBounds(10,350,160,36);
        searchBtn.setBounds(10,400,160,36);
        clearFormBtn.setBounds(10,450,160,36);
        deleteBtn.setBounds(10,500,160,36);
        addTransactionBtn.setBounds(180,350,160,36);
        addCardBtn.setBounds(180,400,160,36);

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
        PnlControl.add(clearFormBtn);
        PnlControl.add(deleteBtn);
        PnlControl.add(addTransactionBtn);
        PnlControl.add(addCardBtn);

    }

    public void constructContent(String queryString){
        if(PnlContent != null){remove(PnlContent);}
        PnlContent = new JPanel();
        PnlContent.setBounds(0,0,720,550);
        PnlContent.setLayout(null);
        PnlContent.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Customer Information");
        contentTitle.setBounds(10,10,200,30);
        exportCsvBtn = new JButton("Export CSV");
        exportCsvBtn.setBounds(595,10,120,30);

        Object[][] data = DB_CRUD.searchCstm(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No Record is found.","",JOptionPane.PLAIN_MESSAGE);
        }
        String[] colName = {"Cus. ID", "First Name", "Last Name","Mobile","email","Address","Gender","DOB_Y","DOB_M","DOB_D"};
        cstmTable = new JTable(data, colName);
        cstmTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        cstmTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cstmTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = cstmTable.getSelectedRow();
                customerIdTF.setText((cstmTable.getModel().getValueAt(row,0)).toString());
                firstNameTF.setText((cstmTable.getModel().getValueAt(row,1)).toString());
                lastNameTF.setText((cstmTable.getModel().getValueAt(row,2)).toString());
                mobileTF.setText((cstmTable.getModel().getValueAt(row,3)).toString());
                emailTF.setText((cstmTable.getModel().getValueAt(row,4)).toString());
                genderCB.setSelectedIndex(0);
                DOBYearCB.setSelectedIndex(0);
                DOBMonthCB.setSelectedIndex(0);
                DOBDayCB.setSelectedIndex(0);
            }
        });

        exportCsvBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Save Destination");

                int userSelection  = fileChooser.showSaveDialog(exportCsvBtn);
                if (userSelection == JFileChooser.APPROVE_OPTION){
                    File fileToSave = fileChooser.getSelectedFile();
                    try{
                        FileWriter fileWriter = new FileWriter(fileToSave);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("customerID,firstName,lastName,mobile,email,address,gender,DOB_Y,DOB_M,DOB_D");
                        bufferedWriter.newLine();
                        for (int i = 0; i < data.length; i++){
                            for (int j = 0; j < 10; j++){
                                if(j != 9){
                                    bufferedWriter.write(data[i][j] + ",");
                                }else{
                                    bufferedWriter.write(data[i][j] + "");
                                }
                            }
                            bufferedWriter.newLine();
                        }
                        JOptionPane.showMessageDialog(exportCsvBtn, "SUCCESSFULLY SAVED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(exportCsvBtn, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        scrollPan = new JScrollPane(cstmTable);
        scrollPan.setBounds(10,40,700,500);
        cstmTable.setFillsViewportHeight(true);

        PnlContent.add(contentTitle);
        PnlContent.add(exportCsvBtn);
        PnlContent.add(scrollPan);
    }

    public Boolean inputValidation(){
        //validation can be increase upon request
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
            if (gender == null){
                targetCustomer.setGender("");
            }else{
                targetCustomer.setGender(gender);
            }
            if(DOBYear == null){
                targetCustomer.setDOB_Y("");
            }else{
                targetCustomer.setDOB_Y(DOBYear);
            }
            if(DOBMonth == null){
                targetCustomer.setDOB_M("");
            }else{
                targetCustomer.setDOB_M(DOBMonth);
            }
            if(DOBDay == null){
                targetCustomer.setDOB_D("");
            }else{
                targetCustomer.setDOB_D(DOBDay);
            }
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
