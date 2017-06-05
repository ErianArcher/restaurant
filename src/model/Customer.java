package model;

import java.util.Set;

/**
 * Created by a106-19 on 2017/5/22.
 */
// @TargetTable(precustomer)
    /*
    CREATE TABLE customer (
    id INT NOT NULL auto_increment,
    name VARCHAR(30) NOT NULL,
    phone VARCHAR(16) default NULL,
    PRIMARY KEY(id))
     */
public class Customer {

    public final static String TABLENAME = "customer";

    // @FieldName(id) @PrimaryKey @auto_increment
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // @FieldName(name) @NotNull @Property(varchar(30))
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @FieldName(phone) @NotNull @Property(varchar(16))
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Customer() {
    }

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (!name.equals(customer.name)) return false;
        return phone != null ? phone.equals(customer.phone) : customer.phone == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
