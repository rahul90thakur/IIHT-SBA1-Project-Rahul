package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;


@WebServlet({"/listall","/process","/callemi","/updatestatus","/logout"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "/listall" : 
				viewName = listall(request, response);
				break;
			case "/process":
				viewName=process(request,response);
				break;
			case "/callemi":
				viewName=calemi(request,response);
				break;
			case "/updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "/logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		
		return null;
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
		LoanInfo loaninformation = new LoanInfo();
		
		User user= new User();
		String applicationNumber = request.getParameter("applno");
		String amountRequested = request.getParameter("amtrequest");
		String dateofAssigned = request.getParameter("doa");
		String bankstructure = request.getParameter("bstructure");
		String bankIndicator = request.getParameter("bindicator");
		String address = request.getParameter("address");
			loaninformation.setAmtrequest(Integer.parseInt(request.getParameter("applno")));
				loaninformation.setRate(Double.parseDouble(request.getParameter("amtrequest")));
				loan.setDisbursementDate(LocalDate.parse(request.getParameter("doa")));
				loan.setDueDate(LocalDate.parse(request.getParameter("bstructure")));
				loan.setDueDate(LocalDate.parse(request.getParameter("bindicator")));
				loan.setDueDate(LocalDate.parse(request.getParameter("address")));
					if(amountRequested.isEmpty() || dateofAssigned.isEmpty() || bankstructure.isEmpty()) {
						double time = Period.between(loaninformation.getDoa(),loaninformation.getBstructure())
								.toTotalMonths()/12.0;
						double interest = time*loaninformation.getLoanAmount()*loaninformation.getRate();
						double pamt = loaninformation.getAmtRequested()+interest;
						
						loaninformation.setTimeInYears(time);
						loaninformation.setInterest(interest);
						loaninformation.setPayableAmount(pamt);
					}
					return loan;
				
				loaninformation = loancomputation.computeInterest(loan);
				
				
				request.setAttribute("loan", loan);
				
				String view = "simple_interest_display_page.jsp";
				request.getRequestDispatcher(view).forward(request, response);
				
		return "calemi.jsp";
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
	/* return to process page */
		String applicationNumber = request.getParameter("applno");
		LoanInfo loaninformation = new LoanInfo();
		loaninformation.setAmtrequest(Integer.parseInt(request.getParameter("applno")));
		request.setAttribute("loanprocess",loaninformation);
		RequestDispatcher rd=request.getRequestDispatcher("process.jsp");
		rd.forward(request, response); 
		
		return  null;
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		HttpSession session = request.getSession();
		String incomingReqPath = request.getServletPath();
		String view = "";
		
		session.removeAttribute("user");
		session.invalidate();
		view = "index.jsp";
		return null;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		request.setAttribute("loanList", Loaninfo.listAll());
		return "/listall.jsp";
		
	}

	
}