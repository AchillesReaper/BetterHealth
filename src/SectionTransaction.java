import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SectionTransaction extends JPanel {
    public JPanel pnlDetail, pnlContent, pnlFilter;

    public String dateFull = "";
    public String dateY = "";
    public String dateM = "";
    public String dateD = "";
    public String customerID = "";
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


    public String[] listStartDate = new String[3];
    public String[] listEndDate = new String[3];
    public Integer[] trimListStartDate = new Integer[3];
    public Integer[] trimListEndDate = new Integer[3];



    public SectionTransaction(){
        setBounds(10,70,1080,700);
        setLayout(null);
        constructContentPan("select * from detailed_transaction");
        constructFilter();
        showDetail();
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

        JButton btFilter = new JButton("Search");

        btFilter.addActionListener(e -> {
            String qStr = "select * from detailed_transaction";
            if (!(cbStartDate.isSelected()) && !(cbEndDate.isSelected())){
                JOptionPane.showMessageDialog(this,"Date range is not selected","Error",JOptionPane.ERROR_MESSAGE);
            } else {
                if (cbStartDate.isSelected()){
                    listStartDate[0] = tfSdYear.getText();
                    listStartDate[1] = tfSdMonth.getText();
                    listStartDate[2] = tfSdDay.getText();
                    if (inputValidation(listStartDate, "Start Date")){
                        qStr += generateQueryStringToken("S");
                    }
                }

                if (cbEndDate.isSelected()){
                    listEndDate[0] = tfEdYear.getText();
                    listEndDate[1] = tfEdMonth.getText();
                    listEndDate[2] = tfEdDay.getText();
                    if (inputValidation(listEndDate, "End Date")){
                        qStr += generateQueryStringToken("E");
                    }
                }

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

        btFilter.setBounds(600,5,80,30);

        //add to panel;
        pnlFilter.add(lbFilterTitle);
        pnlFilter.add(pnlStartD);
        pnlFilter.add(pnlEndD);
        pnlFilter.add(btFilter);

        add(pnlFilter);
        setVisible(true);
    }
    public String generateQueryStringToken(String token){
        String qsToken = "";

        if (token.equals("S")){
            qsToken += " WHERE year >= "+listStartDate[0]+" and month >= "+listStartDate[1]+" and day >= "+listStartDate[2];
        }
        if (token.equals("E")){
            qsToken += " WHERE year >= "+listEndDate[0]+" and month >= "+listEndDate[1]+" and day >= "+listEndDate[2];
        }

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
        pnlContent = new JPanel(null);
        pnlContent.setBounds(0,75,720,475);
        pnlContent.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Transactions of Better Health");
        contentTitle.setBounds(10,5,300,30);
        contentTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton exportCsvBtn = new JButton("Export CSV");
        exportCsvBtn.setBounds(595,10,120,30);

        Object[][] data = DB_CRUD.searchTransaction(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No Record is found.","",JOptionPane.PLAIN_MESSAGE);
        }
        String[] colName = {"Trsc. ID", "Year", "Month","Day","Customer ID","First Name", "Last Name","Service ID","Service","Price","Card","Issuer","Card Cover","Cash Paid"};
        JTable tbTransaction = new JTable(data, colName);
        tbTransaction.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbTransaction.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbTransaction.setAutoCreateRowSorter(true);
        tbTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbTransaction.getSelectedRow();
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

                showDetail();
            }
        });

        exportCsvBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose Save Destination");

            int userSelection  = fileChooser.showSaveDialog(exportCsvBtn);
            if (userSelection == JFileChooser.APPROVE_OPTION){
                File fileToSave = fileChooser.getSelectedFile();
                try{
                    FileWriter fileWriter = new FileWriter(fileToSave);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Transaction History of Better Health");
                    bufferedWriter.write("transactionID,year,month,day,customerID,serviceID,totalPrice,cardUsed,cardCover,cashPayment");
                    bufferedWriter.newLine();
                    for (int i = 0; i < data.length; i++){
                        for (int j = 0; j < 11; j++){
                            if(j != 10){
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

    public  void showDetail(){
        if (pnlDetail != null){remove(pnlDetail);}
        pnlDetail = new JPanel(null);
        pnlDetail.setBackground(Color.lightGray);
        pnlDetail.setBounds(730,0,350,550);

        JLabel lbTrscDetail = new JLabel("Transaction Detail");
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


        add(pnlDetail);
        setVisible(true);
    }
}
