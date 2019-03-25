package stef.projects.console.domain;

public enum UserRole {

    ADMIN("ADMINISTRATOR", 1L),
    USER("USER", 2L);

    private String value;
    private Long numberValue;

    UserRole(String value, Long numberValue){
        this.value = value;
        this.numberValue = numberValue;
    }

    public static String getValue(UserRole userRole){
        return userRole.value;
    }

    public static Long getNumberValue(UserRole userRole){
        return userRole.numberValue;
    }

    public static UserRole reverseValue(String string){
        if (ADMIN.value.equals(string)){
            return ADMIN;
        }else if (USER.value.equals(string)){
            return USER;
        }else {
            return null;
        }
    }
    public static UserRole reverseNumberValue(Long intValue){
        if (ADMIN.numberValue.equals(intValue)){
            return ADMIN;
        }else if (USER.numberValue.equals(intValue)){
            return USER;
        }else {
            return null;
        }
    }

}
