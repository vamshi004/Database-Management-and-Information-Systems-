
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
		//getting input values from jsp page
		Integer book_id = Integer.parseInt(request.getParameter("book_id"));
		String book_title = request.getParameter("title");
		String book_category = request.getParameter("year");
		String author = request.getParameter("author");


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/university"; //PostgreSQL URL and followed by the database name
 		String username = "postgres"; //PostgreSQL username
 		String password = "123456"; //PostgreSQL password
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database

		PreparedStatement ps = con.prepareStatement("SELECT book_id FROM book WHERE book_id = ?");
		ps.setInt(1,book_id);
		ResultSet rset = ps.executeQuery();
		int result = 0;
		while(!rset.next()){
	 		System.out.println("Printing connection object "+con);
			//Prepared Statement to add student data
			PreparedStatement st = con .prepareStatement("INSERT INTO book values(?,?,?,?)");
	 		st.setInt(1,book_id);
			st.setString(2,title);
			st.setString(3,category);
			st.setString(4,author);
			int result=st.executeUpdate();
		}

		//Checks if insert is successful.If yes,then redirects to Result.jsp page 
		if(result>0)
		{
			
			RequestDispatcher rd = request.getRequestDispatcher("AddResult.jsp");
			rd.forward(request, response);
		}

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}


}


