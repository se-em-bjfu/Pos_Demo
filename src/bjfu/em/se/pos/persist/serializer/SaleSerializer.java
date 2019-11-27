package bjfu.em.se.pos.persist.serializer;

import bjfu.em.se.pos.domain.ProductDescription;
import bjfu.em.se.pos.domain.Sale;
import bjfu.em.se.pos.domain.SalesLineItem;
import bjfu.em.se.pos.domain.payment.Payment;
import bjfu.em.se.pos.persist.ProductCatalogPersistor;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleSerializer implements Serializer<Sale> {
    PaymentSerializer paymentSerializer;
    public SaleSerializer(){
        paymentSerializer = new PaymentSerializer();
    }

    @Override
    public void serialize(@NotNull DataOutput2 out, @NotNull Sale value) throws IOException {
        out.packLong(value.getId());
        Serializer.DATE.serialize(out,value.getDate());
        paymentSerializer.serialize(out,value.getPayment());
        out.packInt(value.getLineItems().size());
        for (SalesLineItem item:value.getLineItems()){
            out.writeUTF(item.getProductDescription().getId());
            out.packInt(item.getQuantity());
        }
    }

    @Override
    public Sale deserialize(@NotNull DataInput2 input, int available) throws IOException {
        long id = input.unpackInt();
        Date date = Serializer.DATE.deserialize(input,available);
        Payment payment = paymentSerializer.deserialize(input,available);
        int size = input.unpackInt();
        List<SalesLineItem> items =new ArrayList<>();
        for (int i=0;i<size;i++) {
            String productId = input.readUTF();
            ProductDescription description = ProductCatalogPersistor.Instance.getProduct(productId);
            int quantity = input.unpackInt();
            SalesLineItem item = new SalesLineItem(description,quantity);
            items.add(item);
        }
        Sale sale = new Sale(id,date,payment,items);
        return sale;
    }
}
