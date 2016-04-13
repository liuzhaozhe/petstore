package com.petstore.web.action;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.petstore.entity.Category;
import com.petstore.entity.Product;
import com.petstore.service.ProductService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ProductAction extends ActionSupport {

    private Map<String, Object> session = ActionContext.getContext().getSession();
    private ProductService productService = new ProductService();

    private String category = null;
    private String category2 = null;
    private String productId = null;
    private String search = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * 获取类别
     * @return
     */
    public String category(){
        Map<String, String> category2 = productService.getCategory2(category);
        session.put("category2", category2);
        session.put("categoryName", category);
        return SUCCESS;
    }

    /**
     * 获取商品列表
     * @return
     */
    public String productList(){
        List<String[]> productList = productService.getProductInfo(category2);
        session.put("productList", productList);
        return SUCCESS;
    }

    /**
     * 获取商品信息
     * @return
     */
    public String product(){
        Object[] o = productService.getProduct(productId);
        Product product = (Product) o[0];
        Category category = productService.getCategory((String) o[1]);
        session.put("product", product);
        session.put("category", category);
        return SUCCESS;
    }

    /**
     * 搜索商品
     * @return
     */
    public String search(){
        List<String[]> productList = productService.getProductInfoByName(search);
        if (productList == null || productList.size() != 1) {
            session.put("productList", productList);
            return "many";
        } else {
            // 只有一个时直接到商品详细页
            Object[] o = productService.getProduct(productList.get(0)[0]);
            Product product = (Product) o[0];
            Category category = productService.getCategory((String) o[1]);
            session.put("product", product);
            session.put("category", category);
            return "one";
        }
    }

    /**
     * 自动补全，获取商品名称
     * @return
     * @throws IOException
     */
    public String matchName() throws IOException {
        List<String> productInfo = productService.getProductNameList(search);
        JsonArray array = new JsonArray();
        for (String name : productInfo
                ) {
            JsonObject temp = new JsonObject();
            temp.addProperty("value", name);
            array.add(temp);
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        out.print(array.toString());
        out.flush();
        out.close();
        return "";
    }

    /**
     * 获取商品的库存
     * @return
     * @throws IOException
     */
    public String productStock() throws IOException {
        int stock = productService.getStock(productId);
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        out.print(stock);
        out.flush();
        out.close();
        return "";
    }

    /**
     * 获取商品的库存和价格
     * @return
     * @throws IOException
     */
    public String getStockAndPrice() throws IOException {
        Map<String, String> info = productService.getStockAndPrice(productId);
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("price", info.get("price"));
        json.addProperty("stock", info.get("stock"));
        out.write(json.toString());
        out.flush();
        out.close();
        return "";
    }

}
