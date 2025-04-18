package com.example.library_api.request;


public class LoginRequestDTO {
    
    private String mail;
    private String password;
    

    // Constructors
    public LoginRequestDTO() {}

    public LoginRequestDTO(String mail, String password) {
        
    }

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    
}
