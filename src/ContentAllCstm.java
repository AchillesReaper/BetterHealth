import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ContentAllCstm extends JPanel {
    public static String URL = "jdbc:mysql://localhost:3306/betterhealth";
    public static String USER = "root";
    public static String PASSWORD = "!Q@W3e4r";
    public Object[][] data;

    public JTable cstmTable;
    public JScrollPane scrollPan;

    public ContentAllCstm(){
        this.setBounds(10,70,730,600);
        this.setLayout(null);

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
}
