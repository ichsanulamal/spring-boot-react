package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
}
