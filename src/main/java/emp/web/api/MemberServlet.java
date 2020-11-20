package emp.web.api;

import emp.web.business.custom.MemberBO;
import emp.web.dto.MemberDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet(name = "MemberServlet", urlPatterns = "/api/v1/members")
public class MemberServlet extends HttpServlet {

    private MemberBO memberBO;

    public void init() {
        memberBO = ((AnnotationConfigApplicationContext) (getServletContext().getAttribute("ctx"))).getBean(memberBO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("txt-id");

        resp.setContentType("text/plain");
        try (PrintWriter out = resp.getWriter()) {
            if (id == null) {
                List<MemberDTO> allMembers = memberBO.getAllMembers();
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                String json = jsonb.toJson(allMembers);
                out.println(json);
            } else {
                if (!memberBO.MemberExist(Integer.parseInt(id))) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                MemberDTO Member = memberBO.getMember(Integer.parseInt(id));
                out.println(Member);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("txt-id");
        String firstName = req.getParameter("txt-first-name");
        String surname = req.getParameter("txt-surname");
        String empCode = req.getParameter("txt-code");

        if (firstName == null || surname == null || empCode == null || firstName.trim().length() >50 || surname.trim().length() >50) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setContentType("text/plain");
        if (memberBO.MemberExist(Integer.parseInt(id))) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private String getParameter(String queryString, String parameterName) throws UnsupportedEncodingException {

        return null;

    }
}
