package views;

import views.html.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Repository;
import play.twirl.api.Content;

public class viewTest {

	@Test
	public void test() {
		
		List<Repository> repoList = new ArrayList<Repository>();	
		
		Content html=views.html.index.render(repoList, "");
		
		assertEquals("text/html",html.contentType().toString());
	}

}
