<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: x--%>
<%--  Date: 2022/5/26--%>
<%--  Time: 8:36--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Role</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div style="display:inline-block;">--%>
<%--    <span>Please create new role here:</span>--%>
<%--    <span>(Only the user with administrator role can create new role)</span>--%>
<%--<form id="createrole" name="form_createrole" action="<%=request.getContextPath()%>/RoleServlet?action=createrole" method="post">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td>new role:</td>--%>
<%--            <td><input type="text" name="createrole" /></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>creator name:</td>--%>
<%--            <td><input type="text" name="username" value="admin"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>--%>
<%--            <td><span style="color:red"><%=(request.getAttribute("successfulMessage") == null) ? "" : request.getAttribute("successfulMessage")%></span></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><input type="submit" value="create"></input><input type="reset" value="Reset"></input></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>
<%--</div>--%>

<%--<div>--%>
<%--    <span>Please click button to see all existed roles</span>--%>
<%--<form id="showAllRoles" name="form_showallroles" action="<%=request.getContextPath()%>/RoleServlet?" method="get">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td><span style="color:red"><%=(request.getAttribute("role_errMessage") == null) ? "" : request.getAttribute("role_errMessage")%></span></td>--%>
<%--            <td><span style="color:red"><%=(request.getAttribute("role_successfulMessage") == null) ? "" : request.getAttribute("role_successfulMessage")%></span></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><input type="submit" value="show all roles"></input></input></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>
<%--</div>--%>
<%--<form id="assignrole" name="form_createrole" action="<%=request.getContextPath()%>/RoleServlet?action=assignrole" method="post">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td>Assignedrole:</td>--%>
<%--            <td><input type="text" name="assignedrole" /></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Assigneduser:</td>--%>
<%--            <td><input type="text" name="assigneduser" /></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Username</td>--%>
<%--            <td><input type="text" name="username" /></td>--%>
<%--        </tr>--%>
<%--&lt;%&ndash;        <tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <td>Password</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <td><input type="text" name="password" /></td>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </tr>&ndash;%&gt;--%>
<%--        <tr>--%>
<%--            <td><span style="color:red"><%=(request.getAttribute("assignrole_errMessage") == null) ? "" : request.getAttribute("assignrole_errMessage")%></span></td>--%>
<%--        <tr>--%>
<%--            <td><span style="color:red"><%=(request.getAttribute("assignrole_successfulMessage") == null) ? "" : request.getAttribute("assignrole_successfulMessage")%></span></td>--%>
<%--        </tr>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td><input type="submit" value="assign"></input><input type="reset" value="Reset"></input></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>
