package com.petstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.petstore.entity.Item;
import com.petstore.entity.Product;
import com.petstore.entity.User;
import com.petstore.service.ShoppingCarService;

import java.util.List;
import java.util.Map;

/**
 * Created by hezhujun on 2016/4/12.
 */
public class ShoppingCarAction extends ActionSupport {

    private ShoppingCarService shoppingCarService = new ShoppingCarService();
    private Map<String, Object> session = ActionContext.getContext().getSession();

    private String productId = null;
    private int amount = 0;         // 某一商品的数量

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
     * 获取购物车内容
     * @return
     */
    public String shoppingCar(){
        String username = ((User)session.get("user")).getUsername();
        List<Item> items = shoppingCarService.getShoppingCarItems(username);
        session.put("itemList", items);
        return SUCCESS;
    }

    /**
     * 加入购物车
     * @return
     */
    public String addShoppingCar(){
        String username = ((User)session.get("user")).getUsername();
        Product product = (Product) session.get("product");
        boolean result = shoppingCarService.checkCarItem(product.getProductId(), username);
        // 购物车中不存在此商品
        if (!result) {
            Item item = new Item();
            item.setAmount(1);
            item.setPrice(product.getPrice());
            item.setProductId(product.getProductId());
            item.setProductName(product.getProductName());
            item.setTotalPrice(item.getPrice());
            shoppingCarService.addShoppingCar(item, username);
        }
        return SUCCESS;
    }

    /**
     * 删除购物车中的某一商品
     * @return
     */
    public String deleteShoppingCar(){
        String username = ((User)session.get("user")).getUsername();
        shoppingCarService.delete(username,productId);
        return SUCCESS;
    }

    /**
     * 更新购物车内容
     * @return
     */
    public String updateShoppingCar(){
        String username = ((User)session.get("user")).getUsername();
        Item item = shoppingCarService.getCarItem(productId, username);
        item.setAmount(amount);
        item.setTotalPrice(amount * item.getPrice());
        boolean result = shoppingCarService.update(item, username);
        if (result) {
            // 更新session数据
            List<Item> itemList = (List<Item>) session.get("itemList");
            for (Item temp : itemList
                    ) {
                if (temp.getProductId().equals(productId)){
                    temp.setAmount(item.getAmount());
                    temp.setTotalPrice(item.getTotalPrice());
                    break;
                }
            }
            session.put("itemList", itemList);
        }
        return SUCCESS;
    }

}
