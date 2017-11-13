/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Kalum
 */
public class BracodePrint {
    private String recNo;
    private Integer barcodePrint;
    private String proNo;
    private Integer bcQty;    
    private String barCode;
    private String bcDes;
    private Float price;

    public BracodePrint() {
    }

    public BracodePrint(String recNo, Integer barcodePrint, String proNo, Integer bcQty, String barCode, String bcDes, Float price) {
        this.recNo = recNo;
        this.barcodePrint = barcodePrint;
        this.proNo = proNo;
        this.bcQty = bcQty;
        this.barCode = barCode;
        this.bcDes = bcDes;
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public Integer getBarcodePrint() {
        return barcodePrint;
    }

    public void setBarcodePrint(Integer barcodePrint) {
        this.barcodePrint = barcodePrint;
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }

    public Integer getBcQty() {
        return bcQty;
    }

    public void setBcQty(Integer bcQty) {
        this.bcQty = bcQty;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBcDes() {
        return bcDes;
    }

    public void setBcDes(String bcDes) {
        this.bcDes = bcDes;
    }
    
}
