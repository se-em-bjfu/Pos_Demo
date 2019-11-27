package bjfu.em.se.pos.persist;

import bjfu.em.se.pos.domain.Sale;
import bjfu.em.se.pos.persist.serializer.ProductDescriptionSerializer;
import bjfu.em.se.pos.persist.serializer.SaleSerializer;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.*;

public class SalePersistor {
    DB db;
    Map<Long, Sale> sales;
    Comparator<Sale> comparator=new Comparator<Sale>() {
        @Override
        public int compare(Sale o1, Sale o2) {
            return (int) (o1.getId()-o2.getId());
        }
    };

    public static SalePersistor Instance = new SalePersistor();

    public SalePersistor() {
        db= DBMaker.fileDB("sales.db").closeOnJvmShutdown().make();
        SaleSerializer saleSerializer=new SaleSerializer();
        sales=db.hashMap("product_description_map", Serializer.LONG,
                saleSerializer).createOrOpen();
    }

    public void addSale(Sale sale) {
        sales.put(sale.getId(),sale);
        db.commit();
    }

    public List<Sale> getSales() {
        List<Sale> result=new ArrayList<>(sales.values());
        result.sort(comparator);
        return result;
    }
}
