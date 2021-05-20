<%@ page import="com.teamdev.booby.Booby" %>
<%@ page import="com.teamdev.booby.impl.BoobyImpl" %>
<%@ page import="com.teamdev.booby.impl.BoobyCompilerFactoryImpl" %>
<%@ page import="com.teamdev.booby.runtime.RuntimeEnvironment" %>
<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 14.05.2021
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<body>
<%
  String input=request.getParameter("input");
  if(input==null||input.trim().equals("")){
    out.print("<h3>Rewrite string</h3><br>");
  }else{
    try{
      StringBuilder builder = new StringBuilder();
      Booby calculator = new BoobyImpl(new BoobyCompilerFactoryImpl(builder));
      RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
      System.out.println(input);
      calculator.execute(input, environment);

      out.print("<p>"+ builder +"</p>");
    }catch(Exception e){out.print(e);}

  }//end of else
%>
</body>
</html>
