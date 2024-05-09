package burger;

import java.io.Serializable;
import java.util.Objects;

public class Burger implements Serializable, Comparable<Burger> {
    private int code;
    private String name;
    private int price;
    private String info;
    public Burger() {
        super();
    }

    public Burger(int code, String name, int price, String info) {
        this.code = code;
        this.name = name;
        this.info = info;
        this.price = price;
    }
    public void dan()   {
        setName(name+" 단품");
    }
    public void sizeUp()  {
        price = price + 2000;
        setName(name+" 사이즈업(+2000)");
    }
    public void set() {
        price = (int)(price*1.3);
        setName(name+" 세트");
    }
    public void sizeUpNSet()  {
        price = (int)(price*1.3) + 2000;
        setName(name+" 사이즈업+세트");
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Burger burger = (Burger) o;
        return code == burger.code && price == burger.price && Objects.equals(name, burger.name) && Objects.equals(info, burger.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price, info);
    }

    @Override
    public String toString() {
        return getCode()+"번\t\t"+getName()+"\t"+getPrice()+"원\t"+getInfo();
    }

    @Override
    public int compareTo(Burger o) {
        return getCode()-o.getCode();
    }
}
