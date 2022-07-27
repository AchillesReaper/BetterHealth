
public class Main {
    public static void main(String[] args){
        SectionTransaction sectionInput = new SectionTransaction("select * from detailed_transaction");
        new BHFrame(sectionInput);
    }
}
