<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Login</title>

    <style>
        table {
        border-collapse: collapse;
        border: 2px solid black;
        width:60%;
        margin: auto;
        margin-top: 0%;
        cell-padding: 10px;
        }

        td {
        padding: 10px;
        }
    </style>

</head>

<body bgcolor="#d9b3ff">
<h2>Translate !</h2>

<table bgcolor="white" align="">
    <form action="TranslateServlet" method="post">
        <tr>
            <td>From:</td>
            <td>To:</td>
        </tr>
        <tr>
            <td>
                <textarea name="txtFromText"></textarea>
            </td>
            <td>
                <textarea name="txtToText">
                    <% String s2 = (String) request.getAttribute("textreply");%>
                        <% if (s2 != null) {
                            out.println(s2);
                        }%>
                </textarea>
            </td>
        </tr>
        <tr>
            <td>
                <select name="frmType">
                    <%
                        ArrayList<String> ar=new ArrayList<String>();
                        ar=(ArrayList<String>)request.getAttribute("list");
                        for(int i=0; i<ar.size(); i++){
                            out.println("<option>"+ar.get(i)+"</option>");
                        }
                    %>
                </select>
        </td>
            <td>
                <select name="toType">
                   <%
                       ArrayList<String> ar2=new ArrayList<String>();
                       ar2=(ArrayList<String>)request.getAttribute("list");
                       for(int i=0; i<ar2.size(); i++){
                           out.println("<option>"+ar2.get(i)+"</option>");
                       }
                   %>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Translate">
            </td>
        </tr>
        <tr>
            <td>
            <br />
            <h5>Powered by Yandex.Translate<a href="http://translate.yandex.com/">http://translate.yandex.com/</a></h5>
            </td>
        </tr>
    </form>
</table>
</body>
</html>
