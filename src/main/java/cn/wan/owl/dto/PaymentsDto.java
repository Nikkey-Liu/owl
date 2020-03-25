package cn.wan.owl.dto;

import cn.wan.owl.model.Payments;
import lombok.Data;

@Data
public class PaymentsDto {
    private Integer cardid;
    private String cardnumber;

    private Integer expirymonth;

    private Integer expiryyear;

    private Integer cvv;

    private Integer balance;
    public Payments getPayments(){
        return new Payments(0,cardnumber,expirymonth,expiryyear,cvv,0);
    }
    public boolean checkPayments(Payments p1,Payments p2){
        boolean flag=true;
        String result="checkPayments: ";
        if(!p1.getCardnumber().equals(p2.getCardnumber()))
        {
            flag=false;
            result=result+"\n cardnumber is not right";
        }
        else if (!p1.getExpirymonth().equals(p2.getExpirymonth()))
        {   flag=false;
            result=result+"\n expirymonth is not right";
        }
        else if (!p1.getExpiryyear().equals(p2.getExpiryyear())){
            flag=false;
            result=result+"\n expiryyear is not right";
        }
        else if(!p1.getCvv().equals(p2.getCvv())){
            flag=false;
            result=result+"\n Cvv is not right";
        }
        else {
            result= "success check";
        }
        System.out.println(result);
        return flag;
    }
}
