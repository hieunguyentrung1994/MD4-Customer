package ra.controller;

import ra.model.Customer;
import ra.service.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerServlet", value = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    protected CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        customerService.save(new Customer(1,"John", "John@gmail.com","USA"));
        customerService.save(new Customer(2,"Mary", "Mary@gmail.com","Canada"));
        customerService.save(new Customer(3,"Lisa", "Lisa@gmail.com","England"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action");
      if (action != null){
          switch (action){
              case "GETALL":
                  showListCustomer(customerService.findAll(),request, response);
                  break;
              case "DELETE":
                  int idDelete = Integer.parseInt(request.getParameter("id"));
                  customerService.delete(idDelete);
                  showListCustomer(customerService.findAll(),request, response);
                  break;
              case "EDIT":
                  int idEdit = Integer.parseInt(request.getParameter("id"));
                  Customer customer = customerService.findById(idEdit);
                  request.setAttribute("customer",customer);
                  request.getRequestDispatcher("/WEB-INF/editCustomer.jsp").forward(request, response);
                  break;
              case "ADD":
                  request.getRequestDispatcher("/WEB-INF/newCustomer.jsp").forward(request, response);
              case "SEARCH":
                  String search = request.getParameter("search");
                  List<Customer> p = new ArrayList<>();
                  List<Customer> list = customerService.findAll();
                  for (Customer customers1: list) {
                      if (customers1.getName().toLowerCase().contains(search.toLowerCase()) || customers1.getAddress().toLowerCase().contains(search.toLowerCase())){
                          p.add(customers1);
                      }
                  }
                  request.setAttribute("customers", p);
                  request.getRequestDispatcher("/WEB-INF/listCustomer.jsp").forward(request, response);
                  break;
          }
      }
    }

    protected void showListCustomer(List<Customer> customers, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/listCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action !=null){
            switch(action){
                case "UPDATE":
                    int id =Integer.parseInt( request.getParameter("id"));
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String address = request.getParameter("address");
                    customerService.save(new Customer(id,name,email,address));
                    break;
                case "ADD":
                    int newId = customerService.getNewId();
                    String newName = request.getParameter("name");
                    String newEmail = request.getParameter("email");
                    String newAddress = request.getParameter("address");
                    customerService.save(new Customer(newId,newName,newEmail,newAddress));
                    break;
            }
            showListCustomer(customerService.findAll(),request,response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}