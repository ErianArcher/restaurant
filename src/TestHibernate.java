import model.*;
import utils.HibernateUtils;

import java.util.HashSet;

/**
 * This class is for testing if hibernate is working correctly
 * Created by erian on 17-6-1.
 */
public class TestHibernate {

    public static void main (String[] args) {

    }

    private static void testFood() {
        Food food1 = new Food("food1", 2.0, Food.DISH, "Test Intro1");
        HibernateUtils.add(food1);
        Food food2 = new Food("food2", 2.1, Food.BEVERAGE, "Test Intro2");
        HibernateUtils.add(food2);
        HibernateUtils.list(Food.TABLENAME, Food.class, true);
        food2.setName("beverage2");
        HibernateUtils.update(food2);
        HibernateUtils.list(Food.TABLENAME, Food.class, true);
        HibernateUtils.delete(food2);
        HibernateUtils.delete(food1);
        HibernateUtils.list(Food.TABLENAME, Food.class, true);
    }

    private static void testCustomer() {

    }

    private static void testDeliveryBoy() {

    }

    private static void testOrder() {

    }

    private static void testOrderDetail() {

    }
}
