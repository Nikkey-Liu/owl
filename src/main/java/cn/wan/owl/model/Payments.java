package cn.wan.owl.model;

public class Payments {
    private Integer cardid;

    private String cardnumber;

    private Integer expirymonth;

    private Integer expiryyear;

    private Integer cvv;

    private Integer balance;

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Integer getExpirymonth() {
        return expirymonth;
    }

    public void setExpirymonth(Integer expirymonth) {
        this.expirymonth = expirymonth;
    }

    public Integer getExpiryyear() {
        return expiryyear;
    }

    public void setExpiryyear(Integer expiryyear) {
        this.expiryyear = expiryyear;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}