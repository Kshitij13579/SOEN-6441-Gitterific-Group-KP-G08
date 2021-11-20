package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Test for CommitStat Model.
 * @author Kshitij Yerande
 *@version 1.0
 *@since 2020-11-20
 */
public class CommitStatTest {
    
	@InjectMocks
	CommitStat commStat = new CommitStat();
	
	List<Author> top_committers;
	
	@Before
	public void setup() {
		top_committers = new ArrayList<Author>();
		top_committers.add(new Author("Chang","chvin",50));
		top_committers.add(new Author("javaguy","jg",50));
	}
	
	/**
	 * Test for setter.
	 * Setter - check that the values are set in CommitStat object.
	 */
	@Test
	public void testSetters() {
		
		
		
		commStat.setMax_additions(10);
		commStat.setMax_deletions(5);
		commStat.setMin_deletions(10);
		commStat.setMin_additions(10);
		commStat.setAvg_additions(4.7);
		commStat.setAvg_deletions(4.7);
		commStat.setRepository("Java");
		commStat.setTop_committers(top_committers);
		
		assertEquals(commStat.getMax_additions(), 10);
		assertEquals(commStat.getMax_deletions(), 5);
		assertEquals(commStat.getMin_additions(), 10);
		assertEquals(commStat.getMin_deletions(), 10);
		assertEquals(commStat.getAvg_additions(), 4.7,2);
		assertEquals(commStat.getAvg_deletions(), 4.7,2);
		assertEquals(commStat.getRepository(), "Java");
		assertTrue(EqualsBuilder.reflectionEquals(commStat.top_committers, top_committers));
		
	}
	
	/**
	 * Test for getters
	 * Getter - validate the variable values of CommitStat object
	 */
	@Test
	public void testGetters() {
		
		CommitStat com = new CommitStat(top_committers, 10, 5, 10, 5, 10, 5, "Java");
		Author[] expected_top_committers = new Author[] {new Author("Chang","chvin",50),new Author("javaguy","jg",50)};
		Author[] actual_top_committers = new Author[top_committers.size()];
		com.getTop_committers().toArray(actual_top_committers);
		
		assertTrue(Arrays.deepEquals(expected_top_committers, actual_top_committers));
		assertEquals(10.0, com.getAvg_additions(),2);
		assertEquals(5.0, com.getAvg_deletions(),2);
		assertEquals(10, com.getMax_additions(),2);
		assertEquals(5, com.getMax_deletions(),2);
		assertEquals(10, com.getMin_additions(),2);
		assertEquals(5, com.getMin_deletions(),2);
		assertEquals("Java", com.getRepository());
		
	}
}
