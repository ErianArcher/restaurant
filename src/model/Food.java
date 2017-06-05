package model;

/**
 * Created by a106-19 on 2017/5/22.
 */
// @TargetTable(food)
    /*
    CREATE TABLE food (
    id INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(3,2) default 0.0,
    type VARCHAR(10) default NULL,
    introduction TEXT default NULL,
    PRIMARY KEY(id))
     */
public class Food {

    public final static String TABLENAME = "food";
    public final static String BEVERAGE = "beverage";
    public final static String DISH = "dish";

    private int id;
    // @FieldName(name) @NotNull @Property(varchar(50))
    private String name;
    // @FieldName(price) @NotNull @Property(decimal(3,2))
    private double price;
    // @FieldName(type) @Property(varchar(15)) food type 食物种类
    private String type;
    // @FieldName(introduction) @Property(text) brief introduction of food
    private String introduction;

    public Food() {
    }

    public Food(String name, double price, String type, String introduction) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != food.id) return false;
        if (Double.compare(food.price, price) != 0) return false;
        if (!name.equals(food.name)) return false;
        if (type != null ? !type.equals(food.type) : food.type != null) return false;
        return introduction != null ? introduction.equals(food.introduction) : food.introduction == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}