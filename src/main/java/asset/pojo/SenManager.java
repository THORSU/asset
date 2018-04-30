package asset.pojo;

/**
 * Author:QuincySu
 * Date:Created in 2018/4/12
 */
public class SenManager {
    //id
    private String id;
    //姓名
    private String name;
    //密码
    private String password;
    //状态
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
