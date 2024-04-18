package example.controller;

import example.dao.AstonUsersDAO;
import example.model.UsersAston;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addUser", value = "/addUser")
public class AddUser extends HttpServlet {

    private AstonUsersDAO usersDAO = new AstonUsersDAO();


    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


        PrintWriter out = response.getWriter();


        String username = request.getParameter("username");
        String surname = request.getParameter("surname");


        UsersAston user = new UsersAston();


        user.setUsername(username);
        user.setSurname(surname);

        usersDAO.addUser(user);

        out.close();
    }
}
