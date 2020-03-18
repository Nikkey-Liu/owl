package cn.wan.owl.controller;

import cn.wan.owl.dto.NProductCreateDto;

import cn.wan.owl.dto.ProductDto.OriginatorWidget;
import cn.wan.owl.dto.ProductDto.ProductHandler;
import cn.wan.owl.dto.PurchaseQueryDto;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.service.NProductService;
import cn.wan.owl.service.ProductService;
import cn.wan.owl.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    OriginatorWidget o = new OriginatorWidget();
    ProductHandler c = new ProductHandler();
    @Autowired
    private ProductService productService;
    @Autowired
    private NProductService nProductService;
    @GetMapping
    public Object list(String keyword) {
        return CommonResponse.success(productService.list(keyword));
    }
    @GetMapping("/list/productOfme")
    public Object list(String orderBy, String orderType) {
        return CommonResponse.success(nProductService.viewProductsbyUserId(UserUtil.getCurrentUser().getUserid()));
    }
    @PostMapping("/add")
    public Object add(@RequestBody NProductCreateDto dto) {
        System.out.println("------------------------------------------------------" +dto );
        c.setWidget(o);

        nProductService.addProduct(dto.toPo());

        c.setWidgetValue(nProductService.selectProductByMaxId().getProductid().toString());
        return CommonResponse.success();
    }
    @PostMapping("/undo")
    public Object undo() {
       c.undoOperation();
        if(c.getWidgetValue().equals( "" ))
        {
            return CommonResponse.failed();
        }
        else
        {
            nProductService.removeProductByid(Integer.parseInt(c.getWidgetValue()));
            return CommonResponse.success();
        }

    }
    @PostMapping("/delete")
    public Object delete(@RequestParam() int id) {
        productService.delete(id);
        return CommonResponse.success();
    }
}
