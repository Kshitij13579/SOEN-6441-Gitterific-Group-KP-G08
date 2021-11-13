// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Concordia/SOEN 6441 W 2212/Project/SOEN-6441-Gitterific-Group-KP-G08/conf/routes
// @DATE:Sat Nov 13 15:52:00 EST 2021

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def topics(topic:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "topics/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("topic", topic)))
    }
  
    // @LINE:8
    def search(query:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "search" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("query", query)))))
    }
  
    // @LINE:10
    def user_profile(username:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)))
    }
  
    // @LINE:11
    def user_repository(username:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)) + "/repos")
    }
  
    // @LINE:9
    def commits(user:String, repository:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "search/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("user", user)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("repository", repository)) + "/commits")
    }
  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
