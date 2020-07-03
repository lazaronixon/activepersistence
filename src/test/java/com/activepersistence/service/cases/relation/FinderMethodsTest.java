package com.activepersistence.service.cases.relation;

import com.activepersistence.IntegrationTest;
import com.activepersistence.service.models.Post;
import com.activepersistence.service.models.PostsService;
import static java.util.Arrays.asList;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.jboss.arquillian.persistence.UsingDataSet;
import static org.junit.Assert.*;
import org.junit.Test;

@UsingDataSet({"posts.xml", "comments.xml"})
public class FinderMethodsTest extends IntegrationTest {

    @Inject
    private PostsService postsService;

    @Test
    public void testTake() {
        assertNotNull(postsService.take());
    }

    @Test
    public void testTakeWithLimit() {
        assertEquals(2, postsService.take(2).size());
    }

    @Test
    public void testTakeOrFail() {
        assertThrows(NoResultException.class, postsService.where("1=0")::takeOrFail);
    }

    @Test
    public void testFirst() {
        assertEquals((Integer) 1, postsService.first().getId());
    }

    @Test
    public void testFirstWithLimit() {
        assertEquals(2, postsService.first(2).size());
    }

    @Test
    public void testLast() {
        assertEquals((Integer) 9999, postsService.last().getId());
    }

    @Test
    public void testLastWithLimit() {
        assertEquals((Integer) 9999, postsService.last(2).get(0).getId());
    }

    @Test
    public void testLastOrFail() {
        assertThrows(NoResultException.class, postsService.where("1=0")::lastOrFail);
    }

    @Test
    public void testFind() {
        assertEquals((Integer) 2, postsService.find(2).getId());
    }

    @Test
    public void testFindMultiple() {
        assertEquals(2 ,postsService.find(asList(1, 2)).size());
    }

    @Test
    public void testFindBy() {
        assertEquals((Integer) 1 ,postsService.findBy("this.title = 'hello world'").getId());
    }

    @Test
    public void testFindByOrFail() {
        assertThrows(NoResultException.class, () -> postsService.findByOrFail("this.title = 'not found'"));
    }

    @Test
    public void testExists() {
        assertTrue(postsService.where("this.title = 'hello world'").exists());
    }

    @Test
    public void testExistsWithParam() {
        assertTrue(postsService.exists("this.title = 'hello world'"));
    }

    @Test
    public void testFindOrCreateBy() {
        long postsCount = postsService.count();
        Post createdPost = postsService.findOrCreateBy("this.title = 'awesome title'",() -> new Post("awesome title", "body", 0));
        assertEquals(postsCount + 1, postsService.count());
        assertEquals("body", createdPost.getBody());
    }

    @Test
    public void testFindOrCreateByNotCreate() {
        long postsCount   = postsService.count();
        Post existentPost = postsService.findOrCreateBy("this.title = 'hello world'",() -> new Post("hello world", "body", 0));
        assertEquals(postsCount, postsService.count());
        assertEquals("My first post", existentPost.getBody());
    }

    @Test
    public void testFindOrGetBy() {
        Post newPost = postsService.findOrGetBy("this.title = 'awesome title'",() -> new Post("awesome title", "body", 0));
        assertEquals("body", newPost.getBody());
    }

    @Test
    public void testFindOrGetByInitialize() {
        Post newPost = postsService.findOrGetBy("this.title = 'hello world'",() -> new Post("hello world", "body", 0));
        assertEquals("My first post", newPost.getBody());
    }

}
