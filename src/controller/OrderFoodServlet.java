package controller;

import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by erian on 17-6-4.
 */
@WebServlet(name = "OrderFoodServlet", urlPatterns = "/OrderFoodServlet")
public class OrderFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String customerName = request.getParameter("customerName");
        String customerPhone = request.getParameter("customerPhone");
        String tmp = request.getParameter("deliveryBoy");
        int areaCode = tmp.isEmpty()?-1:Integer.parseInt(tmp);
        boolean isPreCustomer = false;

        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        // Check if this customer has previous order
        Customer customer = null;
        List customerList = session.createQuery("FROM Customer WHERE name='"+
        customerName+"'").list();
        if (customerList.isEmpty()) {
            customer = new Customer(customerName, customerPhone);
        } else {
            isPreCustomer = true;
            customer = (Customer) customerList.get(0);
            customer.setPhone(customerPhone);
        }

        // Create new order
        Order order = new Order();
        if (areaCode != -1) {
            DeliveryBoy deliveryBoy = new DeliveryBoy(areaCode);
            List l = session.createQuery("FROM DeliveryBoy WHERE areaCode="+areaCode).list();
            deliveryBoy.setId(((DeliveryBoy)l.get(0)).getId());
            order.setDeliveryBoy(deliveryBoy);
        }

        int customerId = 0;
        if (isPreCustomer) {
            customerId = customer.getId();
            if (customer.getOrders()==null) {
                HashSet<Order> orders = new HashSet<>();
                orders.add(order);
                customer.setOrders(orders);
            } else {
                customer.getOrders().add(order);
            }
            session.update(customer);// Cannot use another session
        } else {
            HashSet<Order> orders = new HashSet<>();
            orders.add(order);
            customer.setOrders(orders);
            customerId = (Integer) session.save(customer);
        }
        tx.commit();

        // Get orderId
        @SuppressWarnings("JpaQlInspection")
        List orderList = session.createQuery("FROM Order WHERE customer_id=" +
        customerId + " ORDER BY id DESC").list();
        order.setId(((Order) orderList.get(0)).getId());

        // Update order
        List foodList = session.createQuery("FROM Food").list();
        double totalPrice = 0.0;
        for (int i = 0; i <  foodList.size(); i++) {
            int IQuantity = Integer.parseInt(request.getParameter(String.valueOf(i+1)));
            if (IQuantity > 0) {
                tx = session.beginTransaction();
                Food food = (Food)foodList.get(i);
                session.save(new OrderDetail(food, order, IQuantity));
                totalPrice += food.getPrice() * IQuantity;
                tx.commit();
            }
        }

        PrintWriter writer = response.getWriter();
        writer.print("<h1>Successfully order</h1>");
        writer.print("Dear customer: " + customer.getName() + "<br>");
        if (isPreCustomer) {
            totalPrice *= 0.9;
            writer.print("You have been to our restaurant, and our restaurant " +
            "decides to give you a 10% off dicount<br>");
        }
        writer.print("Your order total price is " + String.valueOf(totalPrice)+"<br>");
        writer.print("Your order is: <br>");
        //noinspection JpaQlInspection
        for(Object o:
                session.createQuery(
                        "FROM OrderDetail WHERE order_id="+order.getId()).list()) {
            OrderDetail orderDetail = (OrderDetail) o;
            Food f = orderDetail.getFood();
            writer.print("<li>Food name: " + f.getName() + " Price: " + f.getPrice() +
            " Quantity: " + orderDetail.getQuantity() + "</li>");
        }

        session.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
