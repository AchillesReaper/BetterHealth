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

public class SectionTransaction extends JPanel {
    public JPanel pnlDetail, pnlContent;

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
    public String cardCover = "";
    public String cashPayment = "";

    public SectionTransaction(){
        setBounds(10,70,1080,700);
        setLayout(null);
        constructContentPan();
        showDetail();
    }

    public void constructContentPan(){
        pnlContent = new JPanel(null);
        pnlContent.setBounds(0,0,720,550);
        pnlContent.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Transactions of Better Health");
        contentTitle.setBounds(10,5,300,30);
        contentTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton exportCsvBtn = new JButton("Export CSV");
        exportCsvBtn.setBounds(595,10,120,30);

        String queryString = "select * from transactions";

        Object[][] data = DB_CRUD.searchTransaction(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No Record is found.","",JOptionPane.PLAIN_MESSAGE);
        }
        String[] colName = {"Trsc. ID", "Year", "Month","Day","Customer","Service","Price","Card","Card Cover","Cash Paid"};
        JTable tbTransaction = new JTable(data, colName);
        tbTransaction.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbTransaction.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbTransaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbTransaction.getSelectedRow();
                dateY = tbTransaction.getModel().getValueAt(row,1).toString();
                dateM = tbTransaction.getModel().getValueAt(row,2).toString();
                dateD = tbTransaction.getModel().getValueAt(row,3).toString();
                customerID = tbTransaction.getModel().getValueAt(row,4).toString();
                serviceID = tbTransaction.getModel().getValueAt(row,5).toString();
                servicePrice = tbTransaction.getModel().getValueAt(row,6).toString();
                cardID = tbTransaction.getModel().getValueAt(row,7).toString();
                cardCover = tbTransaction.getModel().getValueAt(row,8).toString();
                cashPayment = tbTransaction.getModel().getValueAt(row,9).toString();

                dateFull = dateD+"/"+dateM+"/"+dateY;

                Object[][] subData;
                String queryString;

                queryString = "select * from customer WHERE customerID = '" + customerID + "'";
                subData = DB_CRUD.searchCustomer(queryString);
                firstName = (String) subData[0][1];
                lastName = (String) subData[0][2];

                queryString = "select * from services WHERE serviceID = '" + serviceID + "'";
                subData = DB_CRUD.searchService(queryString);
                serviceName = (String) subData[0][1];
                serviceContent = (String) subData[0][2];

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
        scpTransaction.setBounds(10,40,700,500);
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
        JLabel lbLastName = new JLabel("LastName:");
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
        JTextField tfMediCardUsed = new JTextField(cardID);
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
