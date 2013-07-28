import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import tictactoe.*;
/**
 *
 * @author Varun Agrawal
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    public TicTacToe game;
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);        
        
        game = new TicTacToe();
        Game.CELLSFILLED = 0;
        
    }
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int t = Integer.parseInt(request.getParameter("move"));
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TicTacToe Turing Test</title>");            
            out.println("</head>");
            out.println("<body>");
            
//            if( Game.CELLSFILLED < 9 ){
//                int status = game.play( Integer.parseInt(request.getParameter("move")) );
//            
//                if(status == 0){
//                    out.println("<p>Move made</p>");
//                }
//            }else{
//                out.println("<p>No more valid moves!</p>");
//            }
                        
            out.println("<h1>Welcome to the TicTacToe Turing Test at " + request.getContextPath() + "</h1>");
            out.println("<p>You clicked button: " + t + "</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }                      
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ITA Servlet - Tic Tac Toe";
    }// </editor-fold>
    
}
