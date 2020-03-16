package cn.wan.owl.controller;

import cn.wan.owl.dto.ProductCreateDto;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Object list(String keyword) {
        return CommonResponse.success(productService.list(keyword));
    }

    @PostMapping("/add")
    public Object add(@RequestBody ProductCreateDto dto) {
        productService.add(dto);
        return CommonResponse.success();
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam() int id) {
        productService.delete(id);
        return CommonResponse.success();
    }
}
