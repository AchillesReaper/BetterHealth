import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SectionCustomer extends JPanel{
    public JPanel pnlControl = new JPanel();
    public JPanel pnlContent;
    public JTextField tfCustomerID, tfFirstName, tfLastName, tfMobile, tfEmail, tfAddress;
    public JComboBox cbGender, cbDOB_D, cbDOB_M, cbDOB_Y;
    public String customerID = "";
    public String firstName = "";
    public String lastName = "";
    public String mobile = "";
    public String email = "";
    public String address = "";
    public String gender = "";
    
    public String DOB_D = "";
    public String DOB_M = "";
    public String DOB_Y = "";
    public String queryString = "";

    public JButton btnAddCustomer, btnSearchCustomer, btnClearForm, btnUpdateCustomer, btnAddCard, btnAddTransaction, btnSearchTransaction, btnExportCsv;
    public JTable tbCustomer;
    public JScrollPane scpCustomer;
    public Customer targetCustomer;

    public SectionCustomer(){
        setBounds(10,70,1080,700);
        setLayout(null);

        constructControlPanel();
        constructContent("select * from customer");

    }
    

    public void constructControlPanel(){
        pnlControl.setBounds(730,0,350,550);
        pnlControl.setLayout(null);
        pnlControl.setBackground(Color.lightGray);

        // Create elements for the control panel
        JLabel lbCustomerID = new JLabel("Customer ID:");
        JLabel lbFirstName = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel lbGender = new JLabel("Gender");
        JLabel lbDOB = new JLabel("Date of Birth:");
        JLabel lbMobile = new JLabel("Mobile");
        JLabel lbEmail = new JLabel("email:");
        JLabel lbAddress = new JLabel("Address:");

        tfCustomerID = new JTextField(6);
        tfCustomerID.setEditable(false);
        tfFirstName = new JTextField("",6);
        tfLastName = new JTextField("",6);
        tfMobile = new JTextField("",6);
        tfEmail = new JTextField("",6);

        tfAddress = new JTextField("",6);

        String[] genArr = {"","Male","Female","Not Specified"};
        cbGender = new JComboBox(genArr);
        cbGender.addActionListener(e -> gender = (String) cbGender.getSelectedItem());

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
        cbDOB_D = new JComboBox(dayCB);
        cbDOB_M = new JComboBox(monthCB);
        cbDOB_Y = new JComboBox(yearCB);
        cbDOB_D.addActionListener(e -> DOB_D = (String) cbDOB_D.getSelectedItem());
        cbDOB_M.addActionListener(e -> DOB_M = (String) cbDOB_M.getSelectedItem());
        cbDOB_Y.addActionListener(e -> DOB_Y = (String) cbDOB_Y.getSelectedItem());

        JPanel pnlDOB = new JPanel(null);
        pnlDOB.setBackground(Color.lightGray);
        pnlDOB.setLayout(new FlowLayout(FlowLayout.LEADING, 0,5));
        pnlDOB.add(cbDOB_D);
        pnlDOB.add(cbDOB_M);
        pnlDOB.add(cbDOB_Y);

        btnAddCustomer = new JButton("Add New Customer");
        btnSearchCustomer = new JButton("Search Customer");
        btnClearForm = new JButton("Clear Form");
        btnUpdateCustomer = new JButton("Update Customer");
        btnAddCard = new JButton("Add Health Fund");
        btnAddTransaction = new JButton("New Transaction");
        btnSearchTransaction = new JButton("Search Transaction");


        btnUpdateCustomer.setBackground(Color.red);
        btnAddCustomer.addActionListener(e -> {
            if (tfCustomerID.getText().length()>0){
                JOptionPane.showMessageDialog(null,"Please clear the form first before adding a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (inputValidation()) {
                    DB_CRUD.addCustomer(targetCustomer);
                    constructContent("select * from customer");
                    add(pnlContent);
                    setVisible(true);
                }
            }
        });
        btnSearchCustomer.addActionListener(e -> {
            if(searchValidation()){
                constructContent(queryString);
                add(pnlContent);
                setVisible(true);
            }
        });
        btnClearForm.addActionListener(e -> {
            SectionCustomer sectionCustomer = new SectionCustomer();
            new NewMain(sectionCustomer);
        });
        btnUpdateCustomer.addActionListener(e ->{
            customerID = tfCustomerID.getText();
            if (customerID.length() == 0){
                JOptionPane.showMessageDialog(null,"Please select a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (inputValidation()){
                    targetCustomer.setCustomerID(customerID);
                    DB_CRUD.updateCustomer(targetCustomer);
                    constructContent("select * from customer");
                }
            }
        });
        btnAddTransaction.addActionListener(e -> {
            if (tfCustomerID.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Please select a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
            } else {
                new Pop_newTrsc(tfCustomerID.getText());
            }
        });
        btnAddCard.addActionListener(e -> {
            if (tfCustomerID.getText().length()>0){
                new Pop_Card(tfCustomerID.getText(), tfFirstName.getText(), tfLastName.getText());
            }else{
                JOptionPane.showMessageDialog(null,"Please select a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnSearchTransaction.addActionListener(e -> {
            customerID = tfCustomerID.getText();
            if (customerID.length() == 0){
                JOptionPane.showMessageDialog(null,"Please select a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
            }else{
                SectionTransaction sectionInput = new SectionTransaction(customerID);
                new NewMain(sectionInput);
            }
        });

        //setting constrains for the elements
        lbCustomerID.setBounds(10,10,90,30);
        lbFirstName.setBounds(10,50,90,30);
        lastNameLabel.setBounds(10,90,90,30);
        lbMobile.setBounds(10,130,90,30);
        lbEmail.setBounds(10,170,90,30);
        lbAddress.setBounds(10,210,90,30);
        lbGender.setBounds(10,250,90,30);
        lbDOB.setBounds(10,290,90,30);

        tfCustomerID.setBounds(100,10,240,30);
        tfFirstName.setBounds(100,50,240,30);
        tfLastName.setBounds(100,90,240,30);
        tfMobile.setBounds(100,130,240,30);
        tfEmail.setBounds(100,170,240,30);
        tfAddress.setBounds(100,210,240,30);
        cbGender.setBounds(100,250,240,30);
        pnlDOB.setBounds(100,290,240,30);

        btnAddCustomer.setBounds(10,350,160,36);
        btnSearchCustomer.setBounds(10,400,160,36);
        btnClearForm.setBounds(10,450,160,36);
        btnUpdateCustomer.setBounds(10,500,160,36);
        btnAddCard.setBounds(180,350,160,36);
        btnAddTransaction.setBounds(180,400,160,36);
        btnSearchTransaction.setBounds(180,450,160,36);

        //add all element to the control panel
        pnlControl.add(lbCustomerID);
        pnlControl.add(tfCustomerID);
        pnlControl.add(lbFirstName);
        pnlControl.add(tfFirstName);
        pnlControl.add(lastNameLabel);
        pnlControl.add(tfLastName);
        pnlControl.add(lbMobile);
        pnlControl.add(tfMobile);
        pnlControl.add(lbGender);
        pnlControl.add(cbGender);
        pnlControl.add(lbDOB);
        pnlControl.add(pnlDOB);
        pnlControl.add(lbEmail);
        pnlControl.add(tfEmail);
        pnlControl.add(lbAddress);
        pnlControl.add(tfAddress);
        pnlControl.add(btnAddCustomer);
        pnlControl.add(btnSearchCustomer);
        pnlControl.add(btnClearForm);
        pnlControl.add(btnUpdateCustomer);
        pnlControl.add(btnAddCard);
        pnlControl.add(btnAddTransaction);
        pnlControl.add(btnSearchTransaction);

        add(pnlControl);
        setVisible(true);
    }

    public void constructContent(String queryString){
        if(pnlContent != null){remove(pnlContent);}
        pnlContent = new JPanel(null);
        pnlContent.setBounds(0,0,720,550);
        pnlContent.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Customer Information");
        contentTitle.setBounds(10,10,200,30);
        btnExportCsv = new JButton("Export CSV");
        btnExportCsv.setBounds(610,10,100,20);

        Object[][] data = DB_CRUD.searchCustomer(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No customer record is found.","",JOptionPane.PLAIN_MESSAGE);
        }
        String[] colName = {"Cus. ID", "First Name", "Last Name","Mobile","email","Address","Gender","DOB_Y","DOB_M","DOB_D"};
        tbCustomer = new JTable(data, colName);
        tbCustomer.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbCustomer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbCustomer.setAutoCreateRowSorter(true);
        tbCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbCustomer.getSelectedRow();
                if (row == -1) {
                    return;
                }
                tfCustomerID.setText((tbCustomer.getModel().getValueAt(row,0)).toString());
                tfFirstName.setText((tbCustomer.getModel().getValueAt(row,1)).toString());
                tfLastName.setText((tbCustomer.getModel().getValueAt(row,2)).toString());
                tfMobile.setText((tbCustomer.getModel().getValueAt(row,3)).toString());
                tfEmail.setText((tbCustomer.getModel().getValueAt(row,4)).toString());
                cbGender.setSelectedIndex(0);
                cbDOB_Y.setSelectedIndex(0);
                cbDOB_M.setSelectedIndex(0);
                cbDOB_D.setSelectedIndex(0);
            }
        });

        
        btnExportCsv.addActionListener(e -> {
//            JFileChooser fileChooser = new JFileChooser("G:/My Drive/Database Backup/Report");

            JFileChooser fileChooser = new JFileChooser("G:/我的云端硬盘/Database Backup/Report");
            fileChooser.setDialogTitle("Choose Save Destination");
            FileNameExtensionFilter filter=new FileNameExtensionFilter("*.csv", "csv");
            fileChooser.setFileFilter(filter);

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStamp = formatter.format(date);
            fileChooser.setSelectedFile(new File("customer_"+dateStamp+".csv"));

            int userSelection  = fileChooser.showSaveDialog(btnExportCsv);
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
                    JOptionPane.showMessageDialog(btnExportCsv, "SUCCESSFULLY SAVED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                    bufferedWriter.close();
                    fileWriter.close();
                    if (!fileToSave.getName().endsWith(".csv")) {
                        fileToSave=new File(fileToSave.getPath()+".csv");
                        System.out.println(fileToSave.getPath());
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(btnExportCsv, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        scpCustomer = new JScrollPane(tbCustomer);
        scpCustomer.setBounds(10,40,700,500);
        tbCustomer.setFillsViewportHeight(true);

        pnlContent.add(contentTitle);
        pnlContent.add(btnExportCsv);
        pnlContent.add(scpCustomer);

        add(pnlContent);
        setVisible(true);
    }

    public boolean inputValidation(){
        //validation can be increase upon request
        firstName = tfFirstName.getText();
        lastName = tfLastName.getText();
        mobile = tfMobile.getText();
        email = tfEmail.getText();
        address = tfAddress.getText();
        
        if (firstName.length() < 2 || lastName.length() < 2 || mobile.length() != 10){
            JOptionPane.showMessageDialog(null,"Please check following input:\nFirst Name \nLast Name \nmobile","Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            targetCustomer = new Customer(firstName,lastName, mobile,email,address,gender,DOB_Y,DOB_M,DOB_D);
            System.out.println("target created");
            return true;
        }
    }

    public Boolean searchValidation(){
        ArrayList<String> conditions = new ArrayList<>();
        queryString = "select * from customer";
        if (tfCustomerID.getText().length() == 0){
            if (tfFirstName.getText().length() > 0){
                conditions.add(" where firstName LIKE '%"+tfFirstName.getText()+"%'");
            }
            if (tfLastName.getText().length() > 0){
                conditions.add(" where lastName LIKE '%"+tfLastName.getText()+"%'");
            }
            if (tfMobile.getText().length() > 0){
                conditions.add(" where mobile LIKE '%"+tfMobile.getText()+"%'");
            }
            if (tfEmail.getText().length() > 0){
                conditions.add(" where email LIKE '%"+tfEmail.getText()+"%'");
            }
            if (tfAddress.getText().length() > 0){
                conditions.add(" where address LIKE '%"+tfAddress.getText()+"%'");
            }
            if (gender.length()>0){
                conditions.add(" where gender LIKE '"+gender+"'");
            }
            if (DOB_Y.length()>0){
                conditions.add(" where DOB_Y LIKE '"+DOB_Y+"'");
            }
            if (DOB_M.length()>0){
                conditions.add(" where DOB_M LIKE '"+DOB_M+"'");
            }
            if (DOB_D .length()>0){
                conditions.add(" where DOB_D LIKE '"+DOB_D+"'");
            }
        } else {
            conditions.add(" where customerID = '"+ tfCustomerID.getText() +"'");
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
