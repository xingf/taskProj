<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Test Page</title>
    <script>
        function validate()
        {
            var username = document.form.username.value;
            var password = document.form.password.value;

           if (username==null || username=="")
            {
                alert("Username can't be blank");
                return false;
            }
            else if(password.length<6)
            {
                alert("Password must be at least 6 characters long.");
                return false;
            }

        }
    </script>
</head>
<body>

<table border="1">
    <tr>
        <td>
            <div>Grid 1: Please test "create user" here: </div>
            <form name="form_create_user" action="Users?action=createuser" method="post"  onsubmit="return validate()" style="display:inline-block">
                <table>
                    <tr><td>Username</td><td><input type="text" name="username"/></td></tr>
                    <tr><td>Password</td><td><input type="password" name="password"/></td></tr>
                    <tr><td><input type="submit" value="Register"></td></tr>
                </table>
            </form>
            <div style="color:cadetblue"><%=(request.getAttribute("register_err_message") == null) ? ""
                : request.getAttribute("register_err_message")%></div>
            <div style="color:cadetblue"><%=(request.getAttribute("register_success_message") == null) ? ""
                : request.getAttribute("register_success_message")%></div>
        </td>

        <td>
            <div>Grid 2: Please click to get all existed all users:</div>
            <form name="form_query_all_users" action="Users" method="get"  style="display:inline-block">
                <input type="submit" value="Show All Users">
            </form>
            <div style="color:cadetblue"><%=(request.getAttribute("query_users_success_message") == null) ? ""
                    : request.getAttribute("query_users_success_message")%></div>
            <div style="color:cadetblue"><%=(request.getAttribute("query_users_err_message") == null) ? ""
                    : request.getAttribute("query_users_err_message")%></div>

        </td>

        <td>
            <div>Grid 3: Please test "Delete user" here :</div>
            <form name="form_delete_user" action="<%=request.getContextPath()%>/Users?action=deleteuser" method="post">
                <table >
                    <tr><td>Delete Username:&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="deleteusername" /></td></tr>
                    <tr><td>Administrator name:<input type="text" name="administratorname" value="admin"/></td></tr>
                    <tr><td>Note: Only users with administrator role can delete user</td></tr>
                    <tr>
                        <td><input type="submit" value="delete"></input></td>
                    </tr>
                </table>
                <div>
                    <span style="color:red"><%=(request.getAttribute("delete_user_err_message") == null) ? "" : request.getAttribute("delete_user_err_message")%></span>
                    <span style="color:red"><%=(request.getAttribute("delete_user_success_message") == null) ? "" : request.getAttribute("delete_user_success_message")%></span>

                </div>
            </form>
        </td>
    </tr>

    <tr>
        <td>
            <div>Grid 4: Please test "all roles":</div>
            <form name="query_all_roles" action="<%=request.getContextPath()%>/Roles?" method="get">
                <table>
                    <tr>
                        <td><input type="submit" value="show all roles"></input></td>
                    </tr>
                </table>
            </form>
            <div style="color:cadetblue"><%=(request.getAttribute("query_role_err_message") == null) ? "" : request.getAttribute("query_role_err_message")%></div>
            <div style="color:cadetblue"><%=(request.getAttribute("query_role_success_message") == null) ? "" : request.getAttribute("query_role_success_message")%></div>

        </td>
        <td>
            <div>Grid 5: Please test "create role" here(Only user with administrator role can create):</div>
            <form  name="form_create_role" action="<%=request.getContextPath()%>/Roles?action=createrole" method="post">
                <table>
                    <tr><td>new role:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="createrole" /></td></tr>
                    <tr><td>administrator name:<input type="text" name="username" value="admin"/></td></tr>
                    <tr><td><input type="submit" value="create"></td></tr>
                    <tr>
                        <td>
                            <div style="color:cadetblue"><%=(request.getAttribute("create_role_err_message") == null) ? "" : request.getAttribute("create_role_err_message")%></div>
                            <div style="color:cadetblue"><%=(request.getAttribute("create_role_success_message") == null) ? "" : request.getAttribute("create_role_success_message")%></div>
                        </td>
                    </tr>
                </table>
            </form>
        </td>

        <td>
            <div>Grid 6: Please test "delete role" here(Only user with administrator role can create):</div>
            <form  name="form_delete_role" action="<%=request.getContextPath()%>/Roles?action=deleterole" method="post">
                <table>
                    <tr><td>delete role:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="deleterole" /></td></tr>
                    <tr><td>administrator name:<input type="text" name="administrator" value="admin"/></td></tr>
                    <tr><td><input type="submit" value="delete"></td></tr>
                    <tr>
                        <td>
                            <div style="color:cadetblue"><%=(request.getAttribute("delete_role_err_message") == null) ? "" : request.getAttribute("delete_role_err_message")%></div>
                            <div style="color:cadetblue"><%=(request.getAttribute("delete_role_success_message") == null) ? "" : request.getAttribute("delete_role_success_message")%></div>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <div>Grid 7:Please click to get the map of user and roles:</div>
            <form  name="form_user_roles" action="<%=request.getContextPath()%>/Users?action=userandroles" method="post">
                <input type="submit" value="show user and roles:">
            </form>
            <div style="color:cadetblue"><%=(request.getAttribute("user_role_err_message") == null) ? "" : request.getAttribute("user_role_err_message")%></div>
            <div style="color:cadetblue"><%=(request.getAttribute("user_role_success_message") == null) ? "" : request.getAttribute("user_role_success_message")%></div>
        </td>

        <td>
            <div>Grid 8: Please test "Add role to user" here(Only administrator role has permission to assign roles):</div>
            <form name="form_assign_role" action="<%=request.getContextPath()%>/Roles?action=assignrole" method="post">
                <table>
                    <tr><td>Assign   role:&nbsp&nbsp&nbsp&nbsp<input type="text" name="assignedrole" /></td></tr>
                    <tr><td>Assign   user:&nbsp&nbsp&nbsp&nbsp<input type="text" name="assigneduser" /></td></tr>
                    <tr><td>Administrator:<input type="text" name="administrator" /></td></tr>
                    <tr><td><input type="submit" value="assign"></td></tr>
                </table>
            </form>

            <div style="color:red"><%=(request.getAttribute("assign_role_err_message") == null) ? "" : request.getAttribute("assign_role_err_message")%></div>
            <div style="color:red"><%=(request.getAttribute("assign_role_success_message") == null) ? "" : request.getAttribute("assign_role_success_message")%></div>
        </td>


        <td>
            <div>Grid 9: please test "Authenticate" here:</div>
            <form name="form_authenticate_token" action="<%=request.getContextPath()%>/Users?action=authenticatetoken" method="post">
            <table>
                <tr><td>username:<input type="text" name="username" /></td></tr>
                <tr><td>password:<input type="text" name="password" /></td></tr>
                <tr><td><input type="submit" value="authenticate"></td></tr>
                </table>
            </form>
            <div style="color:red"><%=(request.getAttribute("authenticate_err_message") == null) ? "" : request.getAttribute("authenticate_err_message")%></div>
            <div style="color:red"><%=(request.getAttribute("authenticate_success_message") == null) ? "" : request.getAttribute("authenticate_success_message")%></div>
        </td>
    </tr>
    <tr>
        <td>
            <div>Grid 10: Please test "invalidate" here:</div>
            <form name="form_authenticate_token" action="<%=request.getContextPath()%>/Users?action=invalidtoken" method="post">
                <table>
                    <tr><td>invalid token:<input type="text" name="invalidtoken" /></td></tr>
                    <tr><td><input type="submit" value="invalid"></td></tr>
                </table>
            </form>
            <div style="color:red"><%=(request.getAttribute("invalid_token_err_message") == null) ? "" : request.getAttribute("invalid_token_err_message")%></div>
            <div style="color:red"><%=(request.getAttribute("invalid_token_success_message") == null) ? "" : request.getAttribute("invalid_token_success_message")%></div>
        </td>


        <td>
            <div>Grid 10: Please test "Check role" here:</div>
            <form name="form_check_role_token" action="<%=request.getContextPath()%>/Roles?action=checkrole" method="post">
                <table>
                    <tr><td>auth token:<input type="text" name="authtoken" /></td></tr>
                    <tr><td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsprole:<input type="text" name="role" /></td></tr>
                    <tr><td><input type="submit" value="check"></td></tr>
                </table>
            </form>
            <div style="color:red"><%=(request.getAttribute("check_role_err_message") == null) ? "" : request.getAttribute("check_role_err_message")%></div>
            <div style="color:red"><%=(request.getAttribute("check_role_success_message") == null) ? "" : request.getAttribute("check_role_success_message")%></div>
        </td>


    </tr>


</table>



</body>
</html>