<%@ page contentType="text/html; charset=iso-8859-1" language="java"
import="fr.umlv.smoreau.beontime.dao.*,
		fr.umlv.smoreau.beontime.filter.TimetableFilter,
		fr.umlv.smoreau.beontime.model.Formation,
		fr.umlv.smoreau.beontime.model.user.User,
		fr.umlv.smoreau.beontime.model.element.Room,
		fr.umlv.smoreau.beontime.model.timetable.*,
		fr.umlv.smoreau.beontime.client.ArrangeCourses,
		java.util.*,
		java.text.SimpleDateFormat" %>

<%
SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");

Calendar date = Calendar.getInstance();
String week = request.getParameter("week");
if (week != null)
	date.set(Calendar.WEEK_OF_YEAR, (new Integer(week)).intValue());
String year = request.getParameter("year");
if (year != null)
	date.set(Calendar.YEAR, (new Integer(year)).intValue());
date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

Collection formations = DaoManager.getFormationDao().getFormations();
String idFormation = request.getParameter("id_formation");

String login = request.getParameter("login");
String password = request.getParameter("password");
User teacher = null;
if (login != null && !"".equals(login) && password != null && !"".equals(password)) {
	teacher = DaoManager.getUserDao().testLoginPwd(login, password);
	if (teacher == null || !UserDao.TYPE_TEACHER.equals(teacher.getUserType()))
		teacher = null;
}

%>

<html>
	<head>
		<title>BeOnTime</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta name="author" content="BeOnTime">

		<style type="text/css">
			input.link {
				border:0px;
				background-color: white;
				font: 12px verdana,arial,helvetica;
				color: #0000FF;
				text-decoration:underline;
				cursor: hand;
			}
		</style>
	</head>

	<body>
		<table width="100%">
			<tr>
				<td align="center">
					<img src="logoBoT.png" />
				</td>
				<td align="center">
					<font color="#0033FF" size="+7">
						<strong>Bienvenue sur BeOnTime</strong>
					</font>
				</td>
			</tr>
		</table>

		<br/>
		
		<fieldset>
			<legend><strong>Identification</strong></legend>
<%
	
%>

			<form method="post" action="">
				<table width="100%">
					<tr>
						<% if (teacher == null) { %>
						<td align="left">
							Identifiant :
							<input name="login" type="text" id="login" />
						</td>
						<td align="center">
							Mot de passe :
							<input name="password" type="password" id="password" />
						</td>
						<td align="center">
							<input value="S'identifier" type="submit" />
						</td>
						<% if (idFormation != null) { %>
						<input type="hidden" name="id_formation" value="<%=idFormation %>" />
						<input type="hidden" name="week" value="<%=date.get(Calendar.WEEK_OF_YEAR) %>" />
						<input type="hidden" name="year" value="<%=date.get(Calendar.YEAR) %>" />
						<% } %>
						</form>
						<% } else { %>
						<td align="left">
							Vous �tes <strong><%=teacher.getFirstName() %> <%=teacher.getName() %></strong>
						</td>
						<td align="center">
							<form method="post" action="">
								<input type="hidden" name="login" value="<%=login %>" />
								<input type="hidden" name="password" value="<%=password %>" />
								<input type="hidden" name="id_formation" value="0" />
								<input type="submit" value="Visualiser votre emploi du temps" class="link" />
							</form>
							<form method="post" action="">
								<% if (!"0".equals(idFormation)) { %>
								<input type="hidden" name="id_formation" value="<%=idFormation %>" />
								<input type="hidden" name="week" value="<%=date.get(Calendar.WEEK_OF_YEAR) %>" />
								<input type="hidden" name="year" value="<%=date.get(Calendar.YEAR) %>" />
								<% } %>
								<input type="submit" value="D�connexion" class="link" />
							</form>
						</td>
						<% } %>
						<form method="post" action="">
						<td align="right">
							Visualiser l'emploi du temps de la formation :&nbsp; 
							<select name="id_formation" onChange="javascript:this.form.submit();">
								<option value=""></option>
								<% for (Iterator i = formations.iterator(); i.hasNext(); ) {
									Formation formation = (Formation) i.next(); %>
								<option value="<%=formation.getIdFormation() %>" <% if (formation.getIdFormation().toString().equals(idFormation)) { %>selected="selected"<% } %>><%=formation.getHeading() %></option>
								<% } %>
							</select>
							<input type="hidden" name="login" value="<%=login %>" />
							<input type="hidden" name="password" value="<%=password %>" />
						</td>
					</tr>
				</table>
			</form>
		</fieldset>

<% if (idFormation != null && !"".equals(idFormation)) { %>
<%
TimetableFilter filter = new TimetableFilter();

if ("0".equals(idFormation))
	filter.setTeacher(teacher);
else
	filter.setFormation(new Formation(new Long(idFormation)));

Calendar begin = Calendar.getInstance();
begin.setTime(date.getTime());
begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
begin.set(Calendar.HOUR_OF_DAY, 0);
begin.set(Calendar.MINUTE, 0);
begin.set(Calendar.SECOND, 0);
filter.setBeginPeriod(begin);

Calendar end = Calendar.getInstance();
end.setTime(date.getTime());
end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
end.set(Calendar.HOUR_OF_DAY, 23);
end.set(Calendar.MINUTE, 59);
end.set(Calendar.SECOND, 59);
filter.setEndPeriod(end);

Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
ArrangeCourses arranged = new ArrangeCourses(timetable.getCourses());
%>
		<br/><br/>
		
		<table align="center">
			<tr>
				<td>
					<form method="post" action="">
						<%
						Calendar tmp = Calendar.getInstance();
						tmp.setTime(date.getTime());
						tmp.set(Calendar.WEEK_OF_YEAR, tmp.get(Calendar.WEEK_OF_YEAR)-1);
						%>
						<input type="hidden" name="login" value="<%=login %>" />
						<input type="hidden" name="password" value="<%=password %>" />
						<input type="hidden" name="week" value="<%=tmp.get(Calendar.WEEK_OF_YEAR) %>" />
						<input type="hidden" name="year" value="<%=tmp.get(Calendar.YEAR) %>" />
						<input type="hidden" name="id_formation" value="<%=idFormation %>" />
						<input type="submit" value="<" />
					</form>
				</td>
				<td>
					<strong>Semaine du <%=FORMAT_DATE.format(date.getTime()) %></strong>
				</td>
				<td>
					<form method="post" action="">
						<%
						tmp = Calendar.getInstance();
						tmp.setTime(date.getTime());
						tmp.set(Calendar.WEEK_OF_YEAR, tmp.get(Calendar.WEEK_OF_YEAR)+1);
						%>
						<input type="hidden" name="login" value="<%=login %>" />
						<input type="hidden" name="password" value="<%=password %>" />
						<input type="hidden" name="week" value="<%=tmp.get(Calendar.WEEK_OF_YEAR) %>" />
						<input type="hidden" name="year" value="<%=tmp.get(Calendar.YEAR) %>" />
						<input type="hidden" name="id_formation" value="<%=idFormation %>" />
						<input type="submit" value=">" />
					</form>
				</td>
			</tr>
		</table>

		<br/>

		<table width="100%" border="1">
			<%
			int nbDay = ArrangeCourses.ALL_DAYS.length;
			if (arranged.getCourses(ArrangeCourses.SUNDAY).size() == 0) {
				--nbDay;
				if (arranged.getCourses(ArrangeCourses.SATURDAY).size() == 0)
					--nbDay;
			}
			%>
			<tr>
				<td rowspan="2"></td>
				<% for (int i = arranged.getHourEarliest(); i < arranged.getHourLatest(); ++i) { %>
				<td align="center" colspan="4"><strong><%=i %>H</strong></td>
				<% } %>
			</tr>
			<tr>
				<% for (int i = arranged.getHourEarliest(); i < arranged.getHourLatest(); ++i) { %>
				<td>00</td>
				<td>15</td>
				<td>30</td>
				<td>45</td>
				<% } %>
			</tr>
			<% for (int i = 0; i < nbDay; ++i) { %>
			<tr height="75">
				<td><strong><%=ArrangeCourses.ALL_DAYS[i] %></strong></td>
				<%
					int defaultHour = arranged.getHourEarliest();
					//int defaultMinute = 0;
					for (Iterator it = arranged.getCourses(ArrangeCourses.ALL_DAYS[i]).iterator(); it.hasNext(); ) {
						Course course = (Course) it.next();
						int beginHour = course.getBeginDate().get(Calendar.HOUR_OF_DAY);
						int colspan = (beginHour - defaultHour) * 4;
						int minuteHour = course.getBeginDate().get(Calendar.MINUTE);
						colspan += minuteHour / 15;
						if (colspan > 0) {
				%>
				<td colspan="<%=colspan %>"></td>
				<%
						}
						int endHour = course.getEndDate().get(Calendar.HOUR_OF_DAY);
						colspan = (endHour - beginHour) * 4;
						colspan -= minuteHour / 15;
						minuteHour = course.getEndDate().get(Calendar.MINUTE);
						colspan += minuteHour / 15;
						if (colspan > 0) {
				%>
				<td align="center" colspan="<%=colspan %>" style="background-color:rgb(<%=course.getSubject().getColor().getRed() %>,<%=course.getSubject().getColor().getGreen() %>,<%=course.getSubject().getColor().getBlue() %>)">
					<strong><%=course.getSubject().getHeading() %></strong>
					<br/>
					<%
						for (Iterator it2 = course.getTeachers().iterator(); it2.hasNext(); ) {
							User t = (User) it2.next();
							out.print(t.getFirstName() + " " + t.getName());
							if (it2.hasNext())
								out.print(" - ");
						}
					%>
					<br/>
					<%
						for (Iterator it2 = course.getRooms().iterator(); it2.hasNext(); ) {
							Room r = (Room) it2.next();
							out.print(r.getName());
							if (it2.hasNext())
								out.print(" - ");
						}
					%>
				</td>
				<%
						}
						defaultHour = endHour;
					}
				%>
			</tr>
			<% } %>
		</table>
<% } %>
	</body>
</html>
