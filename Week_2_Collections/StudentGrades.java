
import java.util.LinkedHashMap;
import java.util.Map;

class Student {

    public int id;
    public String name;
    public char grade;

    public Student(int id, String name, char grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", grade=" + grade + '}';
    }
}

class StudentGrades {

    private final LinkedHashMap<Integer, Student> studentGrades;

    public StudentGrades() {
        studentGrades = new LinkedHashMap<>();
    }

    public void addStudent(Student student) {
        studentGrades.put(student.id, student);
    }

    public boolean removeStudent(int studentId) {
        if (studentGrades.containsKey(studentId)) {
            studentGrades.remove(studentId);
            return true;
        }
        return false;
    }

    public boolean updateStudentGrade(int studentId, char newGrade) {
        if (studentGrades.containsKey(studentId)) {
            Student student = studentGrades.get(studentId);
            student.grade = newGrade;
            return true;
        }
        return false;
    }

    public void displayStudents() {
        System.out.println("\nStudents: ");
        for (Map.Entry<Integer, Student> entry : studentGrades.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public static void main(String[] args) {
        StudentGrades sg = new StudentGrades();

        sg.addStudent(new Student(1, "Sagar", 'A'));
        sg.addStudent(new Student(2, "Jyotin", 'B'));
        sg.addStudent(new Student(3, "Pradhyumn", 'C'));

        sg.displayStudents();

        boolean updated = sg.updateStudentGrade(2, 'A');
        if (updated) {
            System.out.println("\nGrade updated for Jyotin");
        } else {
            System.out.println("\nJyotin Smith not found");
        }

        boolean removed = sg.removeStudent(3);
        if (removed) {
            System.out.println("\nPradhyumn removed from the system.");
        } else {
            System.out.println("\nPradhyumn not found.");
        }
        sg.displayStudents();
    }
}
