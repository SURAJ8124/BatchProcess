import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        var url="https://run.mocky.io/v3/cf3f0c29-19a9-45e5-b3fd-21a5fb425482";
//            httpsrequest
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client= HttpClient.newBuilder().build();
        HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
        var body=response.body();
        System.out.println(response.statusCode());
        System.out.println(body);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FacebookApi","root","root@123");
            Statement stmt=con.createStatement();
            String sql1="UPDATE Users set name=? where faceBookId=?";
            PreparedStatement stmt1=con.prepareStatement(sql1);
            stmt1.setString(1,"robert bro");
            stmt1.setInt(2,2);
            stmt1.executeUpdate();
            System.out.println("Data updated successFul !!!");
            stmt.close();
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}