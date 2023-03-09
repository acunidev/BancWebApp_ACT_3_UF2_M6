package com.banc.bancwebapp.web;

import com.banc.bancwebapp.connection.HibernateUtil;
import com.banc.bancwebapp.model.ClientEntity;
import com.banc.bancwebapp.model.CompteEntity;
import com.banc.bancwebapp.repository.dao.ClientDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "mostrarClientes", value = "/mostrarClientes")
public class MostrarClienteServlet extends HttpServlet {
    private static PrintWriter printWriter;

    private static boolean ClientFindedByDNI(ClientDaoImpl clientDao, String dniBuscar) {
        return clientDao.findByDni(dniBuscar) != null;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction()
               .begin();

        ClientDaoImpl clientDao = new ClientDaoImpl(sessionFactory);

        String dniBuscar = request.getParameter("dniBuscar");
        if (ClientFindedByDNI(clientDao, dniBuscar)) {
            ClientEntity client = clientDao.findByDni(dniBuscar);
            final long idClient = client.getIdClient();
            List<CompteEntity> compteEntities = clientDao.findComptesByClientId(idClient);
            response.setContentType("text/html");
            printWriter = response.getWriter();
            printWriter.println("<HTML>");
            printWriter.println("<BODY>");
            printWriter.println("<H1>Cliente:" + client.getNom() + "</H1>");
            printWriter.println("<h2>DNI:" + client.getDNI() + "</h2>");
            printWriter.println("<h3>Comptes</h3>");
            for (CompteEntity compteEntity : compteEntities) {
                printWriter.println(
                        "<p>IBAN: " + compteEntity.getIban() + " Saldo: " + compteEntity.getSaldo() + "</p>");

            }

            printWriter.println("</BODY>");
            printWriter.println("</HTML>");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<HTML>");
            out.println("<BODY>");
            out.println("<h1>cliente no existe</h1>");
            out.println("</BODY>");
            out.println("</HTML>");
        }

    }
}
