import javax.swing.*;
import java.sql.*;

public class DB_CRUD {
    public static String URL = "jdbc:mysql://localhost:3306/BetterHealthTherapyBD";
    public static String USER = "root";
    public static String PASSWORD = "!Q@W3e4r";

    public static void addCstmToDB(String firstName, String lastName, String mediCare, String mobile,
                            String gender, String DOB, String email, String address){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
//            Statement stmt = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement(
                    "insert into customer(firstName,lastName,mediCare,mobile,gender,DOB,email,address) " +
                            "values(?,?,?,?,?,?,?,?)" );
            pst.setString(1,firstName);
            pst.setString(2,lastName);
            pst.setString(3,mediCare);
            pst.setString(4,mobile);
            pst.setString(5,gender);
            pst.setString(6,DOB);
            pst.setString(7,email);
            pst.setString(8,address);
            pst.executeUpdate();

            pst.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "new customer "+firstName+" "+lastName+" is added to database!","",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object[][] showAllCstm(){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement stmt = connection.createStatement();
            String queryString = "select * from customer";
            ResultSet rs = stmt.executeQuery(queryString);
            int row_num = 0;
            while (rs.next()) {
                row_num++;
            }
            Object[][] data = new Object[row_num][8];
            int count = 0;
            while (rs.next()){
                data[count][0] = rs.getString("firstName");
                data[count][1] = rs.getString("lastName");
                data[count][2] = rs.getString("mediCare");
                data[count][3] = rs.getString("mobile");
                data[count][4] = rs.getString("gender");
                data[count][5] = rs.getString("DOB");
                data[count][6] = rs.getString("email");
                data[count][7] = rs.getString("address");
                System.out.println(data[count][0]);
                System.out.println(data[count][1]);
                System.out.println(data[count][2]);
                System.out.println(data[count][3]);
                System.out.println(data[count][4]);
                System.out.println(data[count][5]);
                System.out.println(data[count][6]);
                System.out.println(data[count][7]);

                count ++;
                System.out.println(count);
            }

            rs.close();
            stmt.close();
            connection.close();

            return data;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
