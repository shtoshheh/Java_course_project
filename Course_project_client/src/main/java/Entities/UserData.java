package Entities;

public class UserData {
    protected static Long user_id;
    protected static String login;
    protected static String password;
    protected static String role;

    public UserData() {
    }
    public static void init(User u){
        user_id = u.user_id;
        login = u.login;
        password = u.password;
        role = u.role;
    }

    public static User toUser(){
        User user = new User();
        user.user_id = UserData.user_id;
        user.login = UserData.login;
        user.password = UserData.password;
        user.role = UserData.role;
        return user;
    }

    public static Long getUser_id() {
        return user_id;
    }

    public static void setUser_id(Long user_id) {
        UserData.user_id = user_id;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        UserData.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserData.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserData.role = role;
    }
}