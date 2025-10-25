// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PartB {
   static final String URL = "jdbc:mysql://bytexldb.com:5051/db_442jha2b9";
   static final String USER = "user_442jha2b9";
   static final String PASSWORD = "p442jha2b9";

   public PartB() {
   }

   public static void main(String[] var0) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection var1 = DriverManager.getConnection("jdbc:mysql://bytexldb.com:5051/db_442jha2b9", "user_442jha2b9", "p442jha2b9");
         var1.setAutoCommit(false);
         Scanner var2 = new Scanner(System.in);

         int var3;
         do {
            System.out.println("\n=== Product Management Menu ===");
            System.out.println("1. Create Product");
            System.out.println("2. Read Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            var3 = var2.nextInt();
            var2.nextLine();
            switch (var3) {
               case 1:
                  createProduct(var1, var2);
                  break;
               case 2:
                  readProducts(var1);
                  break;
               case 3:
                  updateProduct(var1, var2);
                  break;
               case 4:
                  deleteProduct(var1, var2);
                  break;
               case 5:
                  System.out.println("Exiting...");
                  break;
               default:
                  System.out.println("Invalid choice! Try again.");
            }
         } while(var3 != 5);

         var1.close();
         var2.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   static void createProduct(Connection var0, Scanner var1) {
      try {
         System.out.print("Enter Product ID: ");
         int var2 = var1.nextInt();
         var1.nextLine();
         System.out.print("Enter Product Name: ");
         String var3 = var1.nextLine();
         System.out.print("Enter Price: ");
         double var4 = var1.nextDouble();
         System.out.print("Enter Quantity: ");
         int var6 = var1.nextInt();
         String var7 = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
         PreparedStatement var8 = var0.prepareStatement(var7);
         var8.setInt(1, var2);
         var8.setString(2, var3);
         var8.setDouble(3, var4);
         var8.setInt(4, var6);
         int var9 = var8.executeUpdate();
         var0.commit();
         System.out.println("" + var9 + " product(s) inserted successfully.");
      } catch (SQLException var11) {
         try {
            var0.rollback();
            System.out.println("Error! Transaction rolled back.");
         } catch (SQLException var10) {
            var10.printStackTrace();
         }

         var11.printStackTrace();
      }

   }

   static void readProducts(Connection var0) {
      try {
         String var1 = "SELECT * FROM Product";
         Statement var2 = var0.createStatement();
         ResultSet var3 = var2.executeQuery(var1);
         System.out.println("ProductID\tProductName\tPrice\tQuantity");
         System.out.println("-----------------------------------------------");

         while(var3.next()) {
            PrintStream var10000 = System.out;
            int var10001 = var3.getInt("ProductID");
            var10000.println("" + var10001 + "\t" + var3.getString("ProductName") + "\t" + var3.getDouble("Price") + "\t" + var3.getInt("Quantity"));
         }
      } catch (SQLException var4) {
         var4.printStackTrace();
      }

   }

   static void updateProduct(Connection var0, Scanner var1) {
      try {
         System.out.print("Enter Product ID to update: ");
         int var2 = var1.nextInt();
         var1.nextLine();
         System.out.print("Enter new Product Name: ");
         String var3 = var1.nextLine();
         System.out.print("Enter new Price: ");
         double var4 = var1.nextDouble();
         System.out.print("Enter new Quantity: ");
         int var6 = var1.nextInt();
         String var7 = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
         PreparedStatement var8 = var0.prepareStatement(var7);
         var8.setString(1, var3);
         var8.setDouble(2, var4);
         var8.setInt(3, var6);
         var8.setInt(4, var2);
         int var9 = var8.executeUpdate();
         var0.commit();
         System.out.println("" + var9 + " product(s) updated successfully.");
      } catch (SQLException var11) {
         try {
            var0.rollback();
            System.out.println("Error! Transaction rolled back.");
         } catch (SQLException var10) {
            var10.printStackTrace();
         }

         var11.printStackTrace();
      }

   }

   static void deleteProduct(Connection var0, Scanner var1) {
      try {
         System.out.print("Enter Product ID to delete: ");
         int var2 = var1.nextInt();
         String var3 = "DELETE FROM Product WHERE ProductID=?";
         PreparedStatement var4 = var0.prepareStatement(var3);
         var4.setInt(1, var2);
         int var5 = var4.executeUpdate();
         var0.commit();
         System.out.println("" + var5 + " product(s) deleted successfully.");
      } catch (SQLException var7) {
         try {
            var0.rollback();
            System.out.println("Error! Transaction rolled back.");
         } catch (SQLException var6) {
            var6.printStackTrace();
         }

         var7.printStackTrace();
      }

   }
}
