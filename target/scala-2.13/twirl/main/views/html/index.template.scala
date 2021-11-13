
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
<center>
	<h2>
		Gitterific
	</h2>
</center>
<body>
	<center>
		<form method="GET" action="search">
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
	    """),_display_(/*29.7*/for(repo <- repos) yield /*29.25*/{_display_(Seq[Any](format.raw/*29.26*/("""
	      """),format.raw/*30.8*/("""<tr>
	      		<td><a href=/users/"""),_display_(/*31.30*/repo/*31.34*/.login),format.raw/*31.40*/(""" """),format.raw/*31.41*/("""style="text-decoration: none;">"""),_display_(/*31.73*/repo/*31.77*/.login),format.raw/*31.83*/("""</a>
<!--  			<td>"""),_display_(/*32.15*/repo/*32.19*/.login),format.raw/*32.25*/("""</td> -->
 			    <td>"""),_display_(/*33.14*/repo/*33.18*/.name),format.raw/*33.23*/("""</td>
			    <td>"""),_display_(/*34.13*/repo/*34.17*/.issues_url),format.raw/*34.28*/("""</td>
			    <td>
			    	<table style="border:none">
			    		<tr>
			    		"""),_display_(/*38.11*/for(topic <- repo.topics) yield /*38.36*/ {_display_(Seq[Any](format.raw/*38.38*/("""
			    			"""),format.raw/*39.11*/("""<tr>
			    				<td style="border:none">
						        	<a href="/topics/"""),_display_(/*41.34*/topic),format.raw/*41.39*/("""" style="text-decoration: none;" target="_blank">
						        		"""),_display_(/*42.18*/topic),format.raw/*42.23*/("""
						        	"""),format.raw/*43.16*/("""</a>
			    				</td>
						    </tr>
			    		""")))}),format.raw/*46.11*/("""
					   """),format.raw/*47.9*/("""</tr>
					</table>
			    </td>
			    <td><a href=search/"""),_display_(/*50.28*/repo/*50.32*/.login),format.raw/*50.38*/("""/"""),_display_(/*50.40*/repo/*50.44*/.name),format.raw/*50.49*/("""/commits style="text-decoration: none;" target="_blank">Link</a></td>
          </tr>
	    """)))}),format.raw/*52.7*/("""
	   """),format.raw/*53.5*/("""</table>
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
                  DATE: 2021-11-13T15:52:00.451
                  SOURCE: D:/Concordia/SOEN 6441 W 2212/Project/SOEN-6441-Gitterific-Group-KP-G08/app/views/index.scala.html
                  HASH: f432c60156b022138e1e0278031c4f1c22973f4f
                  MATRIX: 964->1|1090->32|1118->34|1176->65|1204->66|1234->70|1285->95|1312->96|1340->98|1806->538|1840->556|1879->557|1915->566|1977->601|1990->605|2017->611|2046->612|2105->644|2118->648|2145->654|2192->674|2205->678|2232->684|2283->708|2296->712|2322->717|2368->736|2381->740|2413->751|2522->833|2563->858|2603->860|2643->872|2746->948|2772->953|2867->1021|2893->1026|2938->1043|3020->1094|3057->1104|3147->1167|3160->1171|3187->1177|3216->1179|3229->1183|3255->1188|3379->1282|3412->1288
                  LINES: 28->1|33->1|34->2|36->4|36->4|37->5|38->6|38->6|39->7|61->29|61->29|61->29|62->30|63->31|63->31|63->31|63->31|63->31|63->31|63->31|64->32|64->32|64->32|65->33|65->33|65->33|66->34|66->34|66->34|70->38|70->38|70->38|71->39|73->41|73->41|74->42|74->42|75->43|78->46|79->47|82->50|82->50|82->50|82->50|82->50|82->50|84->52|85->53
                  -- GENERATED --
              */
          