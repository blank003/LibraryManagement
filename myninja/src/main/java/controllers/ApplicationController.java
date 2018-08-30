/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import Service.BookService;
import Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provider;
import javafx.scene.control.Alert;
import ninja.Result;
import ninja.Results;
import ninja.FilterWith;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;
import ninja.session.Session;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import filter.LoginFilter;
import entity.User;
import entity.Book;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;


@Singleton
public class ApplicationController {

    @Inject
    Provider<EntityManager> entitiyManagerProvider;
    @Inject
    private UserService userService;
    @Inject
    private BookService bookService;

    @FilterWith(LoginFilter.class)
    public Result login() {
        return Results.html();
    }

    public Result homePage() {
        return Results.html().template("/views/ApplicationController/Welcome.ftl.html");
    }

    public Result AdminPage() {
        return Results.html().template("/views/ApplicationController/admin.ftl.html");
    }

    public Result temp() {
        return Results.html().template("/views/ApplicationController/temp.ftl.html");
    }

    public Result register() {
        return Results.html().template("/views/ApplicationController/register.ftl.html");
    }

    public Result Books() {
        return Results.html().template("/views/ApplicationController/Books.ftl.html");
    }

    public Result userDB() {
        return Results.html().template("/views/ApplicationController/userDB.ftl.html");
    }

    public Result userbookDB(Session session) {
        return Results.html().template("/views/ApplicationController/userbookDB.ftl.html");
    }

    public Result Addbook() {
        return Results.html().template("/views/ApplicationController/Addbook.ftl.html");
    }

    public Result performLogin(User user, Session session) {
        String userId = user.getUserId();
        String pwd = user.getPassword();
        String adm = "Admin";
        if (userService.checkIfUserExist(user)) {
            if (userId.equals(adm)) {
                return Results.html().template("/views/ApplicationController/admin.ftl.html");
            }
            session.put("userId", userId);
            return Results.html().template("/views/ApplicationController/Welcome.ftl.html");

        } else {
            session.clear();
        }
        return Results.html().template("/views/ApplicationController/result.ftl.html").render("msg", "Invalid user, please login again ");
    }

    public Result getName(Session session) {
        String uname = session.get("userId");
        return Results.json().render(uname);
    }

    public Result performSignup(User user) {
        String userId = user.getUserId();
        String pwd = user.getPassword();
        System.out.println(userId);
        if (StringUtils.isEmpty(userId)) {
            return Results.html().template("/views/ApplicationController/result.ftl.html").render("msg", "Invalid user, please login again ");
        }
        userService.updateData(user);
        return Results.html().template("/views/ApplicationController/result.ftl.html").render("msg", "Welcome: " + user.getUserId() + "You have sucessfully registered.!!");
    }


    public Result performDelete(User user) {
        userService.deleteData(user);
        return Results.html().template("/views/ApplicationController/result.ftl.html").render("msg", "User: " + user.getUserId() + " Deleted");
    }

    public Result doBookEntry(Book book) {
        bookService.updateDatabase(book);
        return Results.html().template("/views/ApplicationController/Addbook.ftl.html");
    }

    public Result deleteBook(@PathParam("id") String id) {
        System.out.println(id);
        bookService.deleteData(id);

        return Results.json().render("{deletion: success}");
    }

    public Result changeAvail(@PathParam("avail") String Avail, @PathParam("id") String id) {
        bookService.changeAvail(Avail, id);
        return Results.json().render("{changeAvail: success}");
    }

    public Result deleteUser(@PathParam("id") String id) {
        System.out.println(id);
        userService.deleteUsr(id);
        return Results.json().render("{deletion: success}");
    }

    @UnitOfWork
    public Result showAll() {
        EntityManager entityManager = entitiyManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM User x");
        List<User> guestbookEntries = (List<User>) q.getResultList();
        for (User u : guestbookEntries)
            System.out.println("FOUND:" + u);
        //return guestbookEntries;
        return Results.json().render(guestbookEntries);

    }

    @UnitOfWork
    public Result showAllBooks() {
        EntityManager entityManager = entitiyManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM Book x");
        List<Book> bookEntries = (List<Book>) q.getResultList();
        for (Book b : bookEntries)
            System.out.println("FOUND:" + b.getBookAvail());
        //return guestbookEntries;
        return Results.json().render(bookEntries);
    }
}