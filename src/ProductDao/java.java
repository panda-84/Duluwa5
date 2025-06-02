/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 */
package ProductDao;

/**
 *
 * @author shita
 */
public class java {
   MySqlConnection mysql = new MySqlConnection();
    
    public void createProduct(Product product) {
         Connection conn = mysql.openConnection();
         String sql = "INSERT INTO products (productName, productImage, productPrice) VALUES (?, ?, ?)";
          
         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setString(1, product.getProductName());
             pstmt.setString(2, product.getProductImage());
             pstmt.setInt(3, product.getProductPrice());
             pstmt.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             mysql.closeConnection(conn);
         }
    }
}

}
