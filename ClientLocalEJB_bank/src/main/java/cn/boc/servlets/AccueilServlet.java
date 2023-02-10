package cn.boc.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.boc.logic.AccountEJBImpl;
import ch.boc.logic.BankEJBImpl;
import ch.boc.logic.ILocalAccount;
import ch.boc.logic.ILocalBank;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ILocalBank bank = new BankEJBImpl();

	@EJB
	private ILocalAccount account;

	public AccueilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		account = new AccountEJBImpl();

		System.out.println(account.isActive());

		account.activate();

		System.out.println(account.isActive());

		String code = account.getCode().toString();
		response.getWriter().append("Served at: ").append(code);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
