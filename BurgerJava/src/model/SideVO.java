package model;

import java.io.Serializable;
import java.util.Objects;

public class SideVO implements Serializable, Comparable<SideVO> {
    private int code;
    private String name;
    private int price;
    public SideVO()   {
        super();
    }
    public SideVO(int code, String sdName, int price) {
        this.code = code;
        this.name = sdName;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SideVO side = (SideVO) o;
        return code == side.code && price == side.price && Objects.equals(name, side.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price);
    }

    @Override
    public String toString() {
        return getCode()+"번\t\t"+getName()+"\t"+getPrice()+"원";
    }
    @Override
    public int compareTo(SideVO o) {
        return getCode() - o.getCode();
    }
}
