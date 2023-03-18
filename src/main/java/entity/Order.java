package entity;

import javax.persistence.*;

/**
 *orderId:6位数,从100001开始;
 * number:订单人数;
 * price:订单总价格;价格显示会员打折后实际支付价格;
 * state:1)已结算已执行:SE;2)未结算已执行:NE;3)已结算未执行:SW;4)未结算未执行:NW;5)未支付:UP;6)退订:C;
 * PS:S-Settle(结算);N-Not(未结算);E-Execute(执行);W-Wait(未执行);UP-Until Payed(未支付);C-Cancel(退订)
 * Rule:退订规则——开课2周前退全额;开课前2周不到退半额;开课后不退;
 */
@Entity
@Table(name = "order", schema = "college", catalog = "")
public class Order {
    private int orderId;
    private String studentMail;
    private String studentName;
    private int courseId;
    private int number;
    private double price;
    private String state;
    private int institutionId;

    @Id
    @Column(name = "orderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "studentMail", nullable = false, length = 45)
    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    @Basic
    @Column(name = "studentName", nullable = false, length = 45)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "courseId", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "institutionId", nullable = false)
    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (orderId != that.orderId) return false;
        if (courseId != that.courseId) return false;
        if (number != that.number) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (institutionId != that.institutionId) return false;
        if (studentMail != null ? !studentMail.equals(that.studentMail) : that.studentMail != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + (studentMail != null ? studentMail.hashCode() : 0);
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + courseId;
        result = 31 * result + number;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + institutionId;
        return result;
    }
}
