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
  
public class CommitTest{
    @Mock
    Commit commit;
    
    @Before
    public void setup() {
    	commit = mock(Commit.class);
    }
    
    @Test
    public void testSetters() {
    	
    	commit.setAuthor("Kshitij");
    	commit.setAdditions(10);
    	commit.setDeletions(2);
    	commit.setSha("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");
    	
    	verify(commit).setAuthor("Kshitij");
    	verify(commit).setAdditions(10);
    	verify(commit).setDeletions(2);
    	verify(commit).setSha("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");
    	
    }
    
    @Test
    public void testGetters() {
    	
    	Commit c = new Commit();
    	c.setAuthor("Kshitij");
    	c.setAdditions(10);
    	c.setDeletions(2);
    	c.setSha("c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");
    	
    	assertEquals("Kshitij", c.getAuthor());
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
