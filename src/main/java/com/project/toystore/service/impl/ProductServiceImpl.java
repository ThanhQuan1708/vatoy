package com.project.toystore.service.impl;

import com.project.toystore.dto.ProductDTO;
import com.project.toystore.dto.SearchProductObj;
import com.project.toystore.entities.*;
import com.project.toystore.reposities.BrandReposity;
import com.project.toystore.reposities.CategoryReposity;
import com.project.toystore.reposities.ProductReposity;
import com.project.toystore.reposities.RecAgeReposity;
import com.project.toystore.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@ComponentScan
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductReposity productRepo;
    @Autowired
    private CategoryReposity categoryRepo;
    @Autowired
    private BrandReposity brandRepo;
    @Autowired
    private RecAgeReposity recAgeRepo;

    public Product convertDTOtoObj(ProductDTO dto){
        Product pro = new Product();
        if(!dto.getId().equals("")){
            pro.setId(Long.parseLong(dto.getId()));
        }

        pro.setName(dto.getName());
        pro.setPrice(Integer.parseInt(dto.getPrice()));
        pro.setInStorage(Integer.parseInt(dto.getInStorage()));
        pro.setDescription(dto.getDescription());
        pro.setTopic(dto.getTopic());
        pro.setOrigin(dto.getOrigin());
        pro.setMaterial(dto.getMaterial());

        pro.setCategory(categoryRepo.findById(dto.getId_category()).get());
        pro.setBrand(brandRepo.findById(dto.getId_brand()).get());
        pro.setRecAge(recAgeRepo.findById(dto.getId_recAge()).get());
        System.out.print("NO LA CAI NAY" + pro);
        return pro;
    }

    @Override
    public Product save(ProductDTO product) {
        Product pro = convertDTOtoObj(product);
        System.out.print(pro);
        return productRepo.save(pro);
    }

    @Override
    public Product update(ProductDTO product) {
        return productRepo.save(convertDTOtoObj(product));
    }

    @Override
    public void deleteById(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getProductById(long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getLastedProduct() {
        return productRepo.findFirst12ByCategoryNameContainingIgnoreCaseOrderByIdDesc("Game");
    }

    @Override
    public List<Product> getAllProductByList(Set<Long> idList) {
        return productRepo.findByIdIn(idList);
    }

    @Override
    public Iterable<Product> getProductByCategory(String category) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QProduct.product.category.name.eq(category));
        return productRepo.findAll(builder);
    }

    @Override
    public Iterable<Product> getProductByBrand(String brand) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QProduct.product.brand.name.eq(brand));
        return productRepo.findAll(builder);
    }
    @Override
    public Iterable<Product> getProductByAge(String age) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QProduct.product.recAge.name.eq(age));
        return productRepo.findAll(builder);
    }

    @Override
    public Iterable<Product> getProductByName(SearchProductObj obj) {
        BooleanBuilder builder = new BooleanBuilder();
        String price = obj.getPrice();
        String[] keyword = obj.getKeyWord();
        builder.and(QProduct.product.name.like("%"+keyword[0]+"%"));
        if(keyword.length>1){
            for (int i = 1 ;i< keyword.length; i++){
                builder.and(QProduct.product.name.like("%"+keyword[i]+"%"));
            }
        }
        switch (price){
            case "duoi-100.000":
                builder.and(QProduct.product.price.lt(100000));
                break;
            case "tu-100.000-den-200.000":
                builder.and(QProduct.product.price.between(100000,200000));
                break;
            case "tu-200.000-den-500.000":
                builder.and(QProduct.product.price.between(200000,500000));
                break;
            case "tren-500.000":
                builder.and(QProduct.product.price.gt(500000));
                break;
            default:
                break;
        }
        return productRepo.findAll(builder);
    }

    @Override
    public Page<Product> getAllProductByFilter(SearchProductObj obj, int page, int limit) {
        BooleanBuilder builder = new BooleanBuilder();
        String price = obj.getPrice();

        Sort sort =Sort.by(Sort.Direction.ASC,"price");
        if(obj.getSortByPrice().equals("desc")){
            sort = Sort.by(Sort.Direction.DESC, "price");
        }
        if(!obj.getCategoryId().equals("")&&obj.getCategoryId()!=null){
            builder.and(QProduct.product.category.eq(categoryRepo.findById(Long.parseLong(obj.getCategoryId())).get()));
        }
        if(!obj.getBrandId().equals("")&& obj.getBrandId()!= null){
            builder.and(QProduct.product.brand.eq(brandRepo.findById(Long.parseLong(obj.getBrandId())).get()));
        }
        if(!obj.getRecAgeId().equals("")&&obj.getRecAgeId() != null){
            builder.and(QProduct.product.recAge.eq(recAgeRepo.findById(Long.parseLong(obj.getRecAgeId())).get()));
        }
        switch (price){
            case "duoi-100.000":
                builder.and(QProduct.product.price.lt(100000));
                break;
            case "tu-100.000 den-200.000":
                builder.and(QProduct.product.price.between(100000,200000));
                break;
            case "tu-200.000-den-500.000":
                builder.and(QProduct.product.price.between(200000,500000));
                break;
            case "tren-500.000":
                builder.and(QProduct.product.price.gt(500000));
                break;

        }
        return productRepo.findAll(builder,PageRequest.of(page,limit,sort));
    }

    @Override
    public Page<Product> getProductByName(SearchProductObj obj, int page, int size) {
        BooleanBuilder builder = new BooleanBuilder();
        String[] keyWord = obj.getKeyWord();
        String sort = obj.getSort();
        String  price = obj.getPrice();
        String brand = obj.getBrand();
        String category = obj.getCategory();
        String recAge = obj.getRecAge();
        //display by keyword
        builder.and(QProduct.product.name.like("%"+keyWord[0]+"%"));
        if (keyWord.length>1){
            for (int i =1; i< keyWord.length;i++){
                builder.and(QProduct.product.name.like("%"+keyWord[i]+"%"));
            }
        }
        //display by price
        switch (price){
            case "duoi-100.000":
                builder.and(QProduct.product.price.lt(100000));
                break;
            case "tu-100.000-den-200.000":
                builder.and(QProduct.product.price.between(100000,200000));
                break;
            case "tu-200.000-den-500.000":
                builder.and(QProduct.product.price.between(200000,500000));
                break;
            case "tren-500.000":
                builder.and(QProduct.product.price.gt(500000));
                break;

        }
        //display by category, brand, recAge
        if(category.length()>1){
            builder.and(QProduct.product.category.name.eq(category));
        }
        if(brand.length()>1){
            builder.and(QProduct.product.brand.name.eq(brand));
        }
        if(recAge.length()>1){
            builder.and(QProduct.product.recAge.name.eq(recAge));
        }
        //display by price
        if(sort.equals("newest")){
            return productRepo.findAll(builder, PageRequest.of(page-1, size,Sort.Direction.DESC,"id"));
        } else if(sort.equals("priceASC")){
            return productRepo.findAll(builder, PageRequest.of(page-1, size, Sort.Direction.ASC,"price"));
        }else if(sort.equals("priceDES")){
            return productRepo.findAll(builder,PageRequest.of(page-1, size,Sort.Direction.DESC,"price"));
        }
        return productRepo.findAll(builder,PageRequest.of(page-1, size));
    }

    @Override
    public Page<Product> getProductByName(String name, int page, int size) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QProduct.product.name.like("%"+name+"%"));
        return productRepo.findAll(builder,PageRequest.of(page,size));
    }

    @Override
    public Page<Product> getProductByBrand(SearchProductObj obj, int page, int size) {
        BooleanBuilder builder = new BooleanBuilder();
        String price = obj.getPrice();
        String brand = obj.getBrand();
        String category = obj.getCategory();
        String recAge = obj.getRecAge();
        switch (price){
            case "duoi-100.000":
                builder.and(QProduct.product.price.lt(100000));
                break;
            case "tu-100.000-den-200.000":
                builder.and(QProduct.product.price.between(100000,200000));
                break;
            case "tu-200.000-den-500.000":
                builder.and(QProduct.product.price.between(200000,500000));
                break;
            case "tren-500.000":
                builder.and(QProduct.product.price.gt(500000));
                break;
            default:
                break;
        }
        if (category.length()>1){
            builder.and(QProduct.product.category.name.eq(category));
        }
        if (brand.length()>1){
            builder.and(QProduct.product.brand.name.eq(brand));
        }
        if (recAge.length()>1){
            builder.and(QProduct.product.recAge.name.eq(recAge));
        }
        return productRepo.findAll(builder,PageRequest.of(page-1,size));
    }
}
