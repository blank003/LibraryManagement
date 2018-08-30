package Service;
import entity.Book;

import java.util.Collection;
import java.util.List;

public interface BookService {
    void updateDatabase(Book book);

    boolean checkIfBookExist(Book book);

    void deleteData(String bookID);

    void changeAvail(String Avail,String id);


}
