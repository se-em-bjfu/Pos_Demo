package bjfu.em.se.pos.persist;

import bjfu.em.se.pos.domain.ProductDescription;
import bjfu.em.se.pos.persist.serializer.ProductDescriptionSerializer;
import org.mapdb.*;

import java.util.Map;

/**
 * 超市商品目录
 * @author Roy
 *
 */
public class ProductCatalogPersistor {
    Map<String, ProductDescription> products;
    DB db;

    public static ProductCatalogPersistor Instance = new ProductCatalogPersistor();

    private ProductCatalogPersistor() {
        db= DBMaker.fileDB("products.db").closeOnJvmShutdown().make();
        ProductDescriptionSerializer productDescriptionSerializer=new ProductDescriptionSerializer();
        products=db.hashMap("product_description_map", Serializer.STRING,
                productDescriptionSerializer).createOrOpen();
    }

    /**
     * 查找某id对应的商品
     * @param id 商品id
     * @return 对应的商品信息
     */
    public ProductDescription getProduct(String id) {
        return products.get(id);
    }

    /**
     * 向产品目录中添加商品
     * @param id 商品id
     * @param name 商品名称
     * @param description 商品描述
     * @param price 商品单价
     */
    public void addProductDescription(String id, String name,String description ,int price){
        ProductDescription desc=new ProductDescription(id,name,description,price);
        products.put(id, desc);
        db.commit();
    }

    /**
     * 返回现有的商品种类数
     *
     * @return 商品种类数
     */
    public int countProducts() {
        return products.size();
    }
}
