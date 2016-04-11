package com.petstore.web.servlet.bill;

import com.petstore.persistence.BillDao;
import com.petstore.entity.Bill;
import com.petstore.entity.User;
import com.petstore.service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetBillListServlet", value = "/billList")
public class GetBillListServlet extends HttpServlet {

    private static String billListPage = "/WEB-INF/jsp/bill/billList.jsp";
    BillService billService = new BillService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();
        List<Bill> billList = billService.getBillList(username);
        request.getSession().setAttribute("billList", billList);
        request.getRequestDispatcher(billListPage).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
