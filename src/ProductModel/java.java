/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProductModel;

/**
 *
 * @author shita
 */
public class java {
    public class Product {
        
        private String productName;
        public String getProductName() {
         return productName;
        }
        
        
        public void setProductName(String productName) {
         this.productName = productName;
        }
        
        
        private String productImage;
        public String getProductImage() {
         return productImage;
        }
        
        
        public void setProductImage(String productImage) {
         this.productImage = productImage;
        }
        
        
        private int productPrice;
        
        public int getProductPrice() {
         return productPrice;
        }
        
        
        public void setProductPrice(int productPrice) {
         this.productPrice = productPrice;
        }
        
        
        public Product(String productName, String productImage , int productPrice){
         this.productName = productName;
         this.productImage = productImage;
         this.productPrice = productPrice;
        }
    }

    
}
