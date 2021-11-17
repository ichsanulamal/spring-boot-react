package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.UserModel;

public interface UserService {
//    UserModel addUser(UserModel user);
    String encrypt(String password);

    //
    String addUser(UserModel user);
    UserModel findUserByNama(String username);

    String changePassword(UserModel user, String password);
}
