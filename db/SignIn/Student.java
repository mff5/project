package SignIn;

public class Student {
    private String sd_num;
    private String name;
    private String sd_id;
    private String sd_pw;

    public Student(String sd_num) {
        this.sd_num = sd_num;
    }

    public Student(String sd_num, String name, String sd_id, String sd_pw) {
        this.sd_num = sd_num;
        this.name = name;
        this.sd_id = sd_id;
        this.sd_pw = sd_pw;
    }

    public String getSd_num() {
        return sd_num;
    }

    public void setSd_num(String sd_num) {
        this.sd_num = sd_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSd_id() {
        return sd_id;
    }

    public void setSd_id(String sd_id) {
        this.sd_id = sd_id;
    }

    public String getSd_pw() {
        return sd_pw;
    }

    public void setSd_pw(String sd_pw) {
        this.sd_pw = sd_pw;
    }

    @Override
    public String toString() {
        return "학번: "+sd_num+"\t이름: "+name+"\t아이디: "+sd_id+"\t비밀번호: "+sd_pw;
    }
}
