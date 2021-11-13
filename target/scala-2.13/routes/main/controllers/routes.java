// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Concordia/SOEN 6441 W 2212/Project/SOEN-6441-Gitterific-Group-KP-G08/conf/routes
// @DATE:Sat Nov 13 15:52:00 EST 2021

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
  }

}
