package JV2_Assignment6;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static final int ACTIVE =1;
    public static final int DEACTIVE = 0;

    public static void main(String[] args){
        try {
            Connector connector = new Connector();
            // khai bao driver
            Class.forName("com.mysql.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/t1807m";

            Connection conn = DriverManager.getConnection(URL,"account","123123");

            boolean start = true;
            Scanner sc = new Scanner(System.in);
            while (start){
                System.out.println("1: Login");
                System.out.println("2: Register");
                System.out.println("3: Listing");
                System.out.println("4: Delete");
                System.out.println("5: Exit");
                System.out.println("nhap vao lua chon: ");
                int menu = sc.hasNextInt()?sc.nextInt():0;
                switch (menu){
                    case 1:login(connector);break;
                    case 2:register(connector);break;
                    case 3:listing(connector);break;
                    case 4:delete(connector);break;
                    case 5:start= false;break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void login(Connector connector) throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("NHAP USERNAME: ");
        String username = sc.nextLine();
        System.out.println("Nhap password: ");
        String password = sc.nextLine();

        String sql = "SELECT * FROM user WHERE password LIKE '"+password+"'AND username LIKE'"+username+"'";
        ResultSet rs = connector.getQuery(sql);
        if (rs.next()){
            System.out.println("Dang nhap thanh cong");
            return;
        }
        System.out.println("sai username  hoac password");
    }
    public static void register(Connector connector) throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("NHAP USERNAME: ");
        String username = sc.nextLine();
        System.out.println("Nhap Email: ");
        String email = sc.nextLine();
        System.out.println("Nhap password: ");
        String password = sc.nextLine();

        String sql = "INSERT INTO user(username,email,password,status) VALUES('"+username+"','"+email+"','"+password+"','"+ACTIVE+"')";
        connector.updateQuery(sql);
        if (connector.updateQuery(sql)>0){
            System.out.println("Dang ki thanh cong");
            return;
        }
        System.out.println("chua tao duoc tai khoan");
    }
    public static void listing(Connector connector) throws Exception{
        String sql = "SELECT * FROM user";

        ResultSet rs = connector.showExecuteQuery(sql);
        while (rs.next()){
            System.out.println("ID: "+rs.getInt("id"));
            System.out.println("username: "+rs.getString("username"));
            System.out.println("email: "+rs.getString("email"));
            System.out.println("password: "+rs.getString("password"));
            System.out.println("-------------------------------------");
        }
    }
    public static void delete(Connector connector) throws Exception{

            Scanner sc = new Scanner(System.in);

            System.out.println("Nhap id de xoa: ");
            int id = sc.nextInt();
            String sql = " DELETE FROM user WHERE id ='"+id+"'";
            connector.deleteQuery(sql);
            int chon = sc.hasNextInt()?sc.nextInt():0;
    }

}

