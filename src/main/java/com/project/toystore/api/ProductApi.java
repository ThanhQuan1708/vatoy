package com.project.toystore.api;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.dto.ProductDTO;
import com.project.toystore.dto.SearchProductObj;
import com.project.toystore.entities.Product;
import com.project.toystore.service.ProductService;
import com.project.toystore.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/product")
public class ProductApi {

    @Autowired
    private ProductValidator validator;

    @Autowired
    private ProductService productService;

    @InitBinder
    protected void initialiseBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    // get all product by filter
    @GetMapping("/all")
    public Page<Product> getAllSanPhamByFilter(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam String categoryId, @RequestParam String brandId,@RequestParam String recAgeId, @RequestParam String price, @RequestParam String sortByPrice) {
        SearchProductObj searchObject = new SearchProductObj();
        searchObject.setCategoryId(categoryId);
        searchObject.setBrandId(brandId);
        searchObject.setRecAgeId(recAgeId);
        searchObject.setPrice(price);
        searchObject.setSortByPrice(sortByPrice);

        Page<Product> products = productService.getAllProductByFilter(searchObject, page-1, 12);
        return products;
    }

    @GetMapping("/latest")
    public List<Product> getLatestProduct(){
        return productService.getLastedProduct();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }


    // lấy sản phẩm theo tên
    @GetMapping("/")
    public Page<Product> getProductByName(@RequestParam String name, @RequestParam(defaultValue = "1") int page) {
        return productService.getProductByName(name, page-1, 12 );
    }

    // lưu sản phẩm vào db
    @PostMapping(value = "/save")
    public ObjectReponse addProduct(@ModelAttribute @Valid ProductDTO newProduct, BindingResult result,
                                    HttpServletRequest request) {

        ObjectReponse obj = new ObjectReponse();
        if (result.hasErrors()) {
            System.out.print("AAAAAAAAAAAAAA");
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            errors.forEach((k, v) -> System.out.println(" test: Key : " + k + " Value : " + v));
            obj.setErrorMassage(errors);
            obj.setStatus("fail");
            errors = null;
        } else {
            Product pro = productService.save(newProduct);
            obj.setData(pro);
            System.out.println("avasdasd"+obj.getData());
            saveImageForProduct(pro, newProduct, request);
            obj.setStatus("success");
        }
        return obj;
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        productService.deleteById(id);
        return "OK !";
    }


    // save image
    public void saveImageForProduct(Product pro, ProductDTO dto, HttpServletRequest request) {

        MultipartFile productImage = dto.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Path path = Paths.get(rootDirectory + "/resources/images/" + pro.getId() + ".png");
        System.out.println(productImage != null && !productImage.isEmpty());
        if (productImage != null && !productImage.isEmpty()) {

            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Product image saving failed", ex);
            }
        }
    }
}
