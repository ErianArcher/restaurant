<%--
  Created by IntelliJ IDEA.
  User: erian
  Date: 17-5-30
  Time: 上午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
import="model.DeliveryBoy" %>
<%@ page import="utils.HibernateUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Food" %>
<html>
  <head>
    <title>Welcome to Restaurant</title>
      <script type="text/javascript">
          function ltrim(str){ //删除左边的空格
              return str.replace(/(^\s*)/g,"");
          }

          //检查input
          function checkInput(obj) {
              var val = obj.value;
              //这个地方需要处理一下空格
              val = ltrim(val);
              if(val == "") {
                  return false;
              } else {
                  return true;
              }
          }

          function check() {
              var customerName = checkInput(document.getElementById('customerName'));
              var customerPhone = checkInput(document.getElementById('customerPhone'));

              if (customerName && customerPhone) {
                  return true;
              } else {
                  alert('Not complete the infomation');
                  return false;
              }

          }

          function ValidateNum(e, pnumber)
          {
              if (!/^\d+$/.test(pnumber))
              {
                  e.value = /\d+/.exec(e.value);
              }
              return false;
          }
      </script>
  </head>
  <body>
  <h1>Welcome</h1>
  <h2>Select your food:</h2>
  <form id="orderList" name="orderList" method="post" action="OrderFoodServlet"
        onsubmit="return check()">
      <div>
      <table border="1">
          <tr>
              <th>NO</th>
              <th>Name</th>
              <th>Price($)</th>
              <th>Type</th>
              <th>Introduction</th>
              <th>Quantity</th>
          </tr>
          <%
              List foodList = HibernateUtils.list(Food.TABLENAME, Food.class, false);
              for (Object o:
                   foodList) {
                  Food food = (Food) o;
                  %>
          <tr>
              <td><%= food.getId()%></td>
              <td><%= food.getName()%></td>
              <td><%= food.getPrice()%></td>
              <td><%= food.getType()%></td>
              <td><%= food.getIntroduction()%></td>
              <td><input id="<%=food.getId()%>" name="<%=food.getId()%>"
              type="number" value="0"></td>
          </tr>
          <%
              }
          %>
      </table>
      </div>
      <div>
      <h2>Your information:</h2>
      <li>Name: <span style="color: red">*</span>
          <input id="customerName" name="customerName" type="text"></li>
      <li>Phone: <span style="color: red">*</span>
          <input id="customerPhone" name="customerPhone" type="text"
      onkeyup="return ValidateNum(this, value)"></li>
      <li>
          Delivery area code:
          <select id="deliveryBoy" name="deliveryBoy">
              <option value="" selected></option>
              <%
                  List list = HibernateUtils.list(DeliveryBoy.TABLENAME, DeliveryBoy.class, false);
                  for (Object o:
                          list) {
                      DeliveryBoy deliveryBoy = (DeliveryBoy) o;
              %> <option value="<%=deliveryBoy.getAreaCode()%>">
              <%= deliveryBoy.getAreaCode()%>
          </option><%
                  }
              %>
          </select>
          <span style="color: red">Empty means you don't need delivery service.</span>
      </li>
      </div>
      <div>
          <input value="submit" name="submit" type="submit">
          <input value="reset" name="reset" type="reset">
      </div>
  </form>
  </body>
</html>
