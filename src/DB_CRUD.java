import javax.swing.*;
import java.sql.*;

public class DB_CRUD {
//    public static String URL = "jdbc:mysql://localhost:3306/betterhealth";
//    public static String USER = "root";
//    public static String PASSWORD = "!Q@W3e4r";
//
//    public static void addCstmToDB(String firstName, String lastName, String mobile, String email,
//                                   String address, String gender, String DOB){
//        try{
//            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
////            Statement stmt = connection.createStatement();
//            PreparedStatement pst = connection.prepareStatement(
//                    "insert into customer(firstName,lastName,mobile,email,address,gender,DOB) " +
//                            "values(?,?,?,?,?,?,?,?)" );
//            pst.setString(1,firstName);
//            pst.setString(2,lastName);
//            pst.setString(3,mobile);
//            pst.setString(4,email);
//            pst.setString(5,address);
//            pst.setString(6,gender);
//            pst.setString(7,DOB);
//            pst.executeUpdate();
//
//            pst.close();
//            connection.close();
//
//            JOptionPane.showMessageDialog(null, firstName+" "+lastName+" is added as a new customer","",JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static Object[][] showAllCstm(){
//        try{
//            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
//            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            String queryString = "select * from customer";
//            ResultSet rs = stmt.executeQuery(queryString);
//            rs.last();
//            int row_num = rs.getRow();
//            rs.beforeFirst();
//            Object[][] data = new Object[row_num][8];
//            int count = 0;
//            while (rs.next()){
//                data[count][0] = rs.getString("firstName");
//                data[count][1] = rs.getString("lastName");
//                data[count][2] = rs.getString("mediCare");
//                data[count][3] = rs.getString("mobile");
//                data[count][4] = rs.getString("gender");
//                data[count][5] = rs.getString("DOB");
//                data[count][6] = rs.getString("email");
//                data[count][7] = rs.getString("address");
//                count ++;
//            }
//
//            rs.close();
//            stmt.close();
//            connection.close();
//
//            return data;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    public static Object[][] searchCstm(String firstName, String lastName, String mediCare, String mobile,
////                                        String gender, String DOB, String email, String address){
////        try{
////            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
////            Statement stmt = connection.createStatement();
////            String queryString = "select * from customer";
////            if (firstName != null){
////                queryString += " where firstName = '%" + firstName + "%'";
////            }
////            if (lastName != null){
////                queryString += " where firstName = '%" + lastName + "%'";
////            }
////            if (firstName != null){
////                queryString += " where firstName = '%" + firstName + "%'";
////            }
////            if (firstName != null){
////                queryString += " where firstName = '%" + firstName + "%'";
////            }
////            if (firstName != null){
////                queryString += " where firstName = '%" + firstName + "%'";
////            }
////            if (firstName != null){
////                queryString += " where firstName = '%" + firstName + "%'";
////            }
////            if (firstName != null){
////                queryString += " where firstName = '%" + firstName + "%'";
////            }
////
////        }
////    }
}
