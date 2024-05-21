package model;

import java.io.Serializable;
import java.util.Objects;

public class MySideVO extends SideVO implements Serializable {
    private int orderNum;
    public MySideVO() {
        super();
    }
    public MySideVO(int orderNum,int code, String name, int price) {
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
        MySideVO mySide = (MySideVO) o;
        return orderNum == mySide.orderNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderNum);
    }
    public String toString2()   {
        return getName() +"\t"+getPrice()+"원";
    }

    @Override
    public String toString() {
        return "주문번호: "+getOrderNum()+"\t"+getName()+"\t\t"+getPrice()+"원";
    }
}
