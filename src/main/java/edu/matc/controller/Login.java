package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.persistence.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1;
    private UserDao login;
    private GenericDao dao = new GenericDao(User.class);
    private final Logger log = LogManager.getLogger(this.getClass());



//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
//            ServletException, IOException {
//
//        HttpSession session = req.getSession();
//
//        String userName = req.getRemoteUser();
//
//        log.info(userName + " successfully logged in");
//
//        List<User> userFound = (List<User>) dao.findByPropertyEqual("userName", userName);
//        User user = userFound.get(1);
//
//        req.setAttribute("userName", userName);
//        req.setAttribute("userLogin", user);
//        session.setAttribute("userLogin", user);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/signUpSuccess.jsp");
//        dispatcher.forward(req, resp);
//    }

    public void init() {
        login = new UserDao();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("userLogin.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            authenticate(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (login.validate(username, password)) {
            HttpSession session = req.getSession();
//            List<User> foundByUsername = dao.findByPropertyEqual("username", username);
//            User user = foundByUsername.get(0);
//            req.setAttribute("username", username);
//            req.setAttribute("user", user);
            session.setAttribute("username", username);

            RequestDispatcher dispatcher = req.getRequestDispatcher("loginSuccess.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("loginError.jsp");
            dispatcher.forward(req, resp);
            throw new Exception("Login not successful..");
        }
    }
}