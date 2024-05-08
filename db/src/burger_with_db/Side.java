package burger;

import java.io.Serializable;
import java.util.Objects;

public class Side implements Serializable, Comparable<Side> {
    private int code;
    private String sdName;
    private int price;
    public Side()   {
        super();
    }
    public Side(int code, String sdName, int price) {
        this.code = code;
        this.sdName = sdName;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
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
        Side side = (Side) o;
        return code == side.code && price == side.price && Objects.equals(sdName, side.sdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, sdName, price);
    }

    @Override
    public String toString() {
        return getCode()+"번\t\t"+getSdName()+"\t"+getPrice()+"원";
    }
    @Override
    public int compareTo(Side o) {
        return getCode() - o.getCode();
    }
}
