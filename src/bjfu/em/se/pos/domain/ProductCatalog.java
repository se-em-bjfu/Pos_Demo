package bjfu.em.se.pos.domain;

import bjfu.em.se.pos.persist.ProductCatalogPersistor;

/**
 * 超市商品目录
 * @author Roy
 *
 */
public class ProductCatalog {
    ProductCatalogPersistor persistor;

    public ProductCatalog() {
        persistor = ProductCatalogPersistor.Instance;
    }

    /**
     * 查找某id对应的商品
     * @param id 商品id
     * @return 对应的商品信息
     */
    public ProductDescription getProduct(String id) {
        return persistor.getProduct(id);
    }

    /**
     * 向产品目录中添加商品
     * @param id 商品id
     * @param name 商品名称
     * @param description 商品描述
     * @param price 商品单价
     */
    public void addProductDescription(String id, String name,String description ,int price){
        persistor.addProductDescription(id,name,description,price);
    }

    /**
     * 初始化商品目录
     */
    public void initProducts(){
        if (persistor.countProducts()<=0) {
            persistor.addProductDescription("1", "面包", "好吃的面包", 400);
            persistor.addProductDescription("2", "香肠", "好吃的香肠", 350);
            persistor.addProductDescription("3", "榨菜", "好吃的榨菜", 050);
            persistor.addProductDescription("4", "方便面", "好吃的方便面", 300);
            persistor.addProductDescription("5", "可乐", "好喝的可乐", 200);
            persistor.addProductDescription("6", "饼干", "好吃的饼干", 430);
            persistor.addProductDescription("7", "蛋糕", "好吃的蛋糕", 680);
            persistor.addProductDescription("8", "牛奶", "盒装牛奶", 530);
            persistor.addProductDescription("9", "煮鸡蛋", "好吃的鸡蛋", 150);
            persistor.addProductDescription("10", "鸡腿", "好吃的鸡腿", 1200);
        }
    }

    public ProductCatalogPersistor getPersistor() {
        return persistor;
    }
}
