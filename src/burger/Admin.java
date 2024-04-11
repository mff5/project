package burger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class Admin {
    private String id;
    private String password;
    public Admin() {
        try(BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {
            id = br.readLine();
            password = br.readLine();
        }catch (Exception e)    {
            System.out.println("admin 오류");
        }
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
