package model;

/**
 * Created by a106-19 on 2017/5/22.
 */
// @TargetTable(order_detail)
    /* CREATE TABLE orderdetail (
        id INT NOT NULL auto_increment,
        food_id INT NOT NULL,
        order_id INT NOT NULL,
        quantity INT default 0 check(quantity>0),
        PRIMARY KEY(id))
    */
public class OrderDetail {

    public final static String TABLENAME = "orderdetail";

    // @FieldName(id) @NotNull @Property(int)
    private int id;

    private Food food;// Many to one

    // @FieldName(quantity) @NotNull @Constraint(quantity>0) @Property(tinyint)
    private int quantity;

    private Order order; // Many to one

    public OrderDetail() {
    }

    public OrderDetail(Food food, Order order, int quantity) {
        this.food = food;
        this.order = order;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        return food.equals(that.food);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + food.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
