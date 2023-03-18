package util;

/**
 *
 */
public class managerOrderUtil {

    private int orderId;
    private int institutionId;
    private String institutionName;
    private String studentMail;
    private double price;
    private String state;

    public int getOrderId(){return orderId;}

    public void setOrderId(int id){this.orderId = id;}

    public int getInstitutionId(){return institutionId;}

    public void setInstitutionId(int id){this.institutionId = id;}

    public String getInstitutionName(){return institutionName;}

    public void setInstitutionName(String name){this.institutionName = name;}

    public String getStudentMail(){return studentMail;}

    public void setStudentMail(String mail){this.studentMail = mail;}

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){this.price = price;}

    public String getState(){
        return state;
    }

    public void setState(String state){this.state = state;}
}
