<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>accueil</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<img src="logoStatique.gif" width="175" height="154"> 
<label>&nbsp;&nbsp;&nbsp;<font color="#0033FF" size="+7"><strong>&nbsp;&nbsp;&nbsp;&nbsp;Bienvenue 
sur BeOnTime</strong></font></label>
<p>&nbsp;</p><fieldset>
<legend><strong>Identification</strong></legend>
<% String login=request.getParameter("login");
	String password=request.getParameter("password");
	String formation=request.getParameter("formation");
%>
<% if((login=="")||(password=="")%>
<% <form name="form1" method="post" action="index.jsp">
  Login:&nbsp;&nbsp; 
  <input name="login" type="text" id="login">
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <label>Password:&nbsp;</label>
  <input name="password" type="password" id="password">
  &nbsp;&nbsp;&nbsp;Visualiser Emploi du temps de&nbsp; 
  <select name="formation" onChange="MM_jumpMenu('parent',this,0)">
    <option>Choisir formation</option>
  </select>
</form> %>
<%else%>
<% <form name="form2" method="post" action="index.jsp">
  <p>&nbsp;Vous etes &nbsp;&nbsp; 
  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Visualiser 
    votre emploi d temps </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Visualiser 
    Emploi du temps de&nbsp; 
    <select name="formation" onChange="MM_jumpMenu('parent',this,0)">
    <option>Choisir formation</option>
  </select>
  </p>
  <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.htm">Deconnexion</a></p>
  </form> %>
  
</fieldset>
&nbsp;
&nbsp;
&nbsp;
<% <table width="95%" height="382" border="1" bordercolor="#CCCCCC" id="emploi_du_temps">
  <tr> 
    <td width="6%" rowspan="2">&nbsp;</td>
    <td ><strong>8</strong></td>
    <td><strong>9</strong></td>
    <td><strong>10</strong></td>
    <td><strong>11</strong></td>
    <td><strong>12</strong></td>
    <td><strong>13</strong></td>
    <td><strong>14</strong></td>
    <td><strong>15</strong></td>
    <td><strong>16</strong></td>
    <td><strong>17</strong></td>
    <td><strong>18</strong></td>
    <td><strong>19</strong></td>
    <td><strong>20</strong></td>
  </tr>
  <tr> 
    <td width="4%" height="24"> 
      <pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
    <td width="4%"><pre> <font size="4">|||</font></pre></td>
  </tr>
  <tr> 
    <td>Lundi</td>
    <td colspan="13" rowspan="7">&nbsp;</td>
  </tr>
  <tr> 
    <td>Mardi</td>
  </tr>
  <tr> 
    <td>Mercredi</td>
  </tr>
  <tr> 
    <td>Jeudi</td>
  </tr>
  <tr> 
    <td>Vendredi</td>
  </tr>
  <tr> 
    <td>Samedi</td>
  </tr>
  <tr> 
    <td>Dimanche</td>
  </tr>
</table> %>
</body>
</html>
