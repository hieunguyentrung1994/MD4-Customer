package ra.service;

import ra.model.Customer;
import ra.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IGenericService<Customer,Integer>{
    private final String FIND_ALL ="SELECT * from Customers";
    private final String INSERT_CUSTOMER ="INSERT INTO CUSTOMERS(name,email,address) VALUES(?,?,?)";
    private final String UPDATE_CUSTOMER ="UPDATE CUSTOMERS SET name =?,email=? ,address=? WHERE id =?";
    private final String FIND_BY_ID ="SELECT * from Customers Where id = ?";
    private final String DELETE_BY_ID = "DELETE FROM CUSTOMERS WHERE id = ?";






    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            PreparedStatement prest = conn.prepareStatement(FIND_ALL);
            ResultSet rs =prest.executeQuery();
            while (rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                customers.add(c);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return customers; }

    @Override
    public void save(Customer customer) {
  Connection conn = null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            PreparedStatement preSt = null;
            if(customer.getId()==0){
                // chức năng thêm mới
                preSt = conn.prepareStatement(INSERT_CUSTOMER);
                // Đặt tham số trong câu truy vấn
                preSt.setString(1,customer.getName());
                preSt.setString(2,customer.getEmail());
                preSt.setString(3,customer.getAddress());

                // thực thi câu lệnh sql
                preSt.executeUpdate();
            }else {
                // cập nhật
                preSt = conn.prepareStatement(UPDATE_CUSTOMER);
                // Đặt tham số trong câu truy vấn
                preSt.setString(1,customer.getName());
                preSt.setString(2,customer.getEmail());
                preSt.setString(3,customer.getAddress());
                preSt.setInt(4,customer.getId());
                // thực thi câu lệnh sql
                preSt.executeUpdate();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Customer findById(Integer integer) {
        Customer customer = null;
        Connection conn = null;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectDB.getConnection();

            // Chuẩn bị câu lệnh SQL sử dụng câu truy vấn đã cung cấp
            PreparedStatement preSt = conn.prepareStatement(FIND_BY_ID);
            // Đặt tham số id trong câu truy vấn
            preSt.setInt(1, integer);

            // Thực hiện câu truy vấn SQL và lưu kết quả vào ResultSet
            ResultSet rs = preSt.executeQuery();
            // Duyệt qua ResultSet
            while (rs.next()) {
                // Tạo một đối tượng khách hàng mới cho mỗi kết quả
                customer = new Customer();
                // Đặt các thuộc tính của khách hàng từ ResultSet
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            // Nếu có ngoại lệ SQL, ném ra RuntimeException mới
            throw new RuntimeException(e);
        } finally {
            // Đóng kết nối đến cơ sở dữ liệu bất kể kết quả
            ConnectDB.closeConnection(conn);
        }
        // Trả về đối tượng khách hàng đã tìm thấy (hoặc null nếu không tìm thấy khách hàng)
        return customer;
    }


    @Override
    public void delete(Integer integer) {
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();
            // chuẩn bị câu lệnh
            PreparedStatement preSt = conn.prepareStatement(DELETE_BY_ID);
            // truyền đối số
            preSt.setInt(1,integer);
            // thực thi câu lệnh xóa
            preSt.executeUpdate();

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }


}
