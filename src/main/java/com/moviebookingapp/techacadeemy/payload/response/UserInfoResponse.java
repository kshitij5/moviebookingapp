package com.moviebookingapp.techacadeemy.payload.response;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
  private String loginId;
  private String firstName;
  private String lastName;
  private String emailId;
  private String contactNumber;
  private List<String> roles;

  public UserInfoResponse(String id, String firstName,String lastName, String email, List<String> roles) {
    this.loginId = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailId = email;
    this.roles = roles;
  }

}