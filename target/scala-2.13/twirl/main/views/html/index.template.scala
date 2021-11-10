
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[List[model.Repository],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(repos: List[model.Repository]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.33*/("""
"""),format.raw/*2.1*/("""<html>
<style>
table, th, td """),format.raw/*4.15*/("""{"""),format.raw/*4.16*/("""
  """),format.raw/*5.3*/("""border:1px solid black;
"""),format.raw/*6.1*/("""}"""),format.raw/*6.2*/("""
"""),format.raw/*7.1*/("""</style>
<center><h2 class="center">Gitterific</h2></center>
<body>
	<center>
		<form method="GET" action="fetch">
		 Search : <input type="text" name="query"></input>
		 <input type="submit" value="Go"></input>
		</form>
	</center>
	<center>
	    <table>
	         <tr>
			    <th>User</th>
			    <th>Repository</th>
			    <th>Issues</th>
			    <th>Topics</th>
			    <th>Commit Statistics</th>
             </tr>
	    """),_display_(/*25.7*/for(repo <- repos) yield /*25.25*/{_display_(Seq[Any](format.raw/*25.26*/("""
	      """),format.raw/*26.8*/("""<tr>
			    <td>"""),_display_(/*27.13*/repo/*27.17*/.login),format.raw/*27.23*/("""</td>
			    <td>"""),_display_(/*28.13*/repo/*28.17*/.name),format.raw/*28.22*/("""</td>
			    <td>"""),_display_(/*29.13*/repo/*29.17*/.issues_url),format.raw/*29.28*/("""</td>
			    <td>Topic</td>
			    <td><a href="""),_display_(/*31.21*/repo/*31.25*/.commits_url),format.raw/*31.37*/(""">Link</a></td>
          </tr>
	    """)))}),format.raw/*33.7*/("""
	   """),format.raw/*34.5*/("""</table>
	</center>
</body>
</html>"""))
      }
    }
  }

  def render(repos:List[model.Repository]): play.twirl.api.HtmlFormat.Appendable = apply(repos)

  def f:((List[model.Repository]) => play.twirl.api.HtmlFormat.Appendable) = (repos) => apply(repos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-11-07T22:22:32.290023900
                  SOURCE: D:/University/Concordia University/Fall 2021/APP/APP_Project/SOEN-6441-Gitterific-Group-KP-G08/app/views/index.scala.html
                  HASH: 31afb380a480767fd9f23ee68baf5fb0d517b655
                  MATRIX: 964->1|1090->32|1118->34|1176->65|1204->66|1234->70|1285->95|1312->96|1340->98|1808->540|1842->558|1881->559|1917->568|1962->586|1975->590|2002->596|2048->615|2061->619|2087->624|2133->643|2146->647|2178->658|2255->708|2268->712|2301->724|2370->763|2403->769
                  LINES: 28->1|33->1|34->2|36->4|36->4|37->5|38->6|38->6|39->7|57->25|57->25|57->25|58->26|59->27|59->27|59->27|60->28|60->28|60->28|61->29|61->29|61->29|63->31|63->31|63->31|65->33|66->34
                  -- GENERATED --
              */
          