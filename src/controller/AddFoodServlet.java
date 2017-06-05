package controller;

import model.Food;
import utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by erian on 17-6-3.
 */
@WebServlet(name = "AddFoodServlet",
        urlPatterns = "/AddFoodServlet")
public class AddFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String type = request.getParameter("type");
        String introduction = request.getParameter("introduction");

        Food food = new Food(name, price,
                type.equals(Food.DISH)?Food.DISH:Food.BEVERAGE,
                introduction);
        List foodList = HibernateUtils.list(Food.TABLENAME, Food.class, false);

        boolean isExist = false;
        for (Object o:
             foodList) {
            Food foodInList = (Food)o;
            if (food.getName().equals(foodInList.getName())) {
                food.setId(foodInList.getId());
                isExist = true;
            }
        }
        PrintWriter out = response.getWriter();
        if (isExist) {
            HibernateUtils.update(food);
            out.print("<h1>Successfully update</h1>");
            out.print(food);
        }
        else {
            HibernateUtils.add(food);
            out.print("<h1>Successfully add</h1>");
            out.print(food);
        }


        out.print("<h1>Success</h1>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
