package burger;

import java.io.Serializable;
import java.util.Objects;

public class MyBurger extends Burger implements Serializable {
    private int orderNum;
    public MyBurger()   {
        super();
    }

    public MyBurger(int orderNum, int code, String name, int price, String info) {
        super(code, name, price, info);
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
        MyBurger myBurger = (MyBurger) o;
        return orderNum == myBurger.orderNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNum);
    }

    @Override
    public String toString() {
        return "주문번호: " +getOrderNum()+"\t"+getBgName()+"\t"+getPrice()+"원"+"\t"+getInfo();
    }

}
