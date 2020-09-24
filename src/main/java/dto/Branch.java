package dto;

public class Branch {
	
	private String name; 
	private Commit commit;
	private boolean _protected;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}
	public boolean is_protected() {
		return _protected;
	}
	public void set_protected(boolean _protected) {
		this._protected = _protected;
	}
}
