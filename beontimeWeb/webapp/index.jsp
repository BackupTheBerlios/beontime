<%@ page contentType="text/html; charset=iso-8859-1" language="java"
import="fr.umlv.smoreau.beontime.dao.*,
		fr.umlv.smoreau.beontime.filter.TimetableFilter,
		fr.umlv.smoreau.beontime.model.Formation,
		fr.umlv.smoreau.beontime.model.user.User,
		fr.umlv.smoreau.beontime.model.element.Room,
		fr.umlv.smoreau.beontime.model.timetable.*,
		java.util.*,
		java.awt.Color,
		java.text.SimpleDateFormat" %>

<%
SimpleDateFormat FORMAT_DAY  = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat FORMAT_HOUR = new SimpleDateFormat("HH'h'mm");

Calendar date = Calendar.getInstance();
String week = request.getParameter("week");
if (week != null)
	date.set(Calendar.WEEK_OF_YEAR, (new Integer(week)).intValue());
String year = request.getParameter("year");
if (year != null)
	date.set(Calendar.YEAR, (new Integer(year)).intValue());
date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

String idFormation = request.getParameter("id_formation");
Collection formations = DaoManager.getFormationDao().getFormations();

User teacher = null;
if (request.getParameter("disconnect") != null) {
	session.removeAttribute("teacher");
	if ("0".equals(idFormation))
		idFormation = null;
} else
	teacher = (User) session.getAttribute("teacher");

if (teacher == null) {
	String login = request.getParameter("login");
	String password = request.getParameter("password");

	if (login != null && !"".equals(login) && password != null && !"".equals(password)) {
		teacher = DaoManager.getUserDao().testLoginPwd(login, password);
		if (teacher == null || !UserDao.TYPE_TEACHER.equals(teacher.getUserType()))
			teacher = null;
		else
			session.putValue("teacher", teacher);
	}
}

if (teacher == null && (date.get(Calendar.WEEK_OF_YEAR) - Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)) > 2)
	idFormation = null;

Timetable timetable = null;
if (idFormation != null && !"".equals(idFormation)) {
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

	timetable = DaoManager.getTimetableDao().getTimetable(filter);
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
			table.edt {
				border-bottom: 1px black solid;
				border-right: 1px black solid;
			}
			td.titre {
				border: 1px black solid;
				background-color: lightgrey;
				text-align: center;
			}
			td.cours {
				border-style: outset;
			}
		</style>
	</head>

	<body>
		<table width="100%">
			<tr>
				<td align="center">
					<% if (timetable != null) { %>
					<img src="miniLogoBoT.png" />
					<% } else { %>
					<img src="logoBoT.png" />
					<% } %>
				</td>
				<td align="center">
					<font color="#0033FF" size="+7">
						<strong>
							<% if (timetable == null) { %>Bienvenue sur <% } %>BeOnTime
						</strong>
					</font>
				</td>
			</tr>
		</table>

		<br/>
		
		<fieldset>
			<legend><strong>Identification</strong></legend>
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
							Vous êtes <strong><%=teacher.getFirstName() %> <%=teacher.getName() %></strong>
						</td>
						<td align="center">
							<form method="post" action="">
								<input type="hidden" name="id_formation" value="0" />
								<input type="submit" value="Visualiser votre emploi du temps" class="link" <% if ("0".equals(idFormation)) { %>disabled="disabled" style="color: #808080;"<% } %> />
							</form>
							<form method="post" action="">
								<input type="hidden" name="disconnect" />
								<% if (!"0".equals(idFormation) && idFormation != null) { %>
								<input type="hidden" name="id_formation" value="<%=idFormation %>" />
								<input type="hidden" name="week" value="<%=date.get(Calendar.WEEK_OF_YEAR) %>" />
								<input type="hidden" name="year" value="<%=date.get(Calendar.YEAR) %>" />
								<% } %>
								<input type="submit" value="Déconnexion" class="link" />
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
						</td>
					</tr>
				</table>
			</form>
		</fieldset>

<% if (timetable != null) { %>
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
						<input type="hidden" name="week" value="<%=tmp.get(Calendar.WEEK_OF_YEAR) %>" />
						<input type="hidden" name="year" value="<%=tmp.get(Calendar.YEAR) %>" />
						<input type="hidden" name="id_formation" value="<%=idFormation %>" />
						<input type="submit" value="<" />
					</form>
				</td>
				<td>
					<strong>Semaine du <%=FORMAT_DAY.format(date.getTime()) %></strong>
				</td>
				<td>
					<form method="post" action="">
						<%
						tmp = Calendar.getInstance();
						tmp.setTime(date.getTime());
						tmp.set(Calendar.WEEK_OF_YEAR, tmp.get(Calendar.WEEK_OF_YEAR)+1);
						boolean disable = false;
						if (teacher == null && (date.get(Calendar.WEEK_OF_YEAR) - Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)) >= 2)
							disable = true;
						%>
						<input type="hidden" name="week" value="<%=tmp.get(Calendar.WEEK_OF_YEAR) %>" />
						<input type="hidden" name="year" value="<%=tmp.get(Calendar.YEAR) %>" />
						<input type="hidden" name="id_formation" value="<%=idFormation %>" />
						<input type="submit" value=">" <% if (disable) { %>disabled="disabled"<% } %> />
					</form>
				</td>
			</tr>
		</table>

		<br/>

		<table width="100%" border="0" cellspacing="0" class="edt">
			<%
			int nbDay = ArrangeCourses.ALL_DAYS.length;
			if (timetable.getCoursesArranged(ArrangeCourses.SUNDAY).size() == 0) {
				--nbDay;
				if (timetable.getCoursesArranged(ArrangeCourses.SATURDAY).size() == 0)
					--nbDay;
			}
			%>
			<tr>
				<td rowspan="2"></td>
				<% for (int i = timetable.getHourEarliest(); i < timetable.getHourLatest(); ++i) { %>
				<td colspan="4" class="titre"><strong><%=i %>H</strong></td>
				<% } %>
			</tr>
			<tr>
				<% for (int i = timetable.getHourEarliest(); i < timetable.getHourLatest(); ++i) { %>
				<td class="titre">00</td>
				<td class="titre">15</td>
				<td class="titre">30</td>
				<td class="titre">45</td>
				<% } %>
			</tr>
			<% for (int i = 0; i < nbDay; ++i) { %>
			<tr height="75">
				<td class="titre"><strong><%=ArrangeCourses.ALL_DAYS[i] %></strong></td>
				<%
					int defaultHour = timetable.getHourEarliest();
					for (Iterator it = timetable.getCoursesArranged(ArrangeCourses.ALL_DAYS[i]).iterator(); it.hasNext(); ) {
						Object object = it.next();
						
						Calendar beginDate;
						Calendar endDate;
						Color color;
						ArrayList courses = new ArrayList();
						
						if (object instanceof Course) {
							Course c = (Course) object;
							beginDate = c.getBeginDate();
							endDate = c.getEndDate();
							color = c.getSubject().getColor();
							courses.add(c);
						} else if (object instanceof Courses) {
							Courses c = (Courses) object;
							beginDate = c.getBeginDate();
							endDate = c.getEndDate();
							color = c.getColor();
							courses.addAll(c.getCourses());
						} else {
							continue;
						}

						int beginHour = beginDate.get(Calendar.HOUR_OF_DAY);
						int beginMinute = beginDate.get(Calendar.MINUTE);
						int endHour = endDate.get(Calendar.HOUR_OF_DAY);
						int endMinute = endDate.get(Calendar.MINUTE);
						int colspanBefore = ((beginHour - defaultHour) * 4) + (beginMinute / 15);
						int colspanAfter = ((endHour - beginHour) * 4) - (beginMinute / 15) + (endMinute / 15);

						String rgb = color.getRed() + "," + color.getGreen() + "," + color.getBlue();
				%>
				<% if (colspanBefore > 0) { %><td colspan="<%=colspanBefore %>"></td><% } %>
				<% if (colspanAfter > 0) { %>
				<td align="center" class="cours" colspan="<%=colspanAfter %>" style="background-color:rgb(<%=rgb %>)">
					<% for (Iterator j = courses.iterator(); j.hasNext(); ) {
						Course course = (Course) j.next(); %>
					<strong><%=course.getSubject().getHeading() %></strong>
					<%
						if (TimetableDao.TYPE_TD.equals(course.getIdCourseType().getNameCourseType()))
							out.println("(TD)");
						else if (TimetableDao.TYPE_TP.equals(course.getIdCourseType().getNameCourseType()))
							out.println("(TP)");
					%>
					<br/>
					<%=FORMAT_HOUR.format(course.getBeginDate().getTime()) + " / " + FORMAT_HOUR.format(course.getEndDate().getTime()) %>
					<br/>
					<%
						for (Iterator it2 = course.getTeachers().iterator(); it2.hasNext(); ) {
							User t = (User) it2.next();
							if (t != null) {
								out.print(t.getFirstName() + " " + t.getName());
								if (it2.hasNext())
									out.print(" - ");
							}
						}
					%>
					<% if (courses.size() > 1) { %> | <% } else { %><br/><% } %>
					<%
						for (Iterator it2 = course.getRooms().iterator(); it2.hasNext(); ) {
							Room r = (Room) it2.next();
							out.print(r.getName());
							if (it2.hasNext())
								out.print(" - ");
						}
					%>
					<% if (j.hasNext()) { %>
					<br/>
					<% } } %>
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
