package stef.projects.console.domain;

public enum UserRole {

    ADMIN("ADMINISTRATOR"),
    USER("USER");

    private String value;

    UserRole(String value){
        this.value = value;
    }

    public String getValue(UserRole userRole){
        return userRole.value;
    }

    public static UserRole reverseValue(String string){
        if (string==ADMIN.value){
            return ADMIN;
        }else if (string==USER.value){
            return USER;
        }else {
            return null;
        }
    }

}
