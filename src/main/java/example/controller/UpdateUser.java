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


@WebServlet(name = "updateUser", value = "/updateUser")
public class UpdateUser extends HttpServlet {

    private AstonUsersDAO usersDAO = new AstonUsersDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();


        String userId = request.getParameter("id");

        int id = Integer.parseInt(userId);

        UsersAston user = usersDAO.getUserById(id);


        out.println("<h2>Edit User Account</h2>");
        out.print(
                "<form action='patchUser' method='post'>");
        out.print("<table>");
        out.print(
                "<tr><td></td><td><input type='hidden' name='id' value='"
                        + user.getId() + "'/></td></tr>");
        out.print(
                "<tr><td>Name:</td><td><input type='text' name='name' value='"
                        + user.getUsername() + "'/></td></tr>");
        out.print(
                "<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");
        out.print("</table>");
        out.print("</form>");


        out.close();
    }
}
