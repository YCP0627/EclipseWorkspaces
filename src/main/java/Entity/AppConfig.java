package Entity;

public enum  AppConfig {
    MYSQL_URL("jdbc:mysql://127.0.0.1:3306/java_experience?useUnicode=true&characterEncoding=utf8"),
    MYSQL_USER("root"),
    MYSQL_PASSWORD("ycp92742520"),
    MYSQL_DRIVER("com.mysql.cj.jdbc.Driver");


    private String value;

    AppConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
