import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import src.exception.NoSuchLinkException;
import src.exception.NoSuchNoteException;
import src.exception.NoSuchTagException;

public class Notebook {
	private List<Note> notes = new ArrayList<Note>();
	private List<Tag> tags = new ArrayList<Tag>();

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void addNote(Note n) {
		if (!notes.contains(n)) {
			notes.add(n);
			n.setNotebookRef(this);
		}
	}

	public void removeNote(Note n) throws NoSuchNoteException, NoSuchLinkException
			 {
		if (notes.contains(n)) {
			for (Note nt : notes) {	
				List<Note> linksTo = nt.getLinksTo();
				List<Note> linksFrom = nt.getLinksFrom();
				if (linksTo.contains(n))
					linksTo.remove(n);
				if (linksFrom.contains(n))
					linksFrom .remove(n);
			}
			notes.remove(n);
			
		} else {
			throw new NoSuchNoteException("Note " + n.getTitle()
					+ " cannot be found ");
		}
	}

	public void addTag(Tag t) {
		if (!tags.contains(t)) {
			tags.add(t);
			for (Note nt : notes) {
				nt.addTag(t);
			}
		}
	}

	public void removeTag(Tag t) throws NoSuchTagException {
		if (tags.contains(t)) {
			for (Note nt : notes) {
				try {
					nt.removeTag(t);
				} catch (NoSuchTagException e) {
					e.printStackTrace();
				}
			}
			tags.remove(t);
			
		} else {
			throw new NoSuchTagException("Notes have not been tagged by "
					+ t.getTag());
		}
	}

	public static void saveAs(Object ob, String fileName) {
		try {
			XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(fileName)));
			xmlEncoder.writeObject(ob);
			xmlEncoder.close();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object restoreFrom(String fileName) {
		Object result = null;
		try {
			XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(fileName)));
			result = xmlDecoder.readObject();
			xmlDecoder.close();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
