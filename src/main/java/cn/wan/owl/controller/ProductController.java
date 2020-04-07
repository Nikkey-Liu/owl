package cn.wan.owl.controller;

import cn.wan.owl.dto.IdDto;
import cn.wan.owl.dto.NProductCreateDto;

import cn.wan.owl.dto.ProductDto.OriginatorWidget;
import cn.wan.owl.dto.ProductDto.ProductHandler;
import cn.wan.owl.dto.pdf.ReportCreator;
import cn.wan.owl.dto.pdf.ReportService;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.ResponseCoN;
import cn.wan.owl.service.NProductService;
import cn.wan.owl.service.ProductService;
import cn.wan.owl.util.Constantvalue;
import cn.wan.owl.util.UserUtil;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;


@RestController
@RequestMapping("/product")
public class ProductController {
    OriginatorWidget o = new OriginatorWidget();
    ProductHandler c = new ProductHandler();
    @Autowired
    private ProductService productService;
    @Autowired
    private NProductService nProductService;
    //    @Autowired
//    private HttpServletRequest request;
//    @Autowired
//    private HttpServletResponse response;
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
    public Object delete(@RequestBody IdDto id) {
        nProductService.removeProductByid(id.getid());
        return CommonResponse.success();
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE )
    public ResponseEntity <byte[]>  approve( @PathVariable( "id") Integer  id ){

        NProduct nProduct= nProductService.selectProductbyid(id);

        nProduct.setProductstate(Constantvalue.ONSALE);
        nProductService.editProduct(nProduct);

        ByteArrayOutputStream out = ReportService.getReportServiceInstance(ReportCreator.getReportFactory()).printDocument( "PDF",nProduct);
        if (out==null)
        {
            System.out.println("------------------------null messege pdf");
        }
        System.out.println("reportPDF builder  finish");
        System.out.println("---------------------build heaeders--------------------");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.parseMediaType( "application/pdf" ) );
//        headers.add("Content-Disposition", "attachment; filename=report.pdf");
//        headers.add("content-type", "application/pdf; charset=utf-8");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        System.out.println("--------------------finish---------------------");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        return response;
    }
    @GetMapping("/list/Allproduct")
    public Object Allproduct(String orderBy, String orderType) {
        return CommonResponse.success(nProductService.viewOnsaleProductByProductState(Constantvalue.ONSALE));
    }
    @GetMapping("/list/TeaApProduct")
    public Object ApProduct(String orderBy, String orderType) {
        return CommonResponse.success(nProductService.viewOnsaleProductByProductState(Constantvalue.UNAPPROVE));
    }
}
