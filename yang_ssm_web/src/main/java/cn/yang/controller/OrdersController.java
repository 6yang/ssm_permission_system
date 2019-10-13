package cn.yang.controller;

import cn.yang.domain.Orders;
import cn.yang.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/***
 * @ClassName: OrdersController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/915:25
 * @version : V1.0
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService iOrdersService;


    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") int size){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = iOrdersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }


    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="id",required = true)String ordersId){
        ModelAndView mv = new ModelAndView();
        Orders orders =  iOrdersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
