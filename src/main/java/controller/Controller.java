package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns =  {"/Controller", "/main", "/insert", "/select", "/update", "/delete/*"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();
    private JavaBeans contato = new JavaBeans();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caminho = request.getServletPath();
		if(caminho.equals("/main")) {
			listarContatos(request, response);
		} else if(caminho.equals("/select")) {
			obterContato(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		contato.setNome(nome);
		contato.setFone(fone);
		contato.setEmail(email);
		this.salvarContato(contato);
		response.sendRedirect("main");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        Gson gson = new Gson();
        JavaBeans contato = gson.fromJson(requestBody.toString(), JavaBeans.class);
		if(this.atualizarContato(contato)) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id")); 
		if(deletarContato(id)) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	private void listarContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<JavaBeans> contatos  = dao.listarContatos();
		request.setAttribute("contatos", contatos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("agenda.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void obterContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		JavaBeans contato = dao.pegarContatoPorID( Integer.parseInt(request.getParameter("id")));
		request.setAttribute("contato", contato);
		RequestDispatcher requestDispatcher =  request.getRequestDispatcher("editar.jsp");
		requestDispatcher.forward(request, response);
	}

	private void salvarContato(JavaBeans contato) throws ServletException, IOException {
		dao.inserirContato(contato);
	}
	
	private Boolean atualizarContato(JavaBeans contato) throws ServletException, IOException {
		System.out.println(contato.toString());
		return dao.atualizarContato(contato);
	}
	
	private Boolean deletarContato(Integer id) throws ServletException, IOException {
		return dao.deletarContato(id);
	}
}
