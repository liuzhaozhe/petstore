package com.petstore.web.servlet.bill;

import com.petstore.persistence.BillDao;
import com.petstore.persistence.ItemDao;
import com.petstore.persistence.ProductDao;
import com.petstore.entity.Bill;
import com.petstore.entity.Item;
import com.petstore.entity.User;
import com.petstore.service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hezhujun on 2016/3/22.
 */
@WebServlet(name = "ConfirmBuyServlet", value = "/confirmBuy")
public class ConfirmBuyServlet extends HttpServlet {

    private static String successPage = "/WEB-INF/jsp/bill/success.jsp";
    private static String okBuyPage = "/WEB-INF/jsp/bill/okBuy.jsp";
    private BillService billService = new BillService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Bill bill = (Bill) request.getSession().getAttribute("bill");
        List<Item> billItemList = (List<Item>) request.getSession().getAttribute("billItemList");
        String username = ((User) request.getSession().getAttribute("user")).getUsername();
        boolean result = billService.addBill(bill, billItemList, username);
        if (result) {
            request.getRequestDispatcher(successPage).forward(request, response);
        } else {
            request.setAttribute("msg", "商品库存不足，请取消购买！");
            request.getRequestDispatcher(okBuyPage).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
