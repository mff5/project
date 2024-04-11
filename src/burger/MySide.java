package burger;

import java.io.Serializable;
import java.util.Objects;

public class MySide extends Side implements Serializable {
    private int orderNum;
    public MySide() {
        super();
    }
    public MySide(int orderNum,int code, String name, int price) {
        super(code, name, price);
        this.orderNum = orderNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MySide mySide = (MySide) o;
        return orderNum == mySide.orderNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderNum);
    }

    @Override
    public String toString() {
        return "주문번호: "+getOrderNum()+"\t"+getSdName()+"\t\t"+getPrice()+"원";
    }
}
