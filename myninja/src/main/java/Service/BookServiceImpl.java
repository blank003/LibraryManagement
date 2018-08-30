package Service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.google.inject.spi.Toolable;
import controllers.ApplicationController;
import entity.Book;
import ninja.Results;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import com.google.inject.persist.Transactional;

public class BookServiceImpl implements BookService {

    @Inject
    Provider<EntityManager> entitiyManagerProvider;


    @Transactional
    public void updateDatabase(Book book) {
        try {
            EntityManager entityManager = entitiyManagerProvider.get();
            entityManager.persist(book);
        } catch (Exception e) {
            System.out.println("Failed to Update Data" + e);
        }
    }

    @UnitOfWork
    public boolean checkIfBookExist(Book book) {
        // System.out.println(user.getUserId() + "   " + user.getPassword());
        EntityManager entityManager = entitiyManagerProvider.get();
        List<?> books = entityManager.createQuery("from Book where name = ?1").setParameter(1, book.getBookName()).getResultList();
        return (books.size() > 0) ? true : false;
    }

    @Transactional
    public void deleteData(String bookID) {
        System.out.println(bookID);
        try {
            EntityManager entityManager = entitiyManagerProvider.get();
            Query query = entityManager.createQuery("Delete from Book where id = ?1").setParameter(1, bookID);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to delete" + e);
        }
    }

    @Transactional
    public void changeAvail(String Avail, String Id) {
        try {
            EntityManager entityManager = entitiyManagerProvider.get();
            Query query = entityManager.createQuery("UPDATE from Book set bookAvail = ?1 where id=?2").setParameter(1, Avail).setParameter(2, Id);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to delete" + e);
        }
    }
}
