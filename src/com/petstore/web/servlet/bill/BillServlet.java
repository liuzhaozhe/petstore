package com.petstore.web.servlet.bill;

import com.petstore.entity.Bill;
import com.petstore.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "BillServlet", value = "/addBill")
public class BillServlet extends HttpServlet {

    private static String okBuyPage = "/WEB-INF/jsp/bill/okBuy.jsp";
    private static String buyPage = "/WEB-INF/jsp/bill/buy.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<Item> buyList = (List<Item>) request.getSession().getAttribute("buyList");
        if (buyList == null || buyList.size() == 0){
            request.setAttribute("msg", "您没有选择商品，不能生成账单");
            request.getRequestDispatcher(buyPage).forward(request,response);
            return;
        }

        String consignee = request.getParameter("consignee");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Bill bill = new Bill();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        Random random = new Random();
        int temp = random.nextInt(90) + 10;
        bill.setBillId(dateString + temp);
        bill.setConsignee(consignee);
        bill.setCreateTime(currentTime);
        bill.setConsigneeAddress(address);
        bill.setConsigneePhone(phone);
        List<Item> billItemList = new ArrayList<Item>();
        double totalPrice = 0;
        for (Item itemTemp : buyList
             ) {
            totalPrice += itemTemp.getPrice() * itemTemp.getAmount();
            itemTemp.setTotalPrice(itemTemp.getPrice() * itemTemp.getAmount());
            billItemList.add(itemTemp);
        }
        bill.setMoney(totalPrice);
        request.getSession().setAttribute("bill", bill);
        request.getSession().setAttribute("billItemList", billItemList);
        request.getRequestDispatcher(okBuyPage).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
