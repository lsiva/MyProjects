import java.util.ArrayList;
import java.util.List;

import src.exception.NoSuchLinkException;
import src.exception.NoSuchTagException;


public class Note {
	
	private String title;
	private Notebook nb;
	private List<Tag> tags = new ArrayList<Tag>();
	private List<Note> linksFrom = new ArrayList<Note>();
	private List<Note> linksTo = new ArrayList<Note>();
	
		
	public void setNotebookRef(Notebook nb) {
		this.nb = nb;
	}

	public Notebook getNotebookRef() {
		return nb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public List<Note> getLinksFrom() {
		return linksFrom;
	}

	public void setLinksFrom(List<Note> linksFrom) {
		this.linksFrom = linksFrom;
	}

	public List<Note> getLinksTo() {
		return linksTo;
	}

	public void setLinksTo(List<Note> linksTo) {
		this.linksTo = linksTo;
	}

	public void addTag(Tag t){
		if (! tags.contains(t)){
			tags.add(t);
		}
		List<Tag> nbTags = this.getNotebookRef().getTags();
		if (nbTags != null && ! nbTags.contains(t)){
			nbTags.add(t);
		}
		
		this.getNotebookRef().setTags(nbTags);
	}
	
	public void removeTag(Tag t) throws NoSuchTagException{
		if (tags.contains(t)){
			tags.remove(t);
		} else {
			throw new NoSuchTagException("Note has not been tagged by " + t.getTag());
		}
		
		List<Tag> nbTags = this.getNotebookRef().getTags();
		if (nbTags != null  && nbTags.contains(t)){
			nbTags.remove(t);
		}
		
		this.getNotebookRef().setTags(nbTags);
	}
	
	public void addLink(Note nt){
		this.linksTo.add(nt);		
		nt.linksFrom.add(this);
	}
	
	public void removeLink(Note nt) throws NoSuchLinkException{
		if (linksTo.contains(nt)){
			this.linksTo.remove(nt);
			nt.linksFrom.remove(this);
		}else {
			throw new NoSuchLinkException("Note "+ this.title + "is not linked to  note " + nt.title);
		}
		
	}
	
}
