package my_reg_controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my_reg_dao.studentDao;
import my_reg_model.student;

import java.io.IOException;

/**
 * Servlet implementation class studentServlet
 */
@WebServlet("/studentServlet")
public class studentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private studentDao studentDao = new studentDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/myStudent.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Insert new student
        String id = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        if (id != null && !id.isEmpty() && firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            int studentId = Integer.parseInt(id);
            student student = new student(studentId, firstName, lastName);

            try {
                studentDao.insertStudent(student);
                //response.sendRedirect("studentdetails.jsp");
                response.getWriter().append("Student inserted successfully changes.");
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().append("Error: Unable to insert student. ").append(e.getMessage());
            }
        } else {
            response.getWriter().append("Invalid input. Please provide valid ID, first name, and last name.");
        }
    }
}
