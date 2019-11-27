package bjfu.em.se.pos.domain;

import bjfu.em.se.pos.persist.SalePersistor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 超市类
 * @author Roy
 *
 */
public class Store {
	private String name;
	private SalePersistor salePersistor;
	private ProductCatalog catalog;
	
	public Store(String name, ProductCatalog productCatalog) {
		this.name=name;
		salePersistor=SalePersistor.Instance;
		this.catalog = productCatalog;
	}
	
	/**
	 * 添加已处理完毕的销售
	 * @param sale 
	 */
	public void addSale(Sale sale){
		salePersistor.addSale(sale);
	}
	
	/**
	 * 返回已处理的销售列表
	 * @return
	 */
	public List<Sale> getSales() {
		return salePersistor.getSales();
	}

	/**
	 * 为Sale记录提供唯一Id
	 *
	 * @return 唯一id
	 */
	public long nextSaleId() {
		//简单起见，我们用当前时间作为唯一id。在实际应用中不可以这么做（可能会出现重复id）！！！！
		return new Date().getTime();
	}
}
