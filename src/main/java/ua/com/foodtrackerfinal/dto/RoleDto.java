package ua.com.foodtrackerfinal.dto;

public enum RoleDto {
    USER("USER"),
    ADMIN("ADMIN");

    RoleDto(String role) {
    }

    private Long id;
    private String role;

    public String getRole() {
        return name();
    }
}
