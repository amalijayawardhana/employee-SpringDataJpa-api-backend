package emp.web.api;


import emp.web.business.custom.EmployeeBO;
import emp.web.dto.EmployeeDTO;
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
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/api/v1/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeBO employeeBO;

    public void init(){
        employeeBO = ((AnnotationConfigApplicationContext)(getServletContext().getAttribute("ctx"))).getBean(EmployeeBO.class);
    }

    public static String getParameter(String queryString, String parameterName) throws UnsupportedEncodingException {
        if (queryString == null || parameterName == null || queryString.trim().isEmpty() || parameterName.trim().isEmpty()) {
            return null;
        }
        String[] queryParameters = queryString.split("&");
        for (String queryParameter : queryParameters) {
            if (queryParameter.contains("=") && queryParameter.startsWith(parameterName)) {
                return URLDecoder.decode(queryParameter.split("=")[1], "UTF-8");
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        resp.setContentType("text/plain");
        try (PrintWriter out = resp.getWriter()){
            if (code==null) {
                resp.setContentType("application/json");
                List<EmployeeDTO> allEmployees = employeeBO.getAllEmployees();
                Jsonb jsonb = JsonbBuilder.create();
                String json = jsonb.toJson(allEmployees);
                out.println(json);
            }else{
                if (employeeBO.EmployeeExist(code)==true) {
                    EmployeeDTO Employee = employeeBO.getEmployee(code);
                    out.println(Employee);
                }
                else{
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("id");
        String initials = req.getParameter("initials");
        String firstName = req.getParameter("firstName");
        String surname = req.getParameter("surname");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String dob = req.getParameter("dob");
        String status = req.getParameter("status");

        if (code == null || firstName == null|| surname == null || address1 == null|| address2 == null|| initials == null
                || dob == null|| status == null | !code.matches("E\\d{2}") || firstName.trim().length()>50
                || surname.trim().length()>50 || address1.trim().length()>100|| address2.trim().length()>100) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.setContentType("text/plain");

        try (PrintWriter out = resp.getWriter()){
            if (employeeBO.EmployeeExist(code)==true) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            employeeBO.saveEmployee(code,initials,firstName,surname,address1,address2, Date.valueOf(dob),status);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.println("Employee Saved Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { ;
        String queryString = req.getQueryString();

        resp.setContentType("text/plain");
        if (queryString == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        BufferedReader reader = req.getReader();
        String line = null;
        String requestBody = "";

        while ((line = reader.readLine())!=null) {
            requestBody+= line;
        }
        String code = getParameter(queryString,"cod");
        String initials = getParameter(requestBody,"initials");
        String firstName = getParameter(requestBody,"firstName");
        String surname = getParameter(requestBody,"surname");
        String address1 = getParameter(requestBody,"address1");
        String address2 = getParameter(requestBody,"address2");
        String dob = getParameter(requestBody,"dob");
        String status = getParameter(requestBody,"status");

        System.out.println(code);


        if (code == null || firstName == null|| surname == null || address1 == null|| address2 == null|| initials == null
                || dob == null|| status == null | !code.matches("E\\d{2}") || firstName.trim().length()>50
                || surname.trim().length()>50 || address1.trim().length()>100|| address2.trim().length()>100) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try{
            if (employeeBO.EmployeeExist(code)==false) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;

            }
            employeeBO.updateEmployee(code, initials, firstName, surname, address1, address2, Date.valueOf(dob), status);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        System.out.println(code);
        resp.setContentType("text/plain");
        if (employeeBO.EmployeeExist(code)==true) {
            try {
                employeeBO.deleteEmployee(code);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
