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
import java.util.Date;

public class SectionTransaction extends JPanel {
    public JPanel pnlDetail, pnlContent, pnlFilter;

    public String transactionID = "";
    public String dateFull = "";
    public String dateY = "";
    public String dateM = "";
    public String dateD = "";
    public String customerID;
    public String firstName = "";
    public String lastName = "";
    public String serviceID = "";
    public String serviceName = "";
    public String serviceContent = "";
    public String servicePrice = "";
    public String cardID = "";
    public String cardIssuer = "";
    public String cardCover = "";
    public String cashPayment = "";
    public String queryString = "select * from detailed_transaction";


    public String[] listStartDate = new String[3];
    public String[] listEndDate = new String[3];
    public Integer[] trimListStartDate = new Integer[3];
    public Integer[] trimListEndDate = new Integer[3];



    public SectionTransaction(String customerID){
        this.customerID = customerID;

        setBounds(10,70,1080,700);
        setLayout(null);

        if (customerID.length() > 0){
            constructContentPan(queryString + " WHERE customerID = " + this.customerID);
        }else{
            constructContentPan(queryString);
        }
        constructFilter();
        showDetail("");
    }

    public void constructFilter(){
        pnlFilter = new JPanel(null);
        pnlFilter.setBounds(0,0,720,70);
        pnlFilter.setBackground(Color.lightGray);

        JLabel lbFilterTitle = new JLabel("Filer by Date");
        lbFilterTitle.setBounds(10,5,160,30);
        lbFilterTitle.setFont(new Font("SansSerif", Font.BOLD, 14));

        JCheckBox cbStartDate = new JCheckBox("Start Date:");
        JCheckBox cbEndDate = new JCheckBox("End Date: ");

        JTextField tfSdYear = new JTextField("YYYY");
        JTextField tfSdMonth = new JTextField("MM");
        JTextField tfSdDay = new JTextField("DD");
        JTextField tfEdYear = new JTextField("YYYY");
        JTextField tfEdMonth = new JTextField("MM");
        JTextField tfEdDay = new JTextField("DD");
        tfSdYear.setEditable(false);
        tfSdMonth.setEditable(false);
        tfSdDay.setEditable(false);
        tfEdYear.setEditable(false);
        tfEdMonth.setEditable(false);
        tfEdDay.setEditable(false);

        tfSdYear.setForeground(Color.lightGray);
        tfSdMonth.setForeground(Color.lightGray);
        tfSdDay.setForeground(Color.lightGray);
        tfEdYear.setForeground(Color.lightGray);
        tfEdMonth.setForeground(Color.lightGray);
        tfEdDay.setForeground(Color.lightGray);

        tfSdYear.setBackground(Color.darkGray);
        tfSdMonth.setBackground(Color.darkGray);
        tfSdDay.setBackground(Color.darkGray);
        tfEdYear.setBackground(Color.darkGray);
        tfEdMonth.setBackground(Color.darkGray);
        tfEdDay.setBackground(Color.darkGray);

        cbStartDate.addActionListener(e-> {
            if (cbStartDate.isSelected()){
                tfSdYear.setText("");
                tfSdMonth.setText("");
                tfSdDay.setText("");
                tfSdYear.setForeground(Color.BLACK);
                tfSdMonth.setForeground(Color.BLACK);
                tfSdDay.setForeground(Color.BLACK);
                tfSdYear.setEditable(true);
                tfSdYear.setBackground(Color.white);
                tfSdMonth.setEditable(true);
                tfSdMonth.setBackground(Color.white);
                tfSdDay.setEditable(true);
                tfSdDay.setBackground(Color.white);


            } else {
                tfSdYear.setText("YYYY");
                tfSdMonth.setText("MM");
                tfSdDay.setText("DD");
                tfSdYear.setEditable(false);
                tfSdYear.setForeground(Color.lightGray);
                tfSdYear.setBackground(Color.darkGray);
                tfSdMonth.setEditable(false);
                tfSdMonth.setForeground(Color.lightGray);
                tfSdMonth.setBackground(Color.darkGray);
                tfSdDay.setEditable(false);
                tfSdDay.setForeground(Color.lightGray);
                tfSdDay.setBackground(Color.darkGray);
            }

        });

        cbEndDate.addActionListener(e-> {
            if (cbEndDate.isSelected()){
                tfEdYear.setText("");
                tfEdMonth.setText("");
                tfEdDay.setText("");
                tfEdYear.setForeground(Color.BLACK);
                tfEdMonth.setForeground(Color.BLACK);
                tfEdDay.setForeground(Color.BLACK);
                tfEdYear.setEditable(true);
                tfEdMonth.setEditable(true);
                tfEdDay.setEditable(true);
                tfEdYear.setBackground(Color.white);
                tfEdMonth.setBackground(Color.white);
                tfEdDay.setBackground(Color.white);
            }else{
                tfEdYear.setText("YYYY");
                tfEdMonth.setText("MM");
                tfEdDay.setText("DD");
                tfEdYear.setForeground(Color.lightGray);
                tfEdMonth.setForeground(Color.lightGray);
                tfEdDay.setForeground(Color.lightGray);
                tfEdYear.setEditable(false);
                tfEdMonth.setEditable(false);
                tfEdDay.setEditable(false);
                tfEdYear.setBackground(Color.darkGray);
                tfEdMonth.setBackground(Color.darkGray);
                tfEdDay.setBackground(Color.darkGray);

            }

        });

        JButton btnFilter = new JButton("Search");

        btnFilter.addActionListener(e -> {
//            String qStr = "select * from detailed_transaction WHERE ";
            String condition1 = "";
            String condition2 = "";
            String ftCondition;
            if (!(cbStartDate.isSelected()) && !(cbEndDate.isSelected())){
                JOptionPane.showMessageDialog(this,"Date range is not selected","Error",JOptionPane.ERROR_MESSAGE);
            } else { //this scenario is triggered when one of the data filter box is ticked
                if (cbStartDate.isSelected()){
                    listStartDate[0] = tfSdYear.getText();
                    listStartDate[1] = tfSdMonth.getText();
                    listStartDate[2] = tfSdDay.getText();
                    if (inputValidation(listStartDate, "Start Date")){
                        condition1 = generateQueryStringToken("S");
                    }
                }

                if (cbEndDate.isSelected()){
                    listEndDate[0] = tfEdYear.getText();
                    listEndDate[1] = tfEdMonth.getText();
                    listEndDate[2] = tfEdDay.getText();
                    if (inputValidation(listEndDate, "End Date")){
                        condition2= generateQueryStringToken("E");
                    }
                }

                //check if starting date and end date is ticked, all these conditions are grouped into one statement
                if (condition1.length() > 0 && condition2.length() > 0){
                    ftCondition = condition1 + " and " + condition2;
                } else if (condition1.length() > 0) {
                    ftCondition = condition1;
                } else {
                    ftCondition = condition2;
                }

                //check if this session is about one client, if yes, all transactions are limited to this client.
                if (customerID.length()>0){
                    ftCondition += " and customerID = " + customerID;
                }

                String qStr = queryString +" WHERE " + ftCondition;

                System.out.println(qStr);

                constructContentPan(qStr);
            }
        });

        //add constrain;
        JPanel pnlStartD = new JPanel(new FlowLayout(FlowLayout.LEADING,0,0));
        pnlStartD.setBounds(170,5,210,30);
        pnlStartD.setBackground(Color.lightGray);
        pnlStartD.add(cbStartDate);
        pnlStartD.add(tfSdYear);
        pnlStartD.add(tfSdMonth);
        pnlStartD.add(tfSdDay);

        JPanel pnlEndD = new JPanel(new FlowLayout(FlowLayout.LEADING,0,0));
        pnlEndD.setBounds(170,35,210,30);
        pnlEndD.setBackground(Color.lightGray);
        pnlEndD.add(cbEndDate);
        pnlEndD.add(tfEdYear);
        pnlEndD.add(tfEdMonth);
        pnlEndD.add(tfEdDay);

        btnFilter.setBounds(610,10,100,20);

        //add to panel;
        pnlFilter.add(lbFilterTitle);
        pnlFilter.add(pnlStartD);
        pnlFilter.add(pnlEndD);
        pnlFilter.add(btnFilter);

        add(pnlFilter);
        setVisible(true);
    }
    public String generateQueryStringToken(String token){
        String qsToken = "";
        int ftYear;
        int ftMonth;
        int ftDay;

        if (token.equals("S")){
//            qsToken += " year >= "+listStartDate[0]+" and month >= "+listStartDate[1]+" and day >= "+listStartDate[2];
            ftYear = Integer.parseInt(listStartDate[0]);
            ftMonth = Integer.parseInt(listStartDate[1]);
            ftDay = Integer.parseInt(listStartDate[2]);
            qsToken += "(year*10000 + month*100 + day) >= "+(ftYear*10000 + ftMonth*100 + ftDay);
        }
        if (token.equals("E")){
//            qsToken += " year >= "+listEndDate[0]+" and month >= "+listEndDate[1]+" and day >= "+listEndDate[2];

            ftYear = Integer.parseInt(listEndDate[0]);
            ftMonth = Integer.parseInt(listEndDate[1]);
            ftDay = Integer.parseInt(listEndDate[2]);
            qsToken += "(year*10000 + month*100 + day) <= "+(ftYear*10000 + ftMonth*100 + ftDay);
        }
        System.out.println(qsToken);
        return qsToken;
    }

    public boolean inputValidation(String[] dateList, String name){
        boolean valResult = true;
        if (dateList[0].length()==0 || dateList[1].length()==0 || dateList[2].length()==0){
            valResult = false;
            JOptionPane.showMessageDialog(null, "Date input of "+name+" is not completed.\nConditions from "+name+" is ignored.", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                if (name.equals("Start Date")){
                    trimListStartDate[0] = Integer.parseInt(dateList[0]);
                    trimListStartDate[1] = Integer.parseInt(dateList[1]);
                    trimListStartDate[2] = Integer.parseInt(dateList[2]);
                }else{
                    trimListEndDate[0] = Integer.parseInt(dateList[0]);
                    trimListEndDate[1] = Integer.parseInt(dateList[1]);
                    trimListEndDate[2] = Integer.parseInt(dateList[2]);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Date input of "+name+" is not integer.\nConditions from "+name+" is ignored.", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                valResult = false;
            }
        }
        return valResult;
    }

    public void constructContentPan(String queryString){
        if(pnlContent != null){remove(pnlContent);}
        pnlContent = new JPanel(null);
        pnlContent.setBounds(0,75,720,475);
        pnlContent.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Transactions of Better Health");
        contentTitle.setBounds(10,5,300,30);
        contentTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton exportCsvBtn = new JButton("Export CSV");
        exportCsvBtn.setBounds(610,10,100,20);

        Object[][] data = DB_CRUD.searchTransaction(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No transaction record is found.","",JOptionPane.PLAIN_MESSAGE);
        }
        String[] colName = {"Trsc. ID", "Year", "Month", "Day", "Customer ID", "First Name", "Last Name", "Service ID", "Service", "Content", "Price", "Card", "Issuer", "Card Cover", "Cash Paid"};
        JTable tbTransaction = new JTable(data, colName);
        tbTransaction.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbTransaction.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbTransaction.setAutoCreateRowSorter(true);
        tbTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbTransaction.getSelectedRow();
                transactionID = tbTransaction.getModel().getValueAt(row,0).toString();
                dateY = tbTransaction.getModel().getValueAt(row,1).toString();
                dateM = tbTransaction.getModel().getValueAt(row,2).toString();
                dateD = tbTransaction.getModel().getValueAt(row,3).toString();
                customerID = tbTransaction.getModel().getValueAt(row,4).toString();
                firstName = tbTransaction.getModel().getValueAt(row,5).toString();
                lastName = tbTransaction.getModel().getValueAt(row,6).toString();
                serviceID = tbTransaction.getModel().getValueAt(row,7).toString();
                serviceName = tbTransaction.getModel().getValueAt(row,8).toString();
                serviceContent = tbTransaction.getModel().getValueAt(row,9).toString();
                servicePrice = tbTransaction.getModel().getValueAt(row,10).toString();
                cardID = tbTransaction.getModel().getValueAt(row,11).toString();
                cardIssuer = tbTransaction.getModel().getValueAt(row,12).toString();
                cardCover = tbTransaction.getModel().getValueAt(row,13).toString();
                cashPayment = tbTransaction.getModel().getValueAt(row,14).toString();

                dateFull = dateD+"/"+dateM+"/"+dateY;

                showDetail(transactionID);

            }
        });

        exportCsvBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("G:/我的云端硬盘/\"Database Backup\"/Report");
            fileChooser.setDialogTitle("Choose Save Destination");
            FileNameExtensionFilter filter=new FileNameExtensionFilter("*.csv", "csv");
            fileChooser.setFileFilter(filter);

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStamp = formatter.format(date);
            fileChooser.setSelectedFile(new File("transaction_"+dateStamp));

            int userSelection  = fileChooser.showSaveDialog(exportCsvBtn);
            if (userSelection == JFileChooser.APPROVE_OPTION){
                File fileToSave = fileChooser.getSelectedFile();
                try{
                    FileWriter fileWriter = new FileWriter(fileToSave);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Transaction History of Better Health");
                    bufferedWriter.write("Trsc. ID, Year, Month,Day,Customer ID,First Name, Last Name,Service ID,Service,Content,Price,Card,Issuer,Card Cover,Cash Paid");
                    bufferedWriter.newLine();
                    for (int i = 0; i < data.length; i++){
                        for (int j = 0; j < 15; j++){
                            if(j != 14){
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
                    if (!fileToSave.getName().endsWith(".csv")) {
                        fileToSave=new File(fileToSave.getPath()+".csv");
                        System.out.println(fileToSave.getPath());
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(exportCsvBtn, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JScrollPane scpTransaction = new JScrollPane(tbTransaction);
        scpTransaction.setBounds(10,40,700,425);
        tbTransaction.setFillsViewportHeight(true);

        pnlContent.add(contentTitle);
        pnlContent.add(exportCsvBtn);
        pnlContent.add(scpTransaction);

        add(pnlContent);
        setVisible(true);
    }

    public  void showDetail(String transactionID){
        if (pnlDetail != null){remove(pnlDetail);}
        pnlDetail = new JPanel(null);
        pnlDetail.setBackground(Color.lightGray);
        pnlDetail.setBounds(730,0,350,550);

        JLabel lbTrscDetail = new JLabel();
        JButton btnDeleteTransaction = new JButton();
        if (transactionID.length() == 0){
            lbTrscDetail.setText("Transaction Detail");
            btnDeleteTransaction.setText("Delete Transaction");
        }else{
            lbTrscDetail.setText("Transaction Detail: ID = "+transactionID);
            btnDeleteTransaction.setText("Delete Transaction ID = "+transactionID);
        }
        btnDeleteTransaction.setBackground(Color.red);
        btnDeleteTransaction.setBounds(45,450,200,30);
        btnDeleteTransaction.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDeleteTransaction.addActionListener(e ->{

            if (transactionID.length() == 0){
                JOptionPane.showMessageDialog(null,"Please select a customer","Reminder",JOptionPane.INFORMATION_MESSAGE);
            } else {
                int input = JOptionPane.showConfirmDialog(null,"Confirm delete transaction (ID = "+transactionID+")?");
                if (input == 0){
                    DB_CRUD.deleteTransaction(transactionID);
                    constructContentPan("select * from detailed_transaction");
                }
            }
        });

        lbTrscDetail.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbTrscDetail.setBounds(10,5,200,30);

        JLabel lbDate = new JLabel("Date:");
        JLabel lbCustomerID = new JLabel("CustomerID:");
        JLabel lbFirstName = new JLabel("Fist Name:");
        JLabel lbLastName = new JLabel("Last Name:");
        JLabel lbServiceID = new JLabel("Service ID:");
        JLabel lbServiceName = new JLabel("Service Name:");
        JLabel lbServiceContent = new JLabel("Service Content:");
        JLabel lbServicePrice = new JLabel("Price:");
        JLabel lbPayment = new JLabel("Payment detail:");
        lbPayment.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel lbMediCardUsed = new JLabel("Medi-Card:");
        JLabel lbCoveredAmount = new JLabel("Covered Amt:");
        JLabel lbCashPaid = new JLabel("Cash Paid:");

        JTextField tfDate = new JTextField(dateFull);
        JTextField tfCustomerID = new JTextField(customerID);
        JTextField tfFirstName = new JTextField(firstName);
        JTextField tfLastName = new JTextField(lastName);
        JTextField tfServiceID = new JTextField(serviceID);
        JTextField tfServiceName = new JTextField(serviceName);
        JTextField tfServiceContent = new JTextField(serviceContent);
        JTextField tfServicePrice = new JTextField(servicePrice);
        JTextField tfMediCardUsed = new JTextField(cardIssuer + "-" + cardID);
        JTextField tfCoveredAmount = new JTextField(cardCover);
        JTextField tfCashPaid = new JTextField(cashPayment);

        tfDate.setEditable(false);
        tfCustomerID.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfServiceID.setEditable(false);
        tfServiceName.setEditable(false);
        tfServiceContent.setEditable(false);
        tfServicePrice.setEditable(false);
        tfMediCardUsed.setEditable(false);
        tfCoveredAmount.setEditable(false);
        tfCashPaid.setEditable(false);




        lbDate.setBounds(10,40,120,30);
        lbCustomerID.setBounds(10,70,120,30);
        lbFirstName.setBounds(10,100,120,30);
        lbLastName.setBounds(10,130,120,30);
        lbServiceID.setBounds(10,160,120,30);
        lbServiceName.setBounds(10,190,120,30);
        lbServiceContent.setBounds(10,220,120,30);
        lbServicePrice.setBounds(10,250,120,30);

        lbPayment.setBounds(10,300,240,30);
        lbMediCardUsed.setBounds(10,330,120,30);
        lbCoveredAmount.setBounds(10,360,120,30);
        lbCashPaid.setBounds(10,390,120,30);

        tfDate.setBounds(120,40,160,30);
        tfCustomerID.setBounds(120,70,160,30);
        tfFirstName.setBounds(120,100,160,30);
        tfLastName.setBounds(120,130,160,30);
        tfServiceID.setBounds(120,160,160,30);
        tfServiceName.setBounds(120,190,160,30);
        tfServiceContent.setBounds(120,220,160,30);
        tfServicePrice.setBounds(120,250,160,30);

        tfMediCardUsed.setBounds(120,330,160,30);
        tfCoveredAmount.setBounds(120,360,160,30);
        tfCashPaid.setBounds(120,390,160,30);



        pnlDetail.add(lbTrscDetail);
        pnlDetail.add(lbDate);
        pnlDetail.add(lbCustomerID);
        pnlDetail.add(lbFirstName);
        pnlDetail.add(lbLastName);
        pnlDetail.add(lbServiceID);
        pnlDetail.add(lbServiceName);
        pnlDetail.add(lbServiceContent);
        pnlDetail.add(lbServicePrice);
        pnlDetail.add(lbPayment);
        pnlDetail.add(lbMediCardUsed);
        pnlDetail.add(lbCoveredAmount);
        pnlDetail.add(lbCashPaid);
        pnlDetail.add(tfDate);
        pnlDetail.add(tfCustomerID);
        pnlDetail.add(tfFirstName);
        pnlDetail.add(tfLastName);
        pnlDetail.add(tfServiceID);
        pnlDetail.add(tfServiceName);
        pnlDetail.add(tfServiceContent);
        pnlDetail.add(tfServicePrice);
        pnlDetail.add(tfMediCardUsed);
        pnlDetail.add(tfCoveredAmount);
        pnlDetail.add(tfCashPaid);
        pnlDetail.add(btnDeleteTransaction);


        add(pnlDetail);
        setVisible(true);
    }
}
