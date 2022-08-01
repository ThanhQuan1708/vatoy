package com.project.toystore.controller;

import com.project.toystore.dto.SearchProductObj;
import com.project.toystore.entities.Product;
import com.project.toystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("search")
    public String searchProduct(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String sort,
                                @RequestParam(defaultValue = "") String range,
                                @RequestParam(defaultValue = "") String brand,
                                @RequestParam(defaultValue = "") String category,
                                @RequestParam(defaultValue = "") String recAge,
                                Model model){
        SearchProductObj obj = new SearchProductObj();
        obj.setKeyWord(name.split(" "));
        obj.setSort(sort);
        obj.setPrice(range);
        obj.setCategory(category);
        obj.setBrand(brand);
        obj.setRecAge(recAge);
        Page<Product> list = productService.getProductByName(obj, page,12);
        int totalPage = list.getTotalPages();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("list", list.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("name",name);
        model.addAttribute("sort", sort);
        model.addAttribute("range", range);
        model.addAttribute("brand",brand);
        model.addAttribute("category", category);
        model.addAttribute("recAge",recAge);
        List<Integer> pageList = new ArrayList<Integer>();
        if(page==1|| page==2||page==3||page==4){
            for (int i =2; i <=5 && i<= totalPage; i++){
                pageList.add(i);
            }
        }else if(page == totalPage){
            for (int i = totalPage; i>= totalPage-3 && i>1; i-- ){
                pageList.add(i);
            }
            Collections.sort(pageList);
        }else{
            for (int i = page; i<= page +2 && i<= totalPage; i++){
                pageList.add(i);
            }
            for (int i = page-1; i>= page-2&& i>1; i--){
                pageList.add(i);
            }
            Collections.sort(pageList);
        }
        model.addAttribute("pageList", pageList);
        Set<String> cate = new HashSet<String>();
        Set<String> bran = new HashSet<String>();
        Set<String> rec = new HashSet<String>();
        Iterable<Product> pros = productService.getProductByName(obj);
        for(Product p : pros){
            cate.add(p.getCategory().getName());
            bran.add(p.getBrand().getName());
            rec.add(p.getRecAge().getName());
        }
        model.addAttribute("cate",cate);
        model.addAttribute("bran",bran);
        model.addAttribute("rec",rec);
        return "client/searchResult";
    }
}
