package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;




@WebServlet({"/registernewuser","/validate","/placeloan","/application1","/editLoanProcess","/registeruser","/register"
	,"/application","/trackloan","/editloan","/displaystatus"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
private LoanDto loancomputation;

	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbc:mysql://localhost:3306/test");
		String jdbcUsername = config.getServletContext().getInitParameter("root");
		String jdbcPassword = config.getServletContext().getInitParameter("root");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}
	private User userservice;
	private ApprovedLoan approvedloan;
	private LoanInfo loaninformation;
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "/registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "/validate":
				viewName=validate(request,response);
				break;
			case "/placeloan":
				viewName=placeloan(request,response);
				break;
			case "/application1":
				viewName=application1(request,response);
				break;
			case "/editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "/registeruser":
				viewName=registerUser(request,response);
				break;
			case "/register":
				viewName = register(request, response);
				break;
			case "/application":
				viewName = application(request, response);
				break;
			case "/trackloan":
				viewName = trackloan(request, response);
				break;
			case "/editloan":
				viewName = editloan(request, response);
				break;	
			case  "/displaystatus" :
				viewName=displaystatus(request,response);
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
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		/* write the code to validate the user */
		String usr_nm = request.getParameter("user_name");
		String psswrd = request.getParameter("password");
		if(usr_nm=="user"||psswrd=="user") {
			RequestDispatcher req1 = request.getRequestDispatcher("userhome1.jsp");
			req1.forward(request, response);}
			else{
				RequestDispatcher req1 = request.getRequestDispatcher("errorPage.jsp");
			req1.include(request, response);
			}
		
		return null;
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
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
		
		return null;
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		ConnectionDao con= new ConnectionDao("jdbc:mysql://localhost:3306/test", "root", "root");
		List<LoanInfo> loaninformationlist=new ArrayList<>();
		PreparedStatement pst = con.prepareStatement(Select_ALL_Query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				LoanInfo.setApplno(rs.getInt(1));
				LoanInfo.setPurpose(rs.getString(2));
				LoanInfo.setAmtrequest(rs.getDouble(3));
				LoanInfo.setDoa(rs.getDate(4).toLocalDate());
				LoanInfo.setBstructure(rs.getString(5));
				LoanInfo.setBindicator(rs.getString(6));
				LoanInfo.setAddress(rs.getString(7));
				LoanInfo.setEmail(rs.getString(8));
				LoanInfo.setMobile(rs.getString(9));
				LoanInfo.setStatus(rs.getString(10));
			}
		return "/application.jsp";
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		int bcode = Integer.parseInt(request.getParameter("applno"));
		LoanInfo loaninformation = new LoanInfo();
				String applcationnumber=loaninformation.getApplno();
		request.setAttribute("isNew",false);
		request.setAttribute("LoanInfo",loaninformation);
		return "/application.jsp";
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		
		String userName1 = request.getParameter("unm");
		String password1 = request.getParameter("pwd");

		if (userName1 == null) {
			view = "notfound.jsp";
		} else if (password1 == null) {
			request.setAttribute("err", "Password is not received!");
			view = "userhome.jsp";
		} else {
			try {
				User user = userService.authenticate(userName1, password1);
				session.setAttribute("user", user);
				view = "index.jsp";
			} catch (AddressBookException e) {
				request.setAttribute("err", e.getMessage());
				view = "userhome1.jsp";
			}
		}
	}
	
		
		
		
		
		
		
		
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		
		
		User user= new User();
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		if(first_name.isEmpty() || last_name.isEmpty() || username.isEmpty() || 
				password.isEmpty() || address.isEmpty() || contact.isEmpty())
		{
			RequestDispatcher req = request.getRequestDispatcher("errorPage.jsp");
			req.include(request, response);
		}
		else
		{
			RequestDispatcher req = request.getRequestDispatcher("register.jsp");
			req.forward(request, response);
		}
		String view = "";
		
		RequestDispatcher req = request.getRequestDispatcher("register.jsp");
		req.forward(request, response);
		return "newuserui.jsp";
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		User user= new User();
		String loginID = request.getParameter("username");
		String password = request.getParameter("password");
		
		user.setUsername(loginID);
		user.setPassword(password);
		request.setAttribute("userlogin",user);
		RequestDispatcher rd=request.getRequestDispatcher("newuserui.jsp"); 
		rd.forward(request, response); 
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		int bcode = Integer.parseInt(request.getParameter("applno"));
		LoanInfo loaninformation = new LoanInfo();
				String applcationnumber=loaninformation.getApplno();
		request.setAttribute("isNew",false);
		request.setAttribute("LoanInfo",loaninformation);
		return "editloan.jsp";
		
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		LoanInfo loaninformation= new LoanInfo();
		String applno = request.getParameter("applno");
		String email = request.getParameter("email");
		
		loaninformation.setApplno(applno);
		loaninformation.setEmail(email);
		request.setAttribute("trackloan",loaninformation);
		RequestDispatcher rd=request.getRequestDispatcher("newuserui.jsp"); 
		rd.forward(request, response); 
		return "trackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		return null;
	}
}