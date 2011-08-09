import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.exception.NoSuchLinkException;
import src.exception.NoSuchTagException;

public class NoteTest {
	public static Note n = null;
	public static Note link1 = null;
	public static Note link2 = null;
	public static Note link3 = null;
	public static Tag tag2 = null;
	public static Notebook nb1 = new Notebook();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		n = createNote("TestNote1", "TestTag1");
		link1 = createNote("TestLink1", null);
		link2 = createNote("TestLink2", null);
		link3 = createNote("TestLink3", null);
		tag2 = createTag("TestTag2");		
		n.addTag(tag2);

	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testAddNote() {
		assertTrue(n.getTags().size() == 2);
		assertTrue(n.getTags().get(0).getTag().equals("TestTag1"));

	}

	@Test
	public void testAddTag() {
		n.addTag(createTag("TestTag3"));
		assertTrue(n.getTags().size() == 3);
		assertTrue(nb1.getTags().size()== 3);
	}

	@Test
	public void testAddLink() {
		n.addLink(link1);
		assertTrue(n.getLinksTo().size() == 1);
		assertTrue(n.getLinksTo().contains(link1));
		assertEquals(n.getLinksTo().get(0).getTitle(), "TestLink1");

		assertTrue(link1.getLinksFrom().contains(n));

		link2.addLink(n);
		assertEquals(n.getLinksFrom().size(), 1);
		assertTrue(n.getLinksFrom().contains(link2));

		assertTrue(link2.getLinksTo().contains(n));
	}

	@Test
	public void testRemoveLink() {

		n.addLink(link3);
		assertTrue(n.getLinksTo().size() == 2);
		assertTrue(n.getLinksTo().contains(link3));

		assertTrue(link3.getLinksFrom().contains(n));

		try {
			n.removeLink(link1);
		} catch (NoSuchLinkException e) {
			e.printStackTrace();
		}

		assertTrue(n.getLinksTo().size() == 1);
		assertTrue(n.getLinksTo().contains(link3));

		assertTrue(!link1.getLinksFrom().contains(n));

	}

	@Test
	public void testRemoveTag() {
		try {
			n.removeTag(tag2);
		} catch (NoSuchTagException e) {			
			e.printStackTrace();
		}
		assertTrue(n.getTags().size() == 2);
		assertTrue(nb1.getTags().size()== 2);
	}
	
	
	@Test(expected= src.exception.NoSuchTagException.class)
	public void testNoSuchTagException() throws NoSuchTagException {
		Tag exceptionTag = createTag("TestTagException");		
		n.removeTag(exceptionTag);
		
	}
	
	@Test(expected= src.exception.NoSuchLinkException.class)
	public void testNoSuchLinkException() throws NoSuchLinkException {
		Note exceptionLink = createNote("TestLinkException", null);		
		n.removeLink(exceptionLink);
		
	}
	
	private static Note createNote(String note, String tag) {
		Note n = new Note();
		n.setTitle(note);
		n.setNotebookRef(nb1);
		if (tag != null) {
			Tag t = createTag(tag);
			n.addTag(t);
		}
		return n;
	}

	private static Tag createTag(String tag) {
		Tag t = new Tag();
		t.setTag(tag);
		return t;
	}
}
