package model;

import java.io.Serializable;
import java.util.Objects;

public class MyBurgerVO extends BurgerVO implements Serializable {
    private int orderNum;
    public MyBurgerVO()   {
        super();
    }

    public MyBurgerVO(int orderNum, int code, String name, int price, String info) {
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
        MyBurgerVO myBurger = (MyBurgerVO) o;
        return orderNum == myBurger.orderNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNum);
    }

    public String toString2()  {
        return getName()+"\t"+getPrice()+"원";
    }
    @Override
    public String toString() {
        return "주문번호: " +getOrderNum()+"\t"+getName()+"\t"+getPrice()+"원"+"\t"+getInfo();
    }
}
