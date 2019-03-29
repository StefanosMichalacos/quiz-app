package stef.projects.console.domain;

public enum UserRole {

    ADMIN("ADMINISTRATOR", 1L),
    USER("USER", 2L);

    private String value;
    private Long Id;

    UserRole(String value, Long Id){
        this.value = value;
        this.Id = Id;
    }

    public static String getValue(UserRole userRole){
        return userRole.value;
    }

    public static Long getId(UserRole userRole){
        return userRole.Id;
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
    public static UserRole reverseId(Long intValue){
        if (ADMIN.Id.equals(intValue)){
            return ADMIN;
        }else if (USER.Id.equals(intValue)){
            return USER;
        }else {
            return null;
        }
    }

}
