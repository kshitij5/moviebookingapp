package com.moviebookingapp.techacadeemy.payload.response;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private List<String> roles;

  public UserInfoResponse(String id, String firstName,String lastName, String email, List<String> roles) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
  }

}