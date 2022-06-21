package com.role;

import com.auth.Token;
import com.data.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class RoleServlet extends HttpServlet {

    private  RoleDao roleDao = new RoleDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String action = request.getQueryString();
        if(action.equals("action=createrole")){
            this.createRoleRequestHandler(request, response);
        }else if(action.equals("action=deleterole")){
            this.deleteRoleRequestHandler(request, response);
        }else if(action.equals("action=assignrole")){
            this.assignRoleRequestHandler(request, response);
        }else if(action.equals("action=checkrole")){
            this.checkRole(request, response);

        }
    }

    private void checkRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Token token = Database.getTokenByTokenString(request.getParameter("authtoken"));
        if(token != null){
            if(!Database.existedToken(request.getParameter("authtoken"))){
                request.setAttribute("check_role_err_message", "false-invalid token");
                request.getRequestDispatcher("/Test.jsp").forward(request, response);
            }else{
                String role = request.getParameter("role");
                String username = token.getUsername();
                RoleDao roleDao = new RoleDao();
                try{
                    if(roleDao.queryUserRoles(username).contains(role)){
                        request.setAttribute("check_role_success_message", "identified");
                        request.getRequestDispatcher("/Test.jsp").forward(request, response);
                    }else{
                        request.setAttribute("check_role_err_message", "false");
                        request.getRequestDispatcher("/Test.jsp").forward(request, response);
                    }

                }catch (Exception e){
                    request.setAttribute("check_role_err_message", "false");
                    request.getRequestDispatcher("/Test.jsp").forward(request, response);
                }
            }

        }else{
            request.setAttribute("check_role_err_message", "false-no such token");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }
    }

    private void assignRoleRequestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String administrator = request.getParameter("administrator");
        String assigneduser = request.getParameter("assigneduser");
        String assignedrole = request.getParameter("assignedrole");

        if(assigneduser.equals("") || assignedrole.equals("") || administrator.equals("")){
            request.setAttribute("assign_role_err_message", "empty field");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }

        if(!roleDao.queryIfExistedRole(assignedrole)){
            request.setAttribute("assign_role_err_message", "no such role");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }

        if(!this.hasAssignRolePermission(administrator)){
            request.setAttribute("assign_role_err_message", "no permission to assign role");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }else{


            if(this.checkIfExistedUser(assigneduser) && this.checkIfExistedRole(assignedrole)){
                if(this.assignRole(assigneduser, assignedrole)){
                    request.setAttribute("assign_role_success_message", "assign role successfully");
                    request.getRequestDispatcher("/Test.jsp").forward(request, response);
                }else{
                    request.setAttribute("assign_role_err_message", "assign role failure");
                    request.getRequestDispatcher("/Test.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("assign_role_err_message", "assign role failure");
                request.getRequestDispatcher("/Test.jsp").forward(request, response);
            }
        }
    }

    private void deleteRoleRequestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String administrator = request.getParameter("administrator");
        String deleterole =  request.getParameter("deleterole");
        if(deleterole.equals("")){
            request.setAttribute("delete_role_err_message", "delete role empty");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }

        if(administrator.equals("")){
            request.setAttribute("delete_role_err_message", "administrator empty");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }

        if(!this.checkIfExistedRole(deleterole)){
            request.setAttribute("delete_role_err_message", "no such role");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }
        if(!this.hasDeleteRolePermission(administrator)){
            request.setAttribute("delete_role_err_message", "no permission to delete");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
            return;
        }



        if(roleDao.deleteRole(deleterole)){
            request.setAttribute("delete_role_success_message", "delete role successfully");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);

        }else{
            request.setAttribute("delete_role_err_message", "delete role error");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }
    }

    private void createRoleRequestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("createrole").equals("")){
            request.setAttribute("create_role_err_message", "empty role");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }
        if(!this.hasCreateRolePermission(request.getParameter("username"))){
            request.setAttribute("create_role_err_message", "Create role forbidden  -- no permission");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }else{
            if(this.checkIfExistedRole(request.getParameter("createrole")) ){
                request.setAttribute("create_role_err_message", "Already existed");
                request.getRequestDispatcher("/Test.jsp").forward(request, response);
            }else{
                if(this.createRole(request.getParameter("createrole"))){
                    request.setAttribute("create_role_success_message", "create role sucessfully");
                    request.getRequestDispatcher("/Test.jsp").forward(request, response);
                }else{
                    request.setAttribute("create_role_err_message", "create role failure");
                    request.getRequestDispatcher("/Test.jsp").forward(request, response);
                }
            }
        }
    }

//    private boolean deleteRole(String deleterole){
//        if(!Database.existedRole(deleterole)){
//
//        }
//
//        if(roleDao.deleteRole(deleterole)){
//
//        }
//    }

    private boolean assignRole(String assignedUser, String assignedRole){
        if(roleDao.assignRoleToUser(assignedUser, assignedRole)){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkIfExistedUser(String userName){
        RoleDao roleDao = new RoleDao();
        if(roleDao.existedUser(userName)){
            return true;
        }else{
            return false;
        }
    }

    private boolean hasAssignRolePermission(String userName) {
        String role = null;
        RoleDao roleDao = new RoleDao();
        try{
            ArrayList<String> roles = roleDao.queryUserRoles(userName);
            if(roles == null){
                return false;
            }else{
                Iterator<String> itr = roles.iterator();
                while(itr.hasNext()){
                    role = itr.next();
                    if(roleDao.queryAssignRolePermission(role)){
                        return true;
                    }
                }
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(roleDao.queryRoles() != null){
            request.setAttribute("query_role_success_message", "All roles:" + roleDao.queryRoles().toString());
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }else{
            request.setAttribute("query_role_err_message", "Oopse... something went wrong...");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }
    }

    private boolean createRole(String createRole){
        RoleDao roleDao = new RoleDao();
        if(createRole == null){
            return false;
        }
        if(roleDao.addRole(createRole)){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkIfExistedRole(String role){
        RoleDao roleDao = new RoleDao();
        if(roleDao.queryIfExistedRole(role)){
            return true;
        }else{
            return false;
        }
    }

    private boolean hasDeleteRolePermission(String userName){
        String role = null;
        try{
            ArrayList<String> roles = roleDao.queryUserRoles(userName);
            if(roles == null){
                return false;
            }else{
                Iterator<String> itr = roles.iterator();
                while(itr.hasNext()){
                    role = itr.next();
                    if(roleDao.queryDeleteRolePermission(role)){
                        return true;
                    }
                }
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    private boolean hasCreateRolePermission(String userName){
        String role = null;
        RoleDao roleDao = new RoleDao();
        try{
            ArrayList<String> roles = roleDao.queryUserRoles(userName);
            if(roles == null){
                return false;
            }else{
                Iterator<String> itr = roles.iterator();
                while(itr.hasNext()){
                    role = itr.next();
                    if(roleDao.queryCreateRolePermission(role)){
                        return true;
                    }
                }
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
    private boolean checkIfRequestIllegal(HttpServletRequest request){
        if(this.checkIfParameterNull(request, "username")){
            return true;
        }
        //Other parameter check code
        // ...

        return false;
    }

    private boolean checkIfParameterNull(HttpServletRequest request, String parameter){
        if(request.getParameter(parameter) == null){
            return true;
        }

        //Other illegal condition code

        return false;

    }



}
