import javax.swing.*;
import java.sql.*;

public class DB_CRUD {
    public static String URL = "jdbc:mysql://localhost:3306/betterhealth";
    public static String USER = "root";
    public static String PASSWORD = "!Q@W3e4r";

    public static void addCstmToDB(Customer targetCustomer){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO customer VALUES(default,?,?,?,?,?,?,?,?,?)" );
            pst.setString(1,targetCustomer.firstName);
            pst.setString(2,targetCustomer.lastName);
            pst.setString(3,targetCustomer.mobile);
            pst.setString(4,targetCustomer.email);
            pst.setString(5,targetCustomer.address);
            pst.setString(6,targetCustomer.gender);
            pst.setString(7,targetCustomer.DOB_Y);
            pst.setString(8,targetCustomer.DOB_M);
            pst.setString(9,targetCustomer.DOB_D);
            pst.executeUpdate();

            pst.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "New client is added successfully","",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object[][] searchCstm(String queryString){
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryString);
            rs.last();
            int row_num = rs.getRow();
            rs.beforeFirst();
            Object[][] data = new Object[row_num][10];
            int count = 0;
            while (rs.next()){
                data[count][0] = rs.getString("customerID");
                data[count][1] = rs.getString("firstName");
                data[count][2] = rs.getString("lastName");
                data[count][3] = rs.getString("mobile");
                data[count][4] = rs.getString("email");
                data[count][5] = rs.getString("address");
                data[count][6] = rs.getString("gender");
                data[count][7] = rs.getString("DOB_Y");
                data[count][8] = rs.getString("DOB_M");
                data[count][9] = rs.getString("DOB_D");
                count ++;
            }
            rs.close();
            stmt.close();
            connection.close();
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addCardToDB(MediCard mediCard){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO cards VALUES(?,?,?,?)" );
            pst.setString(1, mediCard.customerID);
            pst.setString(2, mediCard.cardID);
            pst.setString(3, mediCard.issuer);
            pst.setString(4, mediCard.coveredAmount);

            pst.executeUpdate();

            pst.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "New card is added to this customer","",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object[][] searchCard(String queryString){
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryString);
            rs.last();
            int row_num = rs.getRow();
            rs.beforeFirst();
            Object[][] data = new Object[row_num][4];
            int count = 0;
            while (rs.next()){
                data[count][0] = rs.getString("customerID");
                data[count][1] = rs.getString("cardID");
                data[count][2] = rs.getString("issuer");
                data[count][3] = rs.getString("coveredAmount");
                count ++;
            }
            rs.close();
            stmt.close();
            connection.close();
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addServiceToDB(Service service){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO services VALUES(default,?,?,?,?)" );
            pst.setString(1, service.name);
            pst.setString(2, service.content);
            pst.setString(3, service.price);
            pst.setString(4, service.availability);

            pst.executeUpdate();

            pst.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "Service is added to this customer","Note",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "New service cannot add. \nPlease contact your developer","Error",JOptionPane.INFORMATION_MESSAGE);
//            throw new RuntimeException(e);
        }
    }

    public static void updateService(Service service){
//        UPDATE `betterhealth`.`services` SET `price` = '80', `availability` = 'No' WHERE (`serviceID` = '1');
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pst = connection.prepareStatement(
                    "UPDATE services SET serviceName = ?, serviceContent = ?, price = ?, availability = ? WHERE (`serviceID` = ?)");
            pst.setString(1, service.name);
            pst.setString(2, service.content);
            pst.setString(3, service.price);
            pst.setString(4, service.availability);
            pst.setString(5, service.id);

            pst.executeUpdate();

            pst.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "Service is updated","Note",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Service cannot be updated. \nPlease contact your developer","Error",JOptionPane.INFORMATION_MESSAGE);
//            throw new RuntimeException(e);
        }
    }

    public static Object[][] searchService(String queryString){
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryString);
            rs.last();
            int row_num = rs.getRow();
            rs.beforeFirst();
            Object[][] data = new Object[row_num][5];
            int count = 0;
            while (rs.next()){
                data[count][0] = rs.getString("serviceID");
                data[count][1] = rs.getString("serviceName");
                data[count][2] = rs.getString("serviceContent");
                data[count][3] = rs.getString("price");
                data[count][4] = rs.getString("availability");
                count ++;
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
