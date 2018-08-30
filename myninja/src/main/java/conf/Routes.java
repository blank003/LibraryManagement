/**
 * Copyright (C) 2012 the original author or authors.
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

package conf;


import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        router.GET().route("/").with(ApplicationController.class, "login");
        router.POST().route("/doLogin").with(ApplicationController.class, "performLogin");
        router.POST().route("/doSignup").with(ApplicationController.class, "performSignup");
        router.POST().route("/doDelete").with(ApplicationController.class, "performDelete");
        router.GET().route("/register.ftl.html").with(ApplicationController.class,"register");
        router.GET().route("/Books.ftl.html").with(ApplicationController.class,"Books");
        router.GET().route("/Welcome.ftl.html").with(ApplicationController.class,"homePage");
        router.GET().route("/showAll").with(ApplicationController.class,"showAll");
        router.GET().route("/temp").with(ApplicationController.class,"temp");
        router.GET().route("/showAllB").with(ApplicationController.class,"showAllBooks");
        router.GET().route("/admin.ftl.html").with(ApplicationController.class,"AdminPage");
        router.GET().route("/userDB").with(ApplicationController.class,"userDB");
        router.GET().route("/userbookDB").with(ApplicationController.class,"userbookDB");
        router.GET().route("/deleteBook/{id}").with(ApplicationController.class,"deleteBook");
        router.GET().route("/deleteBook/{avail}/{id}").with(ApplicationController.class,"changeAvail");
        router.GET().route("/deleteUser/{id}").with(ApplicationController.class,"deleteUser");
        router.GET().route("/Addbook").with(ApplicationController.class,"Addbook");
        router.POST().route("/doBookEntry").with(ApplicationController.class,"doBookEntry");
        router.GET().route("/session").with(ApplicationController.class,"getName");
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
       // router.GET().route("/.*").with(ApplicationController.class, "login");
    }

}
