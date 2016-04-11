package com.petstore.web.servlet.bill;

import com.petstore.persistence.BillDao;
import com.petstore.persistence.ItemDao;
import com.petstore.entity.Bill;
import com.petstore.entity.Item;
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
@WebServlet(name = "GetBillDetailServlet",value = "/billDetail")
public class GetBillDetailServlet extends HttpServlet {

    private static String billDetailPage = "/WEB-INF/jsp/bill/billDetail.jsp";
    BillService billService = new BillService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billId = request.getParameter("billId");
        Bill bill = billService.getBill(billId);
        List<Item> billItemList = billService.getBillItemList(billId);
        request.getSession().setAttribute("bill", bill);
        request.getSession().setAttribute("billItemList", billItemList);
        request.getRequestDispatcher(billDetailPage).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
