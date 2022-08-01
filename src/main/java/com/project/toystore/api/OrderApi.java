package com.project.toystore.api;

import com.project.toystore.dto.SearchOrderObj;
import com.project.toystore.entities.OrderDetail;
import com.project.toystore.entities.Orderr;
import com.project.toystore.entities.Product;
import com.project.toystore.service.OrderService;
import com.project.toystore.service.UserService;
import com.querydsl.core.types.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderApi {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Page<Orderr> getOrderByFilter(@RequestParam (defaultValue = "1") int page, @RequestParam String status, @RequestParam String from, @RequestParam String to) throws Exception{
        SearchOrderObj obj = new SearchOrderObj();
        obj.setFrom(from);
        obj.setTo(to);
        obj.setStatus(status);
        Page<Orderr> orderList = orderService.getAllOrderByFilter(obj,page);
        return orderList;
    }
    @GetMapping("/{id}")
    public Orderr getOrderById(@PathVariable long id){
        return orderService.findById(id);
    }
    @PutMapping("/assign")
    public void assign(@RequestParam("shipper") String emailShipper,@RequestParam("orderId") long id){
        Orderr order = orderService.findById(id);
        order.setStatus("Đang giao");
        order.setShipper(userService.findByEmail(emailShipper));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String dateStr = format.format(new Date());
            Date date = format.parse(dateStr);
            order.setDeliveryDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderService.save(order);
    }
    @PostMapping("/update")
    public void complete(@RequestParam("orderId") long id,@RequestParam("note") String tempNote){
        Orderr o = orderService.findById(id);
        for (OrderDetail od : o.getOrderDetails()){
            Product pro = od.getProduct();
            pro.setSellQuantity(pro.getSellQuantity()+od.getReceiveQuantity());
            pro.setInStorage(pro.getInStorage()-od.getReceiveQuantity());
        }
        o.setStatus("Hoàn thành");
        String note = o.getNote();
        if(!tempNote.equals("")){
            note += "Ghi chú : "+tempNote;
        }
        o.setNote(note);
        orderService.save(o);

    }
    @PostMapping("/cancel")
    public void cancelOrder(@RequestParam("orderId") long id){
        Orderr order = orderService.findById(id);
        order.setStatus("Đã huỷ");
        orderService.save(order);
    }
}
