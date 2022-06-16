public class Transaction {
    public String id, dateY, dateM, dateD, customerID,serviceID, totalPrice, cardUsed, cardCover, cashPayment;

    public Transaction(String dateY, String dateM, String dateD, String customerID, String serviceID, String totalPrice, String cardUsed, String cardCover, String cashPayment){
        this.dateY = dateY;
        this.dateM = dateM;
        this.dateD = dateD;
        this.customerID = customerID;
        this.serviceID = serviceID;
        this.totalPrice = totalPrice;
        this.cardUsed = cardUsed;
        this.cardCover = cardCover;
        this.cashPayment = cashPayment;
    }
    public void setTransactionID(String id){
        this.id = id;
    }
}
