package Tuoi2403;

public class Account {
    private String username;
    private String password;
    private String option;

    public Account(String username, String password, String option) {
        this.username = username;
        this.password = password;
        this.option = option;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOption(){
        return option;
    }
}
