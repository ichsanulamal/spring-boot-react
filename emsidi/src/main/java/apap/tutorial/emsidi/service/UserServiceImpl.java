package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

//    @Override
//    public UserModel addUser(UserModel user) {
//        String pass = encrypt(user.getPassword());
//        user.setPassword(pass);
//        return userDb.save(user);
//    }


    @Override
    public String addUser(UserModel user) {
        String pass = user.getPassword();
        //Reference:
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        if (pass.length() >= 8){
            if(pass.matches(regex)) {
                String password = encrypt(user.getPassword());
                user.setPassword(password);
                userDb.save(user);
                return "User berhasil ditambahkan!";
            }
        }
        return "Password tidak sesuai ketentuan, mohon ulangi";

    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findUserByNama(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public String changePassword(UserModel user, String pass) {
        //Reference:
        //https://stackoverflow.com/questions/11533474/java-how-to-test-if-a-string-contains-both-letter-and-number
        String numRegex   = ".*[0-9].*";
        String alphaRegex = ".*[a-zA-Z].*";

        if (pass.length() >= 8){
            if(pass.matches(numRegex) && pass.matches(alphaRegex)) {
                String password = encrypt(pass);
                user.setPassword(password);
                userDb.save(user);
                return "Password berhasil diubah!";
            }
        }
        return "Password tidak sesuai ketentuan, mohon ulangi";
    }



}
