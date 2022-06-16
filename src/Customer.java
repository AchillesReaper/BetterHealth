public class Customer {
    public String customerID, firstName, lastName, mobile, email, address, gender, DOB_Y, DOB_M, DOB_D;
    public Customer(String firstName, String lastName, String mobile, String email, String address, String gender, String DOB_Y, String DOB_M, String DOB_D){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.DOB_Y = DOB_Y;
        this.DOB_M = DOB_M;
        this.DOB_D = DOB_D;
    }

    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }

}
