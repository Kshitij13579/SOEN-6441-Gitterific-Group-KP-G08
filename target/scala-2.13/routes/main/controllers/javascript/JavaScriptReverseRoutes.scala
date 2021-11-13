// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Concordia/SOEN 6441 W 2212/Project/SOEN-6441-Gitterific-Group-KP-G08/conf/routes
// @DATE:Sat Nov 13 15:52:00 EST 2021

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def topics: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.topics",
      """
        function(topic0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "topics/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("topic", topic0))})
        }
      """
    )
  
    // @LINE:8
    def search: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.search",
      """
        function(query0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "search" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("query", query0)])})
        }
      """
    )
  
    // @LINE:10
    def user_profile: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.user_profile",
      """
        function(username0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0))})
        }
      """
    )
  
    // @LINE:11
    def user_repository: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.user_repository",
      """
        function(username0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0)) + "/repos"})
        }
      """
    )
  
    // @LINE:9
    def commits: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.commits",
      """
        function(user0,repository1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "search/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("user", user0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("repository", repository1)) + "/commits"})
        }
      """
    )
  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
