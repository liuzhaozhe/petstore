package com.petstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.petstore.entity.Bill;
import com.petstore.entity.Item;
import com.petstore.entity.Product;
import com.petstore.entity.User;
import com.petstore.service.BillService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BillAction extends ActionSupport implements ModelDriven<Bill> {

    private BillService billService = new BillService();
    private Map<String, Object> session = ActionContext.getContext().getSession();
    private Bill bill = new Bill();

    private String productId = null;
    private int amount = 1;             // 账单商品数量最少是1

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * 通过购物车购买
     *
     * @return
     */
    public String addBillByCar() {
        List<Item> buyList = new ArrayList<Item>();
        List<Item> itemList = (List<Item>) session.get("itemList");

        if (productId != null) {
            for (Item item : itemList
                    ) {
                if (item.getProductId().equals(productId)) {
                    buyList.add(item);
                    break;
                }
            }
        } else {
            // 购买购物车所有的商品
            buyList.addAll(itemList);
        }
        session.put("buyList", buyList);
        return SUCCESS;
    }

    /**
     * 通过商品页购买
     *
     * @return
     */
    public String addBillByProduct() {
        // 生成Item对象保存购买商品信息
        Product product = (Product) session.get("product");
        Item item = new Item();
        item.setProductId(product.getProductId());
        item.setProductName(product.getProductName());
        item.setAmount(1);
        item.setPrice(product.getPrice());
        item.setTotalPrice(product.getPrice());
        // 页面以List方式接受商品信息
        List<Item> buyList = new ArrayList<Item>();
        buyList.add(item);
        session.put("buyList", buyList);
        return SUCCESS;
    }

    /**
     * 生成账单
     *
     * @return
     */
    public String bill() {
        List<Item> buyList = (List<Item>) session.get("buyList");
        if (buyList == null || buyList.size() == 0) {
            addActionError("您没有选择商品，不能生成账单");
            return ERROR;
        }

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        Random random = new Random();
        int temp = random.nextInt(90) + 10;
        bill.setBillId(dateString + temp);
        bill.setCreateTime(currentTime);
        List<Item> billItemList = new ArrayList<Item>();
        double totalPrice = 0;
        for (Item itemTemp : buyList
                ) {
            totalPrice += itemTemp.getPrice() * itemTemp.getAmount();
            itemTemp.setTotalPrice(itemTemp.getPrice() * itemTemp.getAmount());
            billItemList.add(itemTemp);
        }
        bill.setMoney(totalPrice);
        session.put("bill", bill);
        session.put("billItemList", billItemList);
        return SUCCESS;
    }

    /**
     * 确认购买
     *
     * @return
     */
    public String confirmBuy() {
        Bill sessionBill = (Bill) session.get("bill");
        List<Item> billItemList = (List<Item>) session.get("billItemList");
        String username = ((User) session.get("user")).getUsername();
        boolean result = billService.addBill(sessionBill, billItemList, username);
        if (result) {
            return SUCCESS;
        } else {
            addActionError("商品库存不足，请取消购买！");
            return ERROR;
        }
    }

    /**
     * 删除账单某一项
     *
     * @return
     */
    public String deleteItem() {
        List<Item> buyList = (List<Item>) session.get("buyList");
        int index = -1;
        for (int i = 0; i < buyList.size(); i++) {
            Item itemTemp = buyList.get(i);
            if (itemTemp.getProductId().equals(productId)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            buyList.remove(index);
        }
        session.put("buyList", buyList);
        return SUCCESS;
    }

    /**
     * 获取账单详细信息
     *
     * @return
     */
    public String getBillDetail() {
        bill = billService.getBill(bill.getBillId());
        List<Item> billItemList = billService.getBillItemList(bill.getBillId());
        session.put("bill", bill);
        session.put("billItemList", billItemList);
        return SUCCESS;
    }

    /**
     * 获取账单列表
     *
     * @return
     */
    public String getBillList() {
        User user = (User) session.get("user");
        String username = user.getUsername();
        List<Bill> billList = billService.getBillList(username);
        session.put("billList", billList);
        return SUCCESS;
    }

    /**
     * 更新账单上商品的数量
     *
     * @return
     */
    public String updateItem() {
        List<Item> buyList = (List<Item>) session.get("buyList");
        for (Item itemTemp : buyList
                ) {
            if (itemTemp.getProductId().equals(productId)) {
                itemTemp.setAmount(amount);
                itemTemp.setTotalPrice(amount * itemTemp.getPrice());
                break;
            }
        }
        session.put("buyList", buyList);
        return SUCCESS;
    }

    /**
     * 刷新账单界面
     *
     * @return
     */
    public String updateNewBill() {
        List<Item> buyList = (List<Item>) session.get("buyList");
        // 计算总金额
        double totalPrice = 0;
        for (Item item : buyList
                ) {
            totalPrice += item.getTotalPrice();
        }
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("totalPrice", totalPrice);
        return SUCCESS;
    }

    @Override
    public Bill getModel() {
        return bill;
    }
}
