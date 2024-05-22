package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SideVO implements Serializable, Comparable<SideVO> {
    private int sideCode;
    private String sideName;
    private int sidePrice;
    private String sideDescription;
    private String sideCategory;
    private int sideStock;
    private Date sideCreatedAt;
    private Date sideUpdatedAt;
    private int sideCalories;
    private String sideAllergy;

    public SideVO(int sideCode, String sideName, int sidePrice, String sideDescription, String sideCategory, int sideStock, Date sideCreatedAt, Date sideUpdatedAt, int sideCalories, String sideAllergyInfo) {
        this.sideCode = sideCode;
        this.sideName = sideName;
        this.sidePrice = sidePrice;
        this.sideDescription = sideDescription;
        this.sideCategory = sideCategory;
        this.sideStock = sideStock;
        this.sideCreatedAt = sideCreatedAt;
        this.sideUpdatedAt = sideUpdatedAt;
        this.sideCalories = sideCalories;
        this.sideAllergy = sideAllergyInfo;
    }

    public int getSideCode() {
        return sideCode;
    }

    public void setSideCode(int sideCode) {
        this.sideCode = sideCode;
    }

    public String getSideName() {
        return sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }

    public int getSidePrice() {
        return sidePrice;
    }

    public void setSidePrice(int sidePrice) {
        this.sidePrice = sidePrice;
    }

    public String getSideDescription() {
        return sideDescription;
    }

    public void setSideDescription(String sideDescription) {
        this.sideDescription = sideDescription;
    }

    public String getCategory() {
        return sideCategory;
    }

    public void setCategory(String category) {
        this.sideCategory = category;
    }

    public int getSideStock() {
        return sideStock;
    }

    public void setSideStock(int sideStock) {
        this.sideStock = sideStock;
    }

    public Date getSideCreatedAt() {
        return sideCreatedAt;
    }

    public void setSideCreatedAt(Date sideCreatedAt) {
        this.sideCreatedAt = sideCreatedAt;
    }

    public Date getSideUpdatedAt() {
        return sideUpdatedAt;
    }

    public void setSideUpdatedAt(Date sideUpdatedAt) {
        this.sideUpdatedAt = sideUpdatedAt;
    }

    public int getSideCalories() {
        return sideCalories;
    }

    public void setSideCalories(int sideCalories) {
        this.sideCalories = sideCalories;
    }

    public String getSideAllergyInfo() {
        return sideAllergy;
    }

    public void setSideAllergyInfo(String sideAllergyInfo) {
        this.sideAllergy = sideAllergyInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SideVO sideVO = (SideVO) o;
        return getSideCode() == sideVO.getSideCode() && getSidePrice() == sideVO.getSidePrice() && getSideStock() == sideVO.getSideStock() && getSideCalories() == sideVO.getSideCalories() && Objects.equals(getSideName(), sideVO.getSideName()) && Objects.equals(getSideDescription(), sideVO.getSideDescription()) && Objects.equals(getCategory(), sideVO.getCategory()) && Objects.equals(getSideCreatedAt(), sideVO.getSideCreatedAt()) && Objects.equals(getSideUpdatedAt(), sideVO.getSideUpdatedAt()) && Objects.equals(getSideAllergyInfo(), sideVO.getSideAllergyInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSideCode(), getSideName(), getSidePrice(), getSideDescription(), getCategory(), getSideStock(), getSideCreatedAt(), getSideUpdatedAt(), getSideCalories(), getSideAllergyInfo());
    }

    @Override
    public String toString() {
        return "SideVO{" +
                "sideCode=" + sideCode +
                ", sideName='" + sideName + '\'' +
                ", sidePrice=" + sidePrice +
                ", sideDescription='" + sideDescription + '\'' +
                ", category='" + sideCategory + '\'' +
                ", sideStock=" + sideStock +
                ", sideCreatedAt=" + sideCreatedAt +
                ", sideUpdatedAt=" + sideUpdatedAt +
                ", sideCalories=" + sideCalories +
                ", sideAllergyInfo='" + sideAllergy + '\'' +
                '}';
    }

    @Override
    public int compareTo(SideVO o) {
        return 0;
    }
}
