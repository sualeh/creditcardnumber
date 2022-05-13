package us.fatehi.creditcardnumber;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class BinCode {
  @CsvBindByName
  private int bin;
  @CsvBindByName
  private String brand;
  @CsvBindByName
  private String type;
  @CsvBindByName
  private String category;
  @CsvBindByName
  private String issuer;
  @CsvBindByName(column = "alpha_2")
  private String alpha2;
  @CsvBindByName(column = "alpha_3")
  private String alpha3;
  @CsvBindByName
  private String country;
  @CsvBindByName
  private Double latitude;
  @CsvBindByName
  private Double longitude;
  @CsvBindByName(column = "bank_phone")
  private String bankPhone;
  @CsvBindByName(column = "bank_url")
  private String bankUrl;

  public BinCode() {
  }

  public int getBin() {
    return bin;
  }

  public void setBin(int bin) {
    this.bin = bin;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getAlpha2() {
    return alpha2;
  }

  public void setAlpha2(String alpha2) {
    this.alpha2 = alpha2;
  }

  public String getAlpha3() {
    return alpha3;
  }

  public void setAlpha3(String alpha3) {
    this.alpha3 = alpha3;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getBankPhone() {
    return bankPhone;
  }

  public void setBankPhone(String bankPhone) {
    this.bankPhone = bankPhone;
  }

  public String getBankUrl() {
    return bankUrl;
  }

  public void setBankUrl(String bankUrl) {
    this.bankUrl = bankUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BinCode binCode = (BinCode) o;
    return getBin() == binCode.getBin() && Objects.equals(getBrand(), binCode.getBrand()) && Objects.equals(getType(), binCode.getType()) && Objects.equals(getCategory(), binCode.getCategory()) && Objects.equals(getIssuer(), binCode.getIssuer()) && Objects.equals(getAlpha2(), binCode.getAlpha2()) && Objects.equals(getAlpha3(), binCode.getAlpha3()) && Objects.equals(getCountry(), binCode.getCountry()) && Objects.equals(getLatitude(), binCode.getLatitude()) && Objects.equals(getLongitude(), binCode.getLongitude()) && Objects.equals(getBankPhone(), binCode.getBankPhone()) && Objects.equals(getBankUrl(), binCode.getBankUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBin(), getBrand(), getType(), getCategory(), getIssuer(), getAlpha2(), getAlpha3(), getCountry(), getLatitude(), getLongitude(), getBankPhone(), getBankUrl());
  }

  @Override
  public String toString() {
    return "BinCode{" +
      "bin=" + bin +
      ", brand='" + brand + '\'' +
      ", type='" + type + '\'' +
      ", category='" + category + '\'' +
      ", issuer='" + issuer + '\'' +
      ", alpha2='" + alpha2 + '\'' +
      ", alpha3='" + alpha3 + '\'' +
      ", country='" + country + '\'' +
      ", latitude=" + latitude +
      ", longitude=" + longitude +
      ", bankPhone='" + bankPhone + '\'' +
      ", bankUrl='" + bankUrl + '\'' +
      '}';
  }
}
