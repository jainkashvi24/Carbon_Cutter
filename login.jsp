<%@ page import="java.sql.*" %>
<%@ page import="Db_Connection.MyConnection" %>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    boolean isValidUser = false;

    if (username != null && password != null) {
        try {
            Connection con = MyConnection.getConnection();
            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isValidUser = true;
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    if (isValidUser) {
       response.sendRedirect("calculator.jsp");
    } else {
        response.sendRedirect("index.html");
    }
%>

