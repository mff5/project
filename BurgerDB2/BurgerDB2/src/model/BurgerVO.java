package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BurgerVO implements Serializable, Comparable<BurgerVO> {
    private int burgerCode;
    private String burgerName;
    private int burgerPrice;
    private String burgerDescription;
    private String burgerCategory;
    private int burgerStock;
    private Date burgerCreatedAt;
    private Date burgerUpdatedAt;
    private int burgerCalories;
    private String burgerAllergy;

    public BurgerVO(int burgerCode, String burgerName, int burgerPrice, String burgerDescription, String burgerCategory, int burgerStock, Date burgerCreatedAt, Date burgerUpdatedAt, int burgerCalories, String burgerAllergy) {
        this.burgerCode = burgerCode;
        this.burgerName = burgerName;
        this.burgerPrice = burgerPrice;
        this.burgerDescription = burgerDescription;
        this.burgerCategory = burgerCategory;
        this.burgerStock = burgerStock;
        this.burgerCreatedAt = burgerCreatedAt;
        this.burgerUpdatedAt = burgerUpdatedAt;
        this.burgerCalories = burgerCalories;
        this.burgerAllergy = burgerAllergy;
    }
    public void dan()   {

        setBurgerName(getBurgerName()+" 단품");
    }
    public void sizeUp()  {
        setBurgerPrice(getBurgerPrice()+2000);
        setBurgerName(getBurgerName()+" 사이즈업(+2000)");
    }
    public void set() {
        setBurgerPrice((int)(getBurgerPrice()*1.3));
        setBurgerName(getBurgerName()+" 세트");
    }
    public void sizeUpNSet()  {
        setBurgerPrice((int)(getBurgerPrice()*1.3) + 2000);
        setBurgerName(getBurgerName()+" 사이즈업+세트");
    }

    public int getBurgerCode() {
        return burgerCode;
    }

    public void setBurgerCode(int burgerCode) {
        this.burgerCode = burgerCode;
    }

    public String getBurgerName() {
        return burgerName;
    }

    public void setBurgerName(String burgerName) {
        this.burgerName = burgerName;
    }

    public int getBurgerPrice() {
        return burgerPrice;
    }

    public void setBurgerPrice(int burgerPrice) {
        this.burgerPrice = burgerPrice;
    }

    public String getBurgerDescription() {
        return burgerDescription;
    }

    public void setBurgerDescription(String burgerDescription) {
        this.burgerDescription = burgerDescription;
    }

    public String getBurgerCategory() {
        return burgerCategory;
    }

    public void setBurgerCategory(String burgerCategory) {
        this.burgerCategory = burgerCategory;
    }

    public int getBurgerStock() {
        return burgerStock;
    }

    public void setBurgerStock(int burgerStock) {
        this.burgerStock = burgerStock;
    }

    public Date getBurgerCreatedAt() {
        return burgerCreatedAt;
    }

    public void setBurgerCreatedAt(Date burgerCreatedAt) {
        this.burgerCreatedAt = burgerCreatedAt;
    }

    public Date getBurgerUpdatedAt() {
        return burgerUpdatedAt;
    }

    public void setBurgerUpdatedAt(Date burgerUpdatedAt) {
        this.burgerUpdatedAt = burgerUpdatedAt;
    }

    public int getBurgerCalories() {
        return burgerCalories;
    }

    public void setBurgerCalories(int burgerCalories) {
        this.burgerCalories = burgerCalories;
    }

    public String getBurgerAllergy() {
        return burgerAllergy;
    }

    public void setBurgerAllergy(String burgerAllergy) {
        this.burgerAllergy = burgerAllergy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BurgerVO burgerVO = (BurgerVO) o;
        return getBurgerCode() == burgerVO.getBurgerCode() && getBurgerPrice() == burgerVO.getBurgerPrice() && getBurgerStock() == burgerVO.getBurgerStock() && getBurgerCalories() == burgerVO.getBurgerCalories() && Objects.equals(getBurgerName(), burgerVO.getBurgerName()) && Objects.equals(getBurgerDescription(), burgerVO.getBurgerDescription()) && Objects.equals(getBurgerCategory(), burgerVO.getBurgerCategory()) && Objects.equals(getBurgerCreatedAt(), burgerVO.getBurgerCreatedAt()) && Objects.equals(getBurgerUpdatedAt(), burgerVO.getBurgerUpdatedAt()) && Objects.equals(getBurgerAllergy(), burgerVO.getBurgerAllergy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBurgerCode(), getBurgerName(), getBurgerPrice(), getBurgerDescription(), getBurgerCategory(), getBurgerStock(), getBurgerCreatedAt(), getBurgerUpdatedAt(), getBurgerCalories(), getBurgerAllergy());
    }

    @Override
    public String toString() {
        return "BurgerVO{" +
                "burgerCode=" + burgerCode +
                ", burgerName='" + burgerName + '\'' +
                ", burgerPrice=" + burgerPrice +
                ", burgerDescription='" + burgerDescription + '\'' +
                ", burgerCategory='" + burgerCategory + '\'' +
                ", burgerStock=" + burgerStock +
                ", burgerCreatedAt=" + burgerCreatedAt +
                ", burgerUpdatedAt=" + burgerUpdatedAt +
                ", burgerCalories=" + burgerCalories +
                ", burgerAllergy='" + burgerAllergy + '\'' +
                '}';
    }

    @Override
    public int compareTo(BurgerVO o) {
        return 0;
    }
}
