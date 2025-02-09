package bookapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import bookapi.entity.Teacher;

//@Service								// Service work as way as component , but here it is somewhat more readable
@Component
public class TeacherService_withoutDB {

	private static List<Teacher> tlist = new ArrayList<>();

	static {
		tlist.add(new Teacher(101, "Nirbhay Sinha", "Maths"));
		tlist.add(new Teacher(102, "Rahul Sinha", "Science"));
		tlist.add(new Teacher(103, "Vikash Sharma", "Geography"));
		tlist.add(new Teacher(104, "Shyam Kumar", "Chemistry"));
		tlist.add(new Teacher(105, "Priya Mehta", "Physics"));
		tlist.add(new Teacher(106, "Govind Kumar", "History"));
		tlist.add(new Teacher(107, "Shubham Tiwari", "Ecomonics"));
	}

	// Get All Books
	public List<Teacher> getAllTeachers() {
		return tlist;
	}

	// Get single Book by ID
	public Teacher getSingleTeacherById(int id) 
	{
		Teacher teacher = null;
		try 
		{														// findAny()	
			teacher = tlist.stream().filter(t -> t.getTid() == id).findFirst().orElse(null);			// Here just NULL will be returned, without any EXCEPTION. 
//			teacher = tlist.stream().filter(t -> t.getTid() == id).findFirst().get();					// Here Compiler will get EXCEPTION as well.
//			teacher = tlist.stream().filter(t -> t.getTid() == id).collect(Collectors.toList()).get(0);					
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

//        for(Teacher t : tlist) 
//        {
//        	if(t.getTid()==id) {
//        		teacher=t;
//        		break;
//        	}
//        }

		return teacher;
	}

	// Saving a teacher
	public Teacher saveTeacher(Teacher b) 
	{
		try 
		{
			tlist.add(b);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// Deleting a teacher , through its ID
	public List<Teacher> deleteTeacherById(int tid) // Delete and show the rest teachers
	{
		try 
		{
			tlist = tlist.stream().filter(t -> t.getTid() != tid).collect(Collectors.toList());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return tlist;
	}

//	public void deleteTeacherById(int bid)					// Delete but then NOT showing the rest teachers
//	{
//		try 
//		{
//			tlist = tlist.stream().filter(book -> book.getTid()!=bid).collect(Collectors.toList());
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public List<Teacher> updateTeacher(int tid, Teacher updatedTeacher) 
	{
		try 
		{
			tlist = tlist.stream().map(t -> { // map ke "b" aapko Book ka ek-ek obj deta jayega, but it will also expect
												// one obj of same type in return.
				if (t.getTid() == tid) 
				{
					t.setTname(updatedTeacher.getTname());
					t.setTsubject(updatedTeacher.getTsubject());
				}
				return t;
			}).collect(Collectors.toList());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return tlist;
	}
	
}
