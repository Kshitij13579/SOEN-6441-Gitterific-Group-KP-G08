package views;

import views.html.*;
import static org.junit.Assert.*;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import service.IssueStatService;
import org.junit.Test;
import model.Issues;
import model.Repository;
import play.api.http.MediaRange;
import play.i18n.Lang;
import play.libs.typedmap.TypedKey;
import play.libs.typedmap.TypedMap;
import play.mvc.Http;
import play.mvc.Http.Cookie;
import play.mvc.Http.Cookies;
import play.mvc.Http.Headers;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBody;
import play.twirl.api.Content;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.GET;

/**
 * Tests the views of the application
 * 
 * @author Mrinal Rai
 * @version 1.0
 * @since 2021-11-20
 */
public class viewTest {

	/**
	 * Tests the view of the index action in HomeController class 
	 * Tests the content-type and the Title <code>"Gitterific"</code> in the text of the view
	 * 
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Test
	public void testIndexView() {
		
		List<Repository> repoList = new ArrayList<Repository>();
		Http.Request request = new Http.Request() {

			@Override
			public String uri() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String method() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String version() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String remoteAddress() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean secure() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public TypedMap attrs() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String host() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String path() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Lang> acceptLanguages() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<MediaRange> acceptedTypes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean accepts(String mimeType) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Map<String, String[]> queryString() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getQueryString(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<String> queryString(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cookies cookies() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cookie cookie(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<Cookie> getCookie(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Headers getHeaders() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasBody() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Optional<String> contentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<String> charset() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<List<X509Certificate>> clientCertificateChain() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public RequestBody body() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Request withBody(RequestBody body) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Request withAttrs(TypedMap newAttrs) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <A> Request addAttr(TypedKey<A> key, A value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Request removeAttr(TypedKey<?> key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public play.api.mvc.Request<RequestBody> asScala() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		Content html=views.html.index.render(request);
		assertEquals("text/html",html.contentType().toString());
		assertTrue(contentAsString(html).contains("Gitterific"));
		

		html=views.html.commit.render(request);
		assertEquals("text/html",html.contentType().toString());
		
		
		html=views.html.issues.render(request,"test","test");
		assertEquals("text/html",html.contentType().toString());
		
		
		html=views.html.repositories.render(request);
		assertEquals("text/html",html.contentType().toString());
		
		html=views.html.repositoryprofile.render(request,"test","test");
		assertEquals("text/html",html.contentType().toString());
		
		html=views.html.topics.render(request,"play");
		assertEquals("text/html",html.contentType().toString());
		
		html=views.html.users.render(request);
		assertEquals("text/html",html.contentType().toString());
		
	}

}
