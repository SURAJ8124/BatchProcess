import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;


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


        int userId=1;
        String userName="Harry";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FacebookApi", "root", "root@123");
            Statement stmt = con.createStatement();
            Statement statement = null;
            statement = con.createStatement();
            String sql = "SELECT * FROM Users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int faceBookId = resultSet.getInt("faceBookId");
                System.out.println(name + ", " + faceBookId);
            }


            String sql1 = "UPDATE Users set name=? where faceBookId=?";
            PreparedStatement stmt1 = con.prepareStatement(sql1);
            stmt1.setString(1, userName);
            stmt1.setInt(2, userId);
            stmt1.executeUpdate();
            System.out.println("Data updated successFul !!!");
            stmt.close();
            resultSet.close();
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}