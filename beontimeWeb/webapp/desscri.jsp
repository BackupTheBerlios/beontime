<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Document sans titre</title>
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
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="158" height="132">
  <param name="movie" value="test2.swf">
  <param name="quality" value="high">
  <embed src="test2.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="158" height="132"></embed></object>
<label>&nbsp;&nbsp;&nbsp;<font color="#0033FF" size="+7"><strong>&nbsp;&nbsp;&nbsp;&nbsp;Bienvenue 
sur BeOnTime</strong></font></label>
<p>&nbsp;</p><fieldset>
<legend><strong>Identification</strong></legend>

<form name="form1" method="post" action="">
  <label></label>
  <p>&nbsp;Vous etes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Visualiser 
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
</fieldset></body>
</html>
