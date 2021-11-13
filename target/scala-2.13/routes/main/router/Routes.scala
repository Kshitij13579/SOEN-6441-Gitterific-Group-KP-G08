// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Concordia/SOEN 6441 W 2212/Project/SOEN-6441-Gitterific-Group-KP-G08/conf/routes
// @DATE:Sat Nov 13 15:52:00 EST 2021

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_0: controllers.HomeController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_0: controllers.HomeController
  ) = this(errorHandler, HomeController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """search""", """controllers.HomeController.search(query:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """search/""" + "$" + """user<[^/]+>/""" + "$" + """repository<[^/]+>/commits""", """controllers.HomeController.commits(user:String, repository:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/""" + "$" + """username<[^/]+>""", """controllers.HomeController.user_profile(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/""" + "$" + """username<[^/]+>/repos""", """controllers.HomeController.user_repository(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """topics/""" + "$" + """topic<[^/]+>""", """controllers.HomeController.topics(topic:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_search1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("search")))
  )
  private[this] lazy val controllers_HomeController_search1_invoker = createInvoker(
    HomeController_0.search(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "search",
      Seq(classOf[String]),
      "GET",
      this.prefix + """search""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_commits2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("search/"), DynamicPart("user", """[^/]+""",true), StaticPart("/"), DynamicPart("repository", """[^/]+""",true), StaticPart("/commits")))
  )
  private[this] lazy val controllers_HomeController_commits2_invoker = createInvoker(
    HomeController_0.commits(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "commits",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """search/""" + "$" + """user<[^/]+>/""" + "$" + """repository<[^/]+>/commits""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_user_profile3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_user_profile3_invoker = createInvoker(
    HomeController_0.user_profile(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "user_profile",
      Seq(classOf[String]),
      "GET",
      this.prefix + """users/""" + "$" + """username<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_user_repository4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/"), DynamicPart("username", """[^/]+""",true), StaticPart("/repos")))
  )
  private[this] lazy val controllers_HomeController_user_repository4_invoker = createInvoker(
    HomeController_0.user_repository(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "user_repository",
      Seq(classOf[String]),
      "GET",
      this.prefix + """users/""" + "$" + """username<[^/]+>/repos""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_HomeController_topics5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("topics/"), DynamicPart("topic", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_topics5_invoker = createInvoker(
    HomeController_0.topics(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "topics",
      Seq(classOf[String]),
      "GET",
      this.prefix + """topics/""" + "$" + """topic<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:8
    case controllers_HomeController_search1_route(params@_) =>
      call(params.fromQuery[String]("query", None)) { (query) =>
        controllers_HomeController_search1_invoker.call(HomeController_0.search(query))
      }
  
    // @LINE:9
    case controllers_HomeController_commits2_route(params@_) =>
      call(params.fromPath[String]("user", None), params.fromPath[String]("repository", None)) { (user, repository) =>
        controllers_HomeController_commits2_invoker.call(HomeController_0.commits(user, repository))
      }
  
    // @LINE:10
    case controllers_HomeController_user_profile3_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_HomeController_user_profile3_invoker.call(HomeController_0.user_profile(username))
      }
  
    // @LINE:11
    case controllers_HomeController_user_repository4_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_HomeController_user_repository4_invoker.call(HomeController_0.user_repository(username))
      }
  
    // @LINE:12
    case controllers_HomeController_topics5_route(params@_) =>
      call(params.fromPath[String]("topic", None)) { (topic) =>
        controllers_HomeController_topics5_invoker.call(HomeController_0.topics(topic))
      }
  }
}
