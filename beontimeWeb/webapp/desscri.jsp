<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>emploi_du_temps</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
</head>

<body>
<jsp:useBean id="emploi" scope="session" class=""/>
<img src="logoStatique.gif" width="175" height="154"> 
<label>&nbsp;&nbsp;&nbsp;<font color="#0033FF" size="+7"><strong>&nbsp;&nbsp;&nbsp;&nbsp;Bienvenue 
sur BeOnTime</strong></font></label>
<p>&nbsp;</p><fieldset>
<legend><strong>Identification</strong></legend>
<% String login=request.getParameter("login");
	String password=request.getParameter("password");
	String formation=request.getParameter("formation");
%>

<form name="form1" method="post" action="">
  
  <p>&nbsp;Vous etes &nbsp;&nbsp;
  <% if((login=="")||(password=="")%>
	<% =Response.write("etudiant")%>
	<%else%>
  <%=Request.Form("login")%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Visualiser 
    votre emploi d temps </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Visualiser 
    l'emploi du temps de&nbsp; 
    <select name="formation" onChange="MM_jumpMenu('parent',this,0)">
      <option value="index.htm">Choisir formation</option>
      <option value="desscri.jsp">DESS CRI</option>
      <option>Maitrise</option>
      <option>License</option>
    </select>
  </p>
  <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.htm">Deconnexion</a></p>
  </form>
<table width="75%" border="1">
  <tr>
    <td>
	<%= class.methode()%>
	&nbsp;</td>
  </tr>
</table>
</fieldset>
</body>
</html>
