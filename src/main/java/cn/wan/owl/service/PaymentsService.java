package cn.wan.owl.service;

import cn.wan.owl.mapper.PaymentsMapper;
import cn.wan.owl.model.Payments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentsService {
    @Autowired
    PaymentsMapper paymentsMapper;
    public Payments getPamentsBypaymentNumber(String number)
    {
        return paymentsMapper.selectBycardnumber(number);
    }
    public int editPayments(Payments payments)
    {
        return paymentsMapper.updateByPrimaryKeySelective(payments);
    }
}
