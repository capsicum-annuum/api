package br.com.annuum.capsicum.api.domain;

public interface HasEncodedPassword {

  String getPassword();

  HasEncodedPassword setPassword(String encodedPassword);

}
