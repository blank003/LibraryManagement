package Service;
import entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
     void updateData(User user);

     boolean checkIfUserExist(User user);

     void deleteData(User user);

     void deleteUsr(String id);



    }
