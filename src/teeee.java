import model.User;

public class teeee {
    public static void main(String[] args) {
        String email = "mirra@gmail.com";
        String password = "123456";
        User user = new User(email, password);
        System.out.println(user.login(email, password));



    }
}
