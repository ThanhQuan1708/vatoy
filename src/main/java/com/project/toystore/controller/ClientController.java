package com.project.toystore.controller;

import com.project.toystore.dto.ObjectReponse;
import com.project.toystore.dto.SearchProductObj;
import com.project.toystore.entities.Category;
import com.project.toystore.entities.Contact;
import com.project.toystore.entities.Product;
import com.project.toystore.entities.User;
import com.project.toystore.service.CategoryService;
import com.project.toystore.service.ContactService;
import com.project.toystore.service.ProductService;
import com.project.toystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ContactService contactService;

    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    @ModelAttribute("listCategory")
    public List<Category> categoryList(){return categoryService.getAllCategory();}
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping
    public String clientDisplay(Model model){
        return "client/home";
    }
    @GetMapping("/login")
    public String loginDisplay(Model model){
        return "client/login";
    }
    @GetMapping("/contact")
    public String contactDisplay(Model model){
        return "client/contact";
    }
    @PostMapping("/createContact")
    @ResponseBody
    public ObjectReponse createContact(@RequestBody Contact contact){
        contact.setDateCreate(new Date());
        contactService.save(contact);
        return new ObjectReponse();
    }
    @GetMapping("/store")
    public String storeDisplay(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "") String range,
                               @RequestParam(defaultValue = "") String category,
                               @RequestParam(defaultValue = "") String brand,
                               @RequestParam(defaultValue = "") String recAge, Model model){
        SearchProductObj obj = new SearchProductObj();
        obj.setCategory(category);
        obj.setBrand(brand);
        obj.setRecAge(recAge);
        Page<Product> list = productService.getProductByBrand(obj,page,12);
        int totalPage = list.getTotalPages();
        model.addAttribute("total",totalPage);
        model.addAttribute("list",list.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("brand",brand);
        model.addAttribute("category",category);
        model.addAttribute("recAge",recAge);
        List<Integer> pageList = new ArrayList<Integer>();
        if(page==1 || page==2 ||page ==3||page==4){
            for (int i =2; i<=5 && i <= totalPage; i++){
                pageList.add(i);
            }
        }else if(page==totalPage){
            for (int i =totalPage ; i >= totalPage -3 && i>i; i--){
                pageList.add(i);
            }
            Collections.sort(pageList);
        }else{
            for (int i = page; i<= page +2 && i<=totalPage; i++){
                pageList.add(i);
            }
            Collections.sort(pageList);
        }
        model.addAttribute("pageList", pageList);
        Set<String> bran = new HashSet<String>();
        Set<String> rec = new HashSet<String>();
        Iterable<Product> ip = productService.getProductByBrand(brand);
        for(Product p : ip){
            bran.add(p.getBrand().getName());
            rec.add(p.getRecAge().getName());
        }
        model.addAttribute("bran", bran);
        model.addAttribute("rec",rec);
        return "client/store";
    }
    @GetMapping("/product")
    public String detailProduct(@RequestParam int id, Model model){
        Product pro = productService.getProductById(id);
        model.addAttribute("product", pro);
        return "client/detailProduct";
    }
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "client:/login?logout";
    }
    @GetMapping("/shipping")
    public String shippingDisplay(Model model){
        return "client/shipping";
    }



}

