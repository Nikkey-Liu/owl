package cn.wan.owl.controller;

import cn.wan.owl.dto.PurchaseCreateDto;
import cn.wan.owl.dto.PurchaseQueryDto;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.service.PurchaseService;
import cn.wan.owl.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/add")
    public Object add(@RequestBody PurchaseCreateDto createDto) {
        purchaseService.add(createDto);
        return CommonResponse.success();
    }

    @GetMapping("/list/person")
    public Object list(String orderBy, String orderType) {
        return CommonResponse.success(
                purchaseService.list(new PurchaseQueryDto(UserUtil.getCurrentUser().getUserid(), orderBy, orderType)));
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam int id) {
        purchaseService.delete(id);
        return CommonResponse.success();
    }

}
