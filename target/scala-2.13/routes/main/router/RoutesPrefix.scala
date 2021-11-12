// @GENERATOR:play-routes-compiler
// @SOURCE:D:/University/Concordia University/Fall 2021/APP/APP_Project/SOEN-6441-Gitterific-Group-KP-G08/conf/routes
// @DATE:Sat Nov 06 15:20:43 EDT 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
