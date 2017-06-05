<%--
  Created by IntelliJ IDEA.
  User: erian
  Date: 17-6-3
  Time: 下午7:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Food</title>
    <style>
        .errstyle {border:1px solid red;}
        .bor {border: 1px solid #C0C0C0}
    </style>
    <script type="text/javascript">
        function ltrim(str){ //删除左边的空格
            return str.replace(/(^\s*)/g,"");
        }
        //检查select
        function checkSelect(obj) {
            var val = obj.value;
            if(val == "") {
                obj.className = "errstyle";
                return false;
            } else {
                return true;
            }
        }
        //检查input
        function checkInput(obj) {
            var val = obj.value;
            //这个地方需要处理一下空格
            val = ltrim(val);
            if(val == "") {
                obj.className = "errstyle";
                return false;
            } else {
                return true;
            }
        }

        //获取焦点时清除错误
        function clearerr(obj) {
            if(obj.className = "errstyle") {
                obj.className = "bor";
            }
        }

        function check(){
            var name = checkInput(document.getElementById('name'));
            var price = checkInput(document.getElementById('price'));
            var type = checkSelect(document.getElementById('type'));
            var introduction = checkInput(document.getElementById('introduction'));

            if (name && price && type && introduction) {
                return true;
            } else {
                alert('Not complete the infomation');
                return false;
            }
        }

        function ValidateFloat(e, pnumber)
        {
            if (!/^\d+[.]?\d*$/.test(pnumber))
            {
                e.value = /\d+[.]?\d*/.exec(e.value);
            }
            return false;
        }
    </script>
</head>
<body>
<form name="addFood" method="post" action="AddFoodServlet" onsubmit="return check();">
    <table>
        <tr>
            <td>Food name: </td>
            <td class="bor" onfocus="clearerr(this)"><input name="name" type="text" id="name"></td>
        </tr>

        <tr>
            <td>Food price: </td>
            <td class="bor" onfocus="clearerr(this)"><strong>$</strong>
                <input name="price" id="price" onkeyup="return ValidateFloat(this,value)"></td>
        </tr>

        <tr>
            <td>Food type: </td>
            <td class="bor" onfocus="clearerr(this)"><select name="type" id="type">
                <option value="dish" selected>dish</option>
                <option value="beverage">beverage</option>
            </select> </td>
        </tr>

        <tr>
            <td>Introduction: </td>
            <td class="bor" onfocus="clearerr(this)">
                <textarea name="introduction" type="text" cols="40" rows="5" id="introduction"></textarea>
            </td>
        </tr>
        <tr>
            <td><input name="Submit" type="submit" value="submit"></td>
            <td><input name="Reset" type="reset" value="reset"></td>
        </tr>
    </table>
</form>
</body>
</html>
