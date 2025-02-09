package bookapi.entity;

public class Teacher {

	private int tid;
	private String tname;
	private String tsubject;
	
	public Teacher(int tid, String tname, String tsubject) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tsubject = tsubject;
	}
	public Teacher() {
		super();
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTsubject() {
		return tsubject;
	}
	public void setTsubject(String tsubject) {
		this.tsubject = tsubject;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", tsubject=" + tsubject + "]";
	}
	
	
}
