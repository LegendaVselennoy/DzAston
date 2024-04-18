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
import java.util.List;

@WebServlet(name = "viewUser", value = "/viewUser")
public class ViewUser extends HttpServlet {

    private AstonUsersDAO usersDAO = new AstonUsersDAO();

    void processRequest(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();


        String pageId = request.getParameter("page");
        int total = 3;
        int pagesId = Integer.parseInt(pageId);
        if (pagesId == 1) {
        } else {
            pagesId = pagesId - 1;
            pagesId = pagesId * total + 1;
        }

        // initializing list of users
        List<UsersAston> list = null;
        out.println(
                "<a href='/appuser_war_exploded/'>Add user</a>");

        out.print("<h1> User Table: </h1>");
        out.print(
                "<table border='1' cellpadding='4' width='80%'>");
        out.print("<tr><th>Id</th><th>username</th></tr>");

        list = usersDAO.getAllUsers();



        if (list != null) {
            for (UsersAston user : list) {
                out.print("<tr><td>" + user.getId()
                        + "</td><td>" + user.getUsername()
                        + "</td></tr>");
            }

            out.print("</table>");
            out.print("<a href='viewUser?page=1'>1</a> ");
            out.print("<a href='viewUser?page=2'>2</a> ");
            out.print("<a href='viewUser?page=3'>3</a> ");
        }
    }
}
