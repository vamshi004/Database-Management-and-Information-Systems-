
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
 * Servlet implementation class IssueServlet
 */
@WebServlet("/IssueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public IssueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
		//getting input values from jsp page
		Integer student_id = Integer.parseInt(request.getParameter("student_id"));
		Integer book_id = Integer.parseInt(request.getParameter("book_id"));
		String issue_date = request.getParameter("issue_date");
		
		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/library"; //PostgreSQL URL and followed by the database name
 		String username = "postgres"; //PostgreSQL username
 		String password = "sunny2614"; //PostgreSQL password

		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
		PreparedStatement ps = con.prepareStatement("SELECT student_id FROM student WHERE student_id = ?");
		ps.setInt(1, student_id);
		ResultSet rset = ps.executeQuery();
		int result = 0;
		while(rset.next()){
	 		System.out.println("Printing connection object "+con);
			//Prepared Statement to add student data
			PreparedStatement st = con.prepareStatement("INSERT INTO issue VALUES(?,?,?,?)");
	 		st.setInt(1,student_id);
			st.setInt(2,book_id);
			st.setString(3,issue_date);
			st.setString(4,null);
			int result=st.executeUpdate();
		}

		//Checks if insert is successful.If yes,then redirects to Result.jsp page 
		if(result>0)
		{
			
			RequestDispatcher rd = request.getRequestDispatcher("IssueResult.jsp");
			rd.forward(request, response);
		}

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}


}


