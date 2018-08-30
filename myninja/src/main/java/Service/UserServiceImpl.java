package Service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import controllers.ApplicationController;
import entity.User;
import ninja.Results;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    Provider<EntityManager> entitiyManagerProvider;

    @Transactional
    public void updateData(User user) {
        try {
            EntityManager entityManager = entitiyManagerProvider.get();
            entityManager.persist(user);
        } catch (Exception e){
            System.out.println("Failed to Update Data" + e);
        }
    }

    @UnitOfWork
    public boolean checkIfUserExist(User user) {
       // System.out.println(user.getUserId() + "   " + user.getPassword());
        EntityManager entityManager = entitiyManagerProvider.get();
        List<?> users = entityManager.createQuery("from User where user_id = ?1 and password = ?2").setParameter(1, user.getUserId()).setParameter(2, user.getPassword()).getResultList();
        return (users.size() > 0) ? true : false;
    }

    @Transactional
    public void deleteData(User user){
        try {
            EntityManager entityManager = entitiyManagerProvider.get();
            Query query = entityManager.createQuery("Delete from User where user_id = ?1 and password = ?2").setParameter(1,user.getUserId()).setParameter(2,user.getPassword());
            query.executeUpdate();
            } catch (Exception e){
             System.out.println("Failed to delete" + e);
           }
    }

    @Transactional
    public void deleteUsr(String id){
        System.out.println(id);
        try {
            System.out.println(id);
            EntityManager entityManager = entitiyManagerProvider.get();
            Query query = entityManager.createQuery("Delete from User where id = ?1").setParameter(1,id);
            query.executeUpdate();
        } catch (Exception e){
            System.out.println("Failed to delete" + e);
        }
    }
}