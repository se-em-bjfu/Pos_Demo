package bjfu.em.se.pos.persist.serializer;

import bjfu.em.se.pos.domain.payment.*;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

public class PaymentSerializer implements Serializer<Payment> {
    @Override
    public void serialize(@NotNull DataOutput2 out, @NotNull Payment value) throws IOException {
        out.writeUTF(value.getType().toString());
        out.packInt(value.getAmount());
    }

    @Override
    public Payment deserialize(@NotNull DataInput2 input, int available) throws IOException {
        String typeStr = input.readUTF();
        PaymentType type = PaymentType.valueOf(typeStr);
        int amount = input.unpackInt();
        Payment payment=null;
        switch(type) {
            case ByCash:
                payment=new CashPayment(amount);
                break;
            case ByCreditCard:
                payment=new CreditCardPayment(amount);
                break;
            case ByCheck:
                payment=new CheckPayment(amount);
                break;
        }
        return payment;
    }
}
