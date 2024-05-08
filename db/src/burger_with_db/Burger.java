package burger;

import java.io.Serializable;
import java.util.Objects;

public class Burger implements Serializable, Comparable<Burger> {
    private int code;
    private String bgName;
    private int price;
    private String info;
    public Burger() {
        super();
    }

    public Burger(int code, String name, int price, String info) {
        this.code = code;
        this.bgName = name;
        this.info = info;
        this.price = price;
    }
    public void dan()   {
        setBgName(bgName+" 단품");
    }
    public void sizeUp()  {
        price = price + 2000;
        setBgName(bgName+" 사이즈업(+2000)");
    }
    public void set() {
        price = (int)(price*1.3);
        setBgName(bgName+" 세트");
    }
    public void sizeUpNSet()  {
        price = (int)(price*1.3) + 2000;
        setBgName(bgName+" 사이즈업+세트");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBgName() {
        return bgName;
    }

    public void setBgName(String bgName) {
        this.bgName = bgName;
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
        return code == burger.code && price == burger.price && Objects.equals(bgName, burger.bgName) && Objects.equals(info, burger.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, bgName, price, info);
    }

    @Override
    public String toString() {
        return getCode()+"번\t\t"+getBgName()+"\t"+getPrice()+"원\t"+getInfo();
    }

    @Override
    public int compareTo(Burger o) {
        return getCode()-o.getCode();
    }
}
