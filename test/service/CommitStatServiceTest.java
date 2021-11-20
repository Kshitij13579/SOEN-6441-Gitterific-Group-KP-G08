package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Author;
import model.Commit;
import play.libs.Json;

/**
 * Test class for Commit Statistics Service
 * @author Kshitij Yerande
 *@version 1.0
 *@since 2021-11-20
 */
public class CommitStatServiceTest {
    
	CommitStatService commStat = new CommitStatService();
	
	Commit commit;
	List<Commit> commitList = new ArrayList<Commit>();
	
	Author jack,chang, rin, mary, joe, ong, lis, hema, mina, jia, rui,lui,jim,tim,henry;
	
	@Before
	public void setup() {
		 jack    = new Author("jack", "jackgit", 5);
		 chang   = new Author("chang", "changgit", 3);
		 rin     = new Author("rin", "ringit", 2);
		 mary    = new Author("mary", "marygit", 0);
		 joe     = new Author("joe", "joegit", 0);
		 ong     = new Author("ong", "onggit", 1);
		 lis     = new Author("lis", "lisgit", 1);
		 hema    = new Author("hema", "hemagit", 1);
		 mina    = new Author("mina", "minagit", 1);
		 jia     = new Author("jia", "jiagit", 1);
		 rui     = new Author("rui", "ruigit", 1);
		 lui     = new Author("lui", "luigit", 2);
		 jim     = new Author("jim", "jimgit", 0);
		 tim     = new Author("tim", "timgit", 0);
		 henry   = new Author("henry", "henrygit", 0);
		
		commitList.add(new Commit(jack, "soen", 50, 20));
		commitList.add(new Commit(jack, "soen", 10, 40));
		commitList.add(new Commit(jack, "soen", 10, 20));
		commitList.add(new Commit(jack, "soen", 10, 20));
		commitList.add(new Commit(jack, "soen", 10, 20));
		commitList.add(new Commit(chang, "soen", 3, 6));
		commitList.add(new Commit(chang, "soen", 6, 3));
		commitList.add(new Commit(chang, "soen", 12, 9));
		commitList.add(new Commit(rin, "soen", 15, 10));
		commitList.add(new Commit(rin, "soen", 5, 10));
		commitList.add(new Commit(ong, "soen", 6, 18));
		commitList.add(new Commit(lis, "soen", 8, 17));
		commitList.add(new Commit(hema, "soen", 4, 36));
		commitList.add(new Commit(mina, "soen", 9, 90));
		commitList.add(new Commit(jia, "soen", 8, 20));
		commitList.add(new Commit(rui, "soen", 6, 34));
		commitList.add(new Commit(lui, "soen", 2, 78));
		commitList.add(new Commit(lui, "soen", 1, 100));
	}
	 
	/**
	 * Test to validate shaList from list of commits
	 */
	@Test
	public void getShaListTest() {
		String jsonString = "[\n{\n\"sha\" : \"abcdefg#1234\"\n},\n {\n\"sha\" : \"abcdefg#1235\"\n}, \n {\n\"sha\" : \"abcdefg#1236\"\n}\n]";
		JsonNode json= Json.parse(jsonString);
		
		String nullJsonString = "[{\"bha\" : \"abcdefg#1234\"},\n {\"abc\" : \"abcdefg#1235\"},\n {\"xyz\" : \"abcdefg#1236}\"}]";
		JsonNode nullJson= Json.parse(nullJsonString);
		
		
		List<String> expected_shaList = new ArrayList<String>();
		expected_shaList.add("abcdefg#1234");
		expected_shaList.add("abcdefg#1235");
		expected_shaList.add("abcdefg#1236");

		assertTrue(commStat.getShaList(json).containsAll(expected_shaList));
		assertTrue(commStat.getShaList(nullJson).isEmpty());
		
	}
	
	/**
	 * Test to validate maximum additions from list of commits.
	 */
	@Test
	public void getMaxAdditionTest() {
		assertEquals(commStat.getMaxAddition(commitList), 50);
	}
	
	/**
	 * Test to validate maximum deletions from list of commits.
	 */
	@Test
	public void getMaxDeletionTest() {
		assertEquals(commStat.getMaxDeletion(commitList), 100);
	}
	
	/**
	 * Test to validate minimum additions from list of commits.
	 */
	@Test
	public void getMinAdditionTest() {
		assertEquals(commStat.getMinAddition(commitList), 1);
	}
	
    /**
     * Test to validate minimum deletions from list of commits.
     */
	@Test
	public void getMinDeletionTest() {
		assertEquals(commStat.getMinDeletion(commitList), 3);
	}
	
	/**
	 * Test to validate average additions from list of commits.
	 */
	@Test
	public void getAvgAdditionTest() {
		assertEquals(commStat.getAvgAddition(commitList), 9.7223,4);
	}
	
	/**
	 * Test to validate average dletions from list of commits.
	 */
	@Test
	public void getAvgDeletionTest() {
		assertEquals(commStat.getAvgDeletion(commitList), 30.6112,4);
	}
	
	/**
	 * Test to validate top 10 committers from list of commits.
	 */
	@Test
	public void getTopCommitterListTest() {
		
		List<Author> expectedTopCommitterList = new ArrayList<Author>();
		expectedTopCommitterList.add(jack);
		expectedTopCommitterList.add(chang);
		expectedTopCommitterList.add(rin);
		expectedTopCommitterList.add(ong);
		expectedTopCommitterList.add(lis);
		expectedTopCommitterList.add(hema);
		expectedTopCommitterList.add(mina);
		expectedTopCommitterList.add(jia);
		expectedTopCommitterList.add(rui);
		expectedTopCommitterList.add(lui);
		
		assertTrue(EqualsBuilder.reflectionEquals(commStat.getTopCommitterList(commitList), expectedTopCommitterList));
	
    }
	
	/**
	 * Test to validate number of commits per Author from list of commits.
	 */
	@Test
	public void getCommitsByAuthorTest() {
		assertEquals(commStat.getCommitsByAuthor("jack", commitList),5);
		assertEquals(commStat.getCommitsByAuthor("jim", commitList), 0);
	}
	
}
