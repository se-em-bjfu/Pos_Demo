package bjfu.em.se.pos.persist.serializer;

import bjfu.em.se.pos.domain.ProductDescription;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

public class ProductDescriptionSerializer implements Serializer<ProductDescription> {
    @Override
    public void serialize(@NotNull DataOutput2 out, @NotNull ProductDescription value) throws IOException {
        out.writeUTF(value.getId());
        out.writeUTF(value.getName());
        out.writeUTF(value.getDescription());
        out.packInt(value.getPrice());
    }

    @Override
    public ProductDescription deserialize(@NotNull DataInput2 input, int available) throws IOException {
        String id = input.readUTF();
        String name = input.readUTF();
        String desc = input.readUTF();
        int price = input.unpackInt();
        ProductDescription description=new ProductDescription(id,name,desc,price);
        return description;
    }

}
