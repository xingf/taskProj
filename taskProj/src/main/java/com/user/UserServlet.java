package com.user;

import com.auth.Authenticator;
import com.auth.Token;
import com.data.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

public class UserServlet extends HttpServlet {

    UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryString = request.getQueryString();
        if(queryString.equals("action=deleteuser")){
            this.deleteUserRequestHandler(request, response);

        }else if(queryString.equals("action=createuser")){
            this.createUserRequestHandler(request, response);
        }else if(queryString.equals("action=userandroles")){
            request.setAttribute("user_role_success_message", this.queryUserRoleMap().toString());
            request.getRequestDispatcher("Test.jsp").forward(request, response);

        }else if(queryString.equals("action=authenticatetoken")){
            this.authenticate(request, response);
        }else if(queryString.equals("action=invalidtoken")){
            String tokenValue = request.getParameter("invalidtoken");
            if(Database.invalidToken(tokenValue)){
                request.setAttribute("invalid_success_message", "Invalid successfully");
                request.getRequestDispatcher("Test.jsp").forward(request, response);
            }
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Authenticator.authenticate(request.getParameter("username"), request.getParameter("password")) ){
            Token token = userDao.getToken(request.getParameter("username"));
            if(token != null){
                request.setAttribute("authenticate_success_message", "token: " + token.tokenValue + ", valid until:" + new Time(token.getExpiredTime().getTime()).toString());
                request.getRequestDispatcher("Test.jsp").forward(request, response);
            }else{
                request.setAttribute("authenticate_err_message", "Oops..., failure");
                request.getRequestDispatcher("Test.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("authenticate_err_message", "Oops..., failure");
            request.getRequestDispatcher("Test.jsp").forward(request, response);
        }
    }

    private void deleteUserRequestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String deleteusername = request.getParameter("deleteusername");
        final String administrator = request.getParameter("administratorname");
        if(this.hasDeletePermission(administrator)){
            if(!this.checkIfExistedUserName(deleteusername)){
                request.setAttribute("delete_user_err_message", "Oops... no such user");
                request.getRequestDispatcher("/Test.jsp").forward(request, response);
            }
            if(this.deleteUser(deleteusername)){
                request.setAttribute("delete_user_success_message", "delete user successfully...");
                request.getRequestDispatcher("/Test.jsp").forward(request, response);
            }else{
                request.setAttribute("delete_user_err_message", "Oops... delete user wrong...");
                request.getRequestDispatcher("/Test.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("delete_user_err_message", "Oops... delete user wrong...");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }
    }

    private void createUserRequestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(this.checkIfExistedUserName(username)){
            request.setAttribute("register_err_message", "Oopse... user name already existed...");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }

        if(userDao.insertUserIntoUserTable(username, password)){
            request.setAttribute("register_success_message", "Register successfully ...");
            request.getRequestDispatcher("/Test.jsp").forward(request, response);
        }else{
            request.setAttribute("register_err_message", "Oops... Something went wrong ...");
            request.getRequestDispatcher("Test.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getAllUsernameRequestHandler(request, response);
    }

    private void getAllUsernameRequestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> users = userDao.queryAllUsers();
        request.setAttribute("query_users_success_message", "All existed usernames:" + users.toString());
        request.getRequestDispatcher("/Test.jsp").forward(request, response);
    }


    private boolean createUser(String username, String password){
        if(userDao.insertUserIntoUserTable(username, password)){
            return true;
        }else{
            return false;
        }
    }


    private boolean checkIfExistedUserName(String userName){
        if(userDao.existedUserName(userName)){
            return true;
        }else{
            return false;
        }
    }

    private boolean hasDeletePermission(String userName){
        String role = null;
        try{
            ArrayList<String> roles = this.userDao.queryUserRoles(userName);
            if(roles == null){
                return false;
            }else{
                Iterator<String> itr = roles.iterator();
                while(itr.hasNext()){
                    role = itr.next();
                    if(this.userDao.hasDeletePermission(role)){
                        return true;
                    }
                }
                return false;
            }
        }catch(Exception e){
            return false;
        }

    }

    private boolean deleteUser(String deleteUserName){

        if(userDao.deleteUser(deleteUserName)){
            return true;
        }else{
            return false;
        }
    }

    private ArrayList<String> queryUserRoleMap(){
        return userDao.queryUserRoleMap();
    }


}
