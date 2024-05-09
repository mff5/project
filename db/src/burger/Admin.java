package burger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Admin {
    private String id;
    private String password;
    public Admin() throws Exception {
        String filePath = "D:/study/db/src/burger/burger.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(filePath));

        id = properties.getProperty("adminId");
        password = properties.getProperty("adminPassword");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password);
    }

    @Override
    public String toString() {
        return "아이디: " +getId()+"\t비밀번호: " +getPassword();
    }
}
