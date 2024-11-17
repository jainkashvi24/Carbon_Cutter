import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // Database connection class
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");  // Updated to use the correct driver class
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get the connection
    private static Connection getConnection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carbon_cutter", "root", "admin");
        return con;
    }

    // Handling POST request for user registration
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement checkps = null;
        PreparedStatement insertps = null;
        PrintWriter out = response.getWriter();

        try {
            con = getConnection();

            String Query = "SELECT * FROM login WHERE username = ? AND password = ?";
            checkps = con.prepareStatement(Query);
            checkps.setString(1, username);
            checkps.setString(2, password);
            ResultSet rs = checkps.executeQuery();

            if (rs.next()) {
                //out.println("<p style='color:red;'>Username already exists. Please choose a different one.</p>");
                response.sendRedirect("option.html");
            } else {
                out.println("<p style='color:red;'>Invalid User ID or Password.</p>");
                response.sendRedirect("index.html");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>An error occurred. Please try again later.</p>");
            out.println(e);
        } finally {
            try {
                if (insertps != null) insertps.close();
                if (checkps != null) checkps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
