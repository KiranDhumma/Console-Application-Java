package console.application.v2;

import java.util.HashMap;
import java.util.Map;



public class StudentsDatabase {
	private Map<String, Students> students = new HashMap<>();
	
	public StudentsDatabase() {
		students.put("S101", new Students("John", "abcd"));
		students.put("S102", new Students("Daniel", "mnop"));
		
	}
	
	public Students getStudentDetails(String studentId) {
		return students.get(studentId);
	}
	
	public boolean authenticateUser(String studentId, String loginPwd) {
		Students student = getStudentDetails(studentId);
		if(student != null) {
			if(student.getLoginPwd().equals(loginPwd)) {
				return true;
			}
		} return false;
	}
	
	public static void main(String[] args) {
		StudentsDatabase sD = new StudentsDatabase();
//		System.out.println(cD.courses.get("C1"));
		Students student = sD.getStudentDetails("S101");
			System.out.println(student.getStudentId());
			System.out.println(student.getStudentName());
			System.out.println(student.getLoginPwd());

	}
	

}
