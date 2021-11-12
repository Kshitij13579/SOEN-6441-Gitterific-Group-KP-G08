
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[List[String],Form[UrlParam],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(tasks: List[String],urlForm : Form[UrlParam]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*2.2*/import helper._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*3.1*/("""<html>
<center><h2 class="center">Gitterific</h2></center>
<body>
	<center>
		<form method="GET" action="save">
		 Search : <input type="text" name="query"></input>
		 <input type="submit" value="Go"></input>
		</form>
	</center>
	<center><ul>
	    """),_display_(/*13.7*/for(task <- tasks) yield /*13.25*/{_display_(Seq[Any](format.raw/*13.26*/("""
	      """),format.raw/*14.8*/("""<li>"""),_display_(/*14.13*/task),format.raw/*14.17*/("""</li>
	    """)))}),format.raw/*15.7*/("""
	"""),format.raw/*16.2*/("""</ul></center>
</body>
</html>"""))
      }
    }
  }

  def render(tasks:List[String],urlForm:Form[UrlParam]): play.twirl.api.HtmlFormat.Appendable = apply(tasks,urlForm)

  def f:((List[String],Form[UrlParam]) => play.twirl.api.HtmlFormat.Appendable) = (tasks,urlForm) => apply(tasks,urlForm)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-11-06T15:20:43.914826600
                  SOURCE: D:/University/Concordia University/Fall 2021/APP/APP_Project/SOEN-6441-Gitterific-Group-KP-G08/app/views/index.scala.html
                  HASH: 7d05c6f792a63ab360c97e90db1029c1ec39144d
                  MATRIX: 969->1|1088->50|1133->47|1161->67|1447->327|1481->345|1520->346|1556->355|1588->360|1613->364|1656->377|1686->380
                  LINES: 28->1|31->2|34->1|35->3|45->13|45->13|45->13|46->14|46->14|46->14|47->15|48->16
                  -- GENERATED --
              */
          