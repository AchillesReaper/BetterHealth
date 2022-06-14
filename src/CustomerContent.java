import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CustomerContent extends JPanel {
    public static String URL = "jdbc:mysql://localhost:3306/betterhealth";
    public static String USER = "root";
    public static String PASSWORD = "!Q@W3e4r";
    public Object[][] data;

    public JTable cstmTable;
    public JScrollPane scrollPan;

    public CustomerContent(){
        this.setBounds(10,70,730,600);
        this.setLayout(null);
    }
    public void showAllCustomer(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String queryString = "select * from customer";
            ResultSet rs = stmt.executeQuery(queryString);
            rs.last();
            int row_num = rs.getRow();
            rs.beforeFirst();
            data = new Object[row_num][8];
            int count = 0;
            while (rs.next()){
                data[count][0] = rs.getString("customerID");
                data[count][1] = rs.getString("firstName");
                data[count][2] = rs.getString("lastName");
                data[count][3] = rs.getString("mobile");
                data[count][4] = rs.getString("gender");
                data[count][5] = rs.getString("DOB");
                data[count][6] = rs.getString("email");
                data[count][7] = rs.getString("address");
                count ++;
            }
            String[] colName = {"Customer ID", "First Name", "Last Name","Mobile","Gender","DOB","email","Address"};
            cstmTable = new JTable(data, colName);
            cstmTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
            cstmTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

            scrollPan = new JScrollPane(cstmTable);
            scrollPan.setBounds(20,40,700,500);
            cstmTable.setFillsViewportHeight(true);

            JLabel contentTitle = new JLabel("Customer");

            this.add(contentTitle);
            this.add(cstmTable);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchCustomer(Customer cstm){
        System.out.println("Customer ID: "+ cstm.customerID);
        System.out.println("FN: "+ cstm.firstName);
        System.out.println("LN: "+ cstm.lastName);
        if (cstm.mobile == null){
            System.out.println("mobile is null");
        }
        System.out.println("MB: "+ cstm.mobile);
        System.out.println("MB length: "+ cstm.mobile.length());
        System.out.println("GD: "+ cstm.gender);
        System.out.println("DOB: "+ cstm.DOB);
        System.out.println("email: "+ cstm.email);
        System.out.println("address: "+ cstm.address);

    }
}
