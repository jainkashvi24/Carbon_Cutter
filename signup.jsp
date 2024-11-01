<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*"%>
<%@ page import="Db_Connection.MyConnection" %>
 
      <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Connection con = null;
            PreparedStatement checkps = null;
            PreparedStatement insertps = null;

           
                
                // Check if the username already exists
                String checkQuery = "SELECT COUNT(*) FROM login WHERE username = ?";
                checkps = con.prepareStatement(checkQuery);
                checkps.setString(1, username);
                ResultSet rs = checkps.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    out.println("<p style='color:red;'>Username already exists. Please choose a different one.</p>");
                } else {
                    // Insert new user
                    String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                    insertps = con.prepareStatement(insertQuery);
                     insertps.setString(1, username);
                     insertps.setString(2, password); // Ensure you hash passwords in production!
                     insertps.executeUpdate();

                    out.println("<p style='color:green;'>Registration successful! You can now log in.</p>");
                }
            
            } 
            
        
    %>
