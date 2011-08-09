import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.exception.NoSuchLinkException;
import src.exception.NoSuchNoteException;
import src.exception.NoSuchTagException;

public class NotebookTest {
	public static Notebook nb1 = new Notebook();
	public static Note n1 = createNote("TestNote1", "TestTag1");
	public static Note n2 = createNote("TestNote2", "TestTag2");
	public static Note n3 = createNote("TestNote3", "TestTag3");
	public static Note link1 = createNote("TestLink1", null);
	public static Note link2 = createNote("TestLink2", null);
	public static Note link3 = createNote("TestLink3", null);
	public static Tag nbTag1 = createTag("Notebook's Tag");
	public static Tag tag4 = createTag("TestTag4");
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb1.addNote(n1);
		nb1.addNote(n1);
		nb1.addNote(n2);
		nb1.addNote(n3);
		nb1.addNote(link1);
		nb1.addNote(link2);
		nb1.addNote(link3);
		
		n1.addLink(link1);
		n2.addLink(link2);
		n3.addLink(link3);
		
		n3.addLink(n1);
		n1.addLink(n2);
		
		n2.addTag(tag4);
		
		nb1.addTag(nbTag1);		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {		

	}

	@org.junit.Test
	public void testSaveAs() throws Exception {
		Notebook.saveAs(nb1, "myNotes.xml");
		assertNotNull(nb1.getNotes());

	}

	@org.junit.Test
	public void testRestoreFrom() throws Exception {
		Notebook n1 = (Notebook) Notebook.restoreFrom("myNotes.xml");
		assertNotNull(n1.getNotes());
		assertEquals(n1.getNotes().size(), 6);
		for (Note n : n1.getNotes()) {
			System.out.println(n.getTitle());
		}

	}

	@Test
	public void testAddTag() {
		nb1.addTag(createTag("TestTag5"));
		assertTrue(nb1.getTags().size() == 6);		
	}
	
	@Test
	public void testAddNote() {
		nb1.addNote(createNote("TestNote4", null));
		assertTrue(nb1.getNotes().size() == 7);		
	}
	
	@Test
	public void testRemoveTag() {
		assertTrue(n2.getTags().size()== 4);
		try {
			nb1.removeTag(tag4);
		} catch (NoSuchTagException e) {			
			e.printStackTrace();
		}
		assertTrue(nb1.getTags().size() == 5);
		assertTrue(n2.getTags().size()== 3);
	}
	
	@Test
	public void testRemoveNote() {
		assertTrue(nb1.getNotes().size()== 7);
		
			try {
				nb1.removeNote(n1);
			} catch (NoSuchLinkException e) {				
				e.printStackTrace();
			} catch (NoSuchNoteException e) {			
				e.printStackTrace();
			}
		assertTrue(nb1.getNotes().size()== 6);		
		assertTrue(link1.getLinksFrom().size() == 0);
		assertTrue(n3.getLinksTo().size()== 1);
	}
	
	@Test(expected= src.exception.NoSuchTagException.class)
	public void testNoSuchTagException() throws NoSuchTagException {
		Tag exceptionTag = createTag("TestTagException");		
		nb1.removeTag(exceptionTag);
		
	}
	
	@Test(expected= src.exception.NoSuchNoteException.class)
	public void testNoSuchNoteException() throws NoSuchNoteException, NoSuchLinkException {
		Note exceptionNote = createNote("TestNoteException", null);		
		nb1.removeNote(exceptionNote);
		
	}
	
	@org.junit.Test
	public void testSaveAs1() throws Exception {
		Notebook.saveAs(nb1, "myNotes1.xml");
		assertNotNull(nb1.getNotes());

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
