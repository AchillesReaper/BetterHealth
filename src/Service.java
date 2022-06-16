public class Service {
    public String id, name,content,price,availability;

    public Service(String name,String content,String price,String availability){
        this.name = name;
        this.content = content;
        this.price = price;
        this.availability = availability;
    }

    public void setID(String id){
        this.id = id;
    }
}
