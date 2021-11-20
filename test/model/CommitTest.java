package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import play.Application;
import play.test.WithApplication;
  
/**
 * Test for Commit Model.
 * @author Kshitij Yerande
 *@version 1.0
 *@since 2021-11-20
 */
public class CommitTest{
    @Mock
    Commit commit;
    
    @Before
    public void setup() {
    	commit = mock(Commit.class);
    }
    
    /**
	 * Test for setter.
	 * Setter - check that the values are set in Commit object.
	 */
    @Test
    public void testSetters() {
    	Author a = new Author("Kshitij","kyerande",100);
    	commit.setAuthor(a);
    	commit.setAdditions(10);
    	commit.setDeletions(2);
    	commit.setSha("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");
    	
    	verify(commit).setAuthor(a);
    	verify(commit).setAdditions(10);
    	verify(commit).setDeletions(2);
    	verify(commit).setSha("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");
    	
    }
    
    /**
	 * Test for getters
	 * Getter - validate the variable values of Commit object
	 */
    @Test
    public void testGetters() {
    	
    	Commit c = new Commit();
    	c.setAuthor(new Author("Kshitij","kyerande",100));
    	c.setAdditions(10);
    	c.setDeletions(2);
    	c.setSha("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");
    	
    	assertEquals("Kshitij", c.getAuthor().getName());
    	assertEquals("kyerande", c.getAuthor().getLogin());
    	assertEquals(100, c.getAuthor().getCommits());
    	assertEquals(10, c.getAdditions());
    	assertEquals(2, c.getDeletions());
    	assertEquals("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc", c.getSha());
    	
    	//null test
    	c.setAuthor(null);
    	c.setSha(null);
    	
    	assertNull(c.getAuthor());
    	assertNull(c.getSha());
    	
    }
}
