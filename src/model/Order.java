package model;

import java.util.Set;

/**
 * Created by a106-19 on 2017/5/22.
 */
// @TargetTable(order)
    /*
    CREATE TABLE order (
    id INT NOT NULL auto_increment,
    delivery_boy_id INT default -1,
    customer_id INT NOT NULL,
    PRIMARY KEY(id))
     */
public class Order {

    public final static String TABLENAME = "order";

    // @FieldName(id) @PrimaryKey @NotNull @AutoIncrement @Property(int)
    private int id;

    private DeliveryBoy deliveryBoy;

    public Order() {
    }

    public Order(DeliveryBoy deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryBoy getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(DeliveryBoy deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }
}
