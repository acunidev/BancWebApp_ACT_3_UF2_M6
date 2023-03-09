package com.banc.bancwebapp.web;

import com.banc.bancwebapp.connection.HibernateUtil;
import com.banc.bancwebapp.model.ClientEntity;
import com.banc.bancwebapp.model.CompteEntity;
import com.banc.bancwebapp.repository.dao.ClientDaoImpl;
import com.banc.bancwebapp.repository.dao.CompteDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "create User", urlPatterns = "/crearUsuario")
public class CreateClientServlet extends HttpServlet {
    private static PrintWriter printWriter;
    private String message;

    private static boolean ifClienteYCuentaNoExisten(ClientDaoImpl clientDao, CompteDaoImpl compteDao,
                                                     ClientEntity client, CompteEntity compte) {
        return clientDao.findByDni(client.getDNI()) == null && compteDao.findById(compte.getIban()) == null;
    }

    private static boolean ifClienteYCuentaExisten(ClientDaoImpl clientDao, CompteDaoImpl compteDao,
                                                   ClientEntity client, CompteEntity compte) {
        return clientDao.findByDni(client.getDNI()) != null && compteDao.findById(compte.getIban()) != null;
    }

    private static void printTextNuevoClienteCuenta(HttpServletResponse resp, String inputNombreCliente,
                                                    String inputIdFiscal, String inputEmail, String inputPais,
                                                    String inputCuenta, int inputIngresoInicial) throws IOException {
        resp.setContentType("text/html");
        printWriter = resp.getWriter();
        printWriter.println("<HTML>");
        printWriter.println("<BODY>");
        printWriter.println("<H1> NUEVO CLIENTE</H1>");
        printWriter.println("<p>" + "Nombre Cliente: " + inputNombreCliente + "</p>");
        printWriter.println("<p>" + "Id Fiscal: " + inputIdFiscal + "</p>");
        printWriter.println("<p>" + "Email: " + inputEmail + "</p>");
        printWriter.println("<p>" + "Pais: " + inputPais + "</p>");
        printWriter.println("<H1> NUEVA CUENTA</H1>");
        printWriter.println("<p>" + "Cuenta: " + inputCuenta + "</p>");
        printWriter.println("<p>" + "Ingreso Inicial: " + inputIngresoInicial + "</p>");
        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
    }

    private static void printTextClienteYCuentaYaExisten(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        printWriter = resp.getWriter();
        printWriter.println("<HTML>");
        printWriter.println("<BODY>");
        printWriter.println("<H1>Cliente y cuenta ya existen</H1>");
        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
    }

    private static void printTextClienteExisteCuentaAnadida(HttpServletResponse resp, String inputNombreCliente,
                                                            String inputCuenta,
                                                            int inputIngresoInicial) throws IOException {
        resp.setContentType("text/html");
        printWriter = resp.getWriter();
        printWriter.println("<HTML>");
        printWriter.println("<BODY>");
        printWriter.println("<H1> Cuenta agregada a " + inputNombreCliente + "</H1>");
        printWriter.println("<p>" + "Cuenta: " + inputCuenta + "</p>");
        printWriter.println("<p>" + "Ingreso Inicial: " + inputIngresoInicial + "</p>");
        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
    }

    public void init() {
        message = "Alex";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session sesion = sessionFactory.getCurrentSession();

        sesion.getTransaction()
              .begin();
        ClientDaoImpl clientDao = new ClientDaoImpl(sessionFactory);
        CompteDaoImpl compteDao = new CompteDaoImpl(sessionFactory);

        String inputNombreCliente = req.getParameter("inputNombreCliente");

        String inputIdFiscal = req.getParameter("inputIdFiscal");
        String inputEmail = req.getParameter("inputEmail");
        String inputPais = req.getParameter("inputPais");
        String inputCuenta = req.getParameter("inputCuenta");
        int inputIngresoInicial = Integer.parseInt(req.getParameter("inputIngresoInicial"));

        ClientEntity client = new ClientEntity(inputNombreCliente, inputIdFiscal, inputEmail, inputPais);
        CompteEntity compte = new CompteEntity(inputCuenta, inputIngresoInicial, client);

        if (ifClienteYCuentaExisten(clientDao, compteDao, client, compte)) {
            printTextClienteYCuentaYaExisten(resp);
        } else if (ifClienteYCuentaNoExisten(clientDao, compteDao, client, compte)) {
            clientDao.save(client);
            compteDao.save(compte);
            printTextNuevoClienteCuenta(resp, inputNombreCliente, inputIdFiscal, inputEmail, inputPais, inputCuenta,
                                        inputIngresoInicial);
        } else if (clientDao.findByDni(client.getDNI()) != null && compteDao.findById(compte.getIban()) == null) {
            ClientEntity clientEntity = clientDao.findByDni(client.getDNI());
            compte.setCompteClientEntity(clientEntity);
            compteDao.save(compte);
            System.out.println("hola");
            printTextClienteExisteCuentaAnadida(resp, inputNombreCliente, inputCuenta, inputIngresoInicial);
        }

        sesion.getTransaction()
              .commit();
        sesion.close();
    }

    public void destroy() {
    }
}
