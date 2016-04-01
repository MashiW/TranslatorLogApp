<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="css/mystyles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="js/myscripts.js"></script>
</head>

<body id="translatebody">

<div class="container-fluid">
        <div id="trjumbotron" class="jumbotron">
        <div class="top-buffer" id="divheader">
        <h2>Welcome to Translation<b> <%= session.getAttribute("sessionname")%>... !</b></h2>
        </div>
        <br>
        <form role="form" action="TranslateServlet" method="post">
        <div class="row top-buffer">

            <div class="col-md-2"></div>
          <div class="col-md-4">
              <strong>From:</strong> <br/>
              <%
              out.println("<textarea name=\"txtFromText\" id=\"fromText\">");
                    String s1 = (String) request.getAttribute("fromText");
                        if (s1!= null) {
                            out.print(s1);
                    }
                 out.println("</textarea>");
              %>
              <br>
              <div class="top-buffer">
              <select name="frmType" class="form-control" style="width:150px;">
                  <%
                  ArrayList<String> ar=new ArrayList<String>();
                  ar=(ArrayList<String>)request.getAttribute("list");

                      for(int i=0; i<ar.size(); i++){
                      if(ar.get(i).equals(request.getAttribute("selectedFrom"))){
                      out.println("<option selected>"+ar.get(i)+"</option>");
                      }else{
                      out.println("<option>"+ar.get(i)+"</option>");
                      }
                      }
                      %>
              </select>
              </div>
          </div>

           <div class="col-md-4">
               <strong> To:</strong> <br>
                <%
                out.println("<textarea name=\"txtToText\" id=\"toText\">");
                    String s2 = (String) request.getAttribute("textreply");
                        if (s2 != null) {
                            out.print(s2);
                        }
                out.println("</textarea>");
                %>

                <br>
                <div class="top-buffer">
                <select name="toType" class="form-control" style="width:150px;">
                    <%
                    ArrayList<String> ar2=new ArrayList<String>();
                    ar2=(ArrayList<String>)request.getAttribute("list");

                        for(int i=0; i<ar2.size(); i++){
                        if(ar2.get(i).equals(request.getAttribute("selectedTo"))){
                        out.println("<option selected>"+ar2.get(i)+"</option>");
                        }else{
                        out.println("<option>"+ar2.get(i)+"</option>");
                        }
                        }
                        %>
                </select>
                </div>
            </div>
            <div class="col-md-2"></div>
            </div>
                <div class="top-buffer" style="margin:5% 30% 0 30%;">
                    <button type="button" onclick="swapText()" class="btn btn-default">Swap text</button>
                    <input type="submit" class="btn btn-default" value="Translate" align>
                    <input type="reset" class="btn btn-default" value="Reset">

                </div>
            </form>
            <br>
            <div class="row top-buffer">
                <form action="LogoutServlet" method="get">
                    <button type="submit" class="btn btn-default glyphicon glyphicon-log-out" align="right"/>&nbsp;Logout</button>
                </form>
                  <div class="row">
                        <h5><b>Powered by Yandex.Translate: </b><a href="http://translate.yandex.com/"> http://translate.yandex.com/</a></h5>
                  </div>
            </div>
        </div>
    </div>
</body>
</html>
