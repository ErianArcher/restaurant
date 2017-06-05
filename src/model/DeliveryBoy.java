package model;

/**
 * Created by a106-19 on 2017/5/22.
 */
// @TargetTable(delivery_area)
    /*
    CREATE TABLE deliveryboy (
    id INT NOT NULL auto_increment,
    area_code INT NOT NULL,
    PRIMARY KEY(id))
     */
public class DeliveryBoy {

    public final static String TABLENAME = "deliveryboy";

    private int id;

    private int areaCode;

    public DeliveryBoy() {
    }

    public DeliveryBoy(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryBoy that = (DeliveryBoy) o;

        if (id != that.id) return false;
        return areaCode == that.areaCode;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + areaCode;
        return result;
    }
}
