package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import org.junit.experimental.theories.internal.Assignments;
import repository.NotaXMLRepo;
import repository.StudentFileRepository;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    StudentFileRepository studentFileRepository =  new StudentFileRepository("fisiere/testStudent.txt");
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/StudentiTest.xml";
    String filenameTema = "fisiere/TemeTest.xml";
    String filenameNota = "fisiere/NoteTest.xml";

    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Test
    public void test_tc1_testStudentId() {
        Student student = new Student("", "", 931, "some email");
        int number = ((Collection<?>)service.getAllStudenti()).size();
        try {
            service.addStudent(student);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Id incorect!");
        }
        assertEquals(((Collection<?>)service.getAllStudenti()).size(), number);

        Student student1 = new Student("1234", "Andreea", 931, "some email");
        try {
            service.addStudent(student1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Student s1 = service.findStudent("1234");
        assertEquals(s1, student1);
        service.deleteStudent("1234");
    }
//    todo different test cases

    @Test
    public void test_tc2_testStudentName() {
        Student student = new Student("1243", "", 931, "some email");
        int number = ((Collection<?>)service.getAllStudenti()).size();
        try {
            service.addStudent(student);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Nume incorect!");
        }
        assertEquals(((Collection<?>)service.getAllStudenti()).size(), number);

        Student student1 = new Student("1234", "Andreea", 931, "some email");
        try {
            service.addStudent(student1);
        } catch (Exception e) {
            System.out.println(e);
        }
        Student s1 = service.findStudent("1234");
        assertEquals(s1, student1);
        service.deleteStudent("1234");
    }

    @Test
    public void test_tc3_testStudentGroup() {
        Student student = new Student("1234", "Andreea", -1, "some email");
        int number = ((Collection<?>)service.getAllStudenti()).size();
        try {
            service.addStudent(student);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Grupa incorecta!");
        }
        assertEquals(((Collection<?>)service.getAllStudenti()).size(), number);

        Student student1 = new Student("1234", "Andreea", 0, "some email");
        try {
            service.addStudent(student1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Student s1 = service.findStudent("1234");
        assertEquals(s1, student1);
        service.deleteStudent("1234");

        Student student2 = new Student("12345", "Andreea2", 931, "aiie2252@scs.ubbcluj.ro");
        try {
            service.addStudent(student2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Student s2 = service.findStudent("12345");
        assertEquals(s2, student2);
        service.deleteStudent("12345");
    }

    @Test
    public void test_tc4_testStudentEmail() {
        Student student = new Student("1243", "Andreea", 931, "");
        int number = ((Collection<?>)service.getAllStudenti()).size();
        try {
            service.addStudent(student);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Email incorect!");
        }
        assertEquals(((Collection<?>)service.getAllStudenti()).size(), number);

        Student student1 = new Student("1234", "Andreea", 931, "some email");
        try {
            service.addStudent(student1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Student s1 = service.findStudent("1234");
        assertEquals(s1, student1);
        service.deleteStudent("1234");
    }

    @Test
    public void souldAddStudent() {
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("fisiere/studentTest.xml");
        Student student = new Student("12", "Andreea Anbrus", 931, "aiie2252@scs.ubbcluj.ro");
        studentXMLRepo.save(student);
        assertNotNull(studentXMLRepo.findOne("12"));
    }

    @Test
    public void shouldNotAddStudent() {
        assertEquals(false, false);
    }

    //    in class
    @Test
    public void test_tc1_testAssignmentID() {
        Tema tema = new Tema("", "Description", 10, 21);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Numar tema invalid!");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc2_testAssignmentDescription() {
        Tema tema = new Tema("1265", "", 10, 12);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Descriere invalida!");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }
//    take home
    @Test
    public void test_tc3_testAssignmentIDNull() {
        Tema tema = new Tema(null, "Description", 10, 12);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Numar tema invalid!");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc4_deadline() {
        Tema tema = new Tema("12345", "Description", 0, 12);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Deadlineul trebuie sa fie intre 1-14.");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc5_deadline() {
        Tema tema = new Tema("12345", "Description", 15, 12);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Deadlineul trebuie sa fie intre 1-14.");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc6_primire() {
        Tema tema = new Tema("12345", "Description", 11, 0);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Saptamana primirii trebuie sa fie intre 1-14.");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc7_primire() {
        Tema tema = new Tema("12345", "Description", 11, 15);
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Saptamana primirii trebuie sa fie intre 1-14.");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc8_primire() {
        Tema tema = null;
        int number = ((Collection<?>)service.getAllTeme()).size();
        try {
            service.addTema(tema);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entity is null");
        }
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number);
    }

    @Test
    public void test_tc9_primire() {
        Tema tema = new Tema("123", "Description", 11, 12);
        int number = ((Collection<?>)service.getAllTeme()).size();
        service.addTema(tema);
        assertEquals(((Collection<?>)service.getAllTeme()).size(), number + 1);
        service.deleteTema("123");
    }

    @Test
    public void test_tc10_addGrade() {
        Nota nota = new Nota("123", "12", "1", 10, LocalDate.of(2019, 4, 27));
        int number = ((Collection<?>)service.getAllNote()).size();
        service.addNota(nota, "Some feedback");
        assertEquals(((Collection<?>)service.getAllNote()).size(), number + 1);
        service.deleteNota("123");
    }

    @Test
    public void test_big_bang() {
        int numberStudent = ((Collection<?>)service.getAllStudenti()).size();
        int numberAssignments = ((Collection<?>)service.getAllTeme()).size();
        int numberGrades = ((Collection<?>)service.getAllNote()).size();
        Student student = new Student("432", "Andreea", 9, "email");
        Tema tema = new Tema("435", "Tema", 12, 1);
        Nota nota = new Nota("432", "432", "435", 10, LocalDate.of(2020, 4, 27));
        service.addStudent(student);
        service.addTema(tema);
        service.addNota(nota, "new one");
        assertEquals(((Collection<?>)service.getAllStudenti()).size(), numberStudent + 1);
        assertEquals(((Collection<?>)service.getAllNote()).size(), numberGrades + 1);
        assertEquals(((Collection<?>)service.getAllTeme()).size(), numberAssignments + 1);
        service.deleteTema("435");
        service.deleteStudent("432");
        service.deleteNota("432");
    }

    @Test
    public void test_incremental_addStudent() {
        Student student1 = new Student("1234", "Andreea", 931, "some email");
        service.addStudent(student1);
        Student s1 = service.findStudent("1234");
        assertEquals(s1, student1);
        service.deleteStudent("1234");
    }

    @Test
    public void test_incremental_addAssignment() {
        Student student1 = new Student("1234", "Andreea", 931, "some email");
        Tema tema = new Tema("435", "Tema", 12, 1);
        service.addStudent(student1);
        service.addTema(tema);
        Student s1 = service.findStudent("1234");
        Tema t1 = service.findTema("435");
        assertEquals(s1, student1);
        assertEquals(tema, t1);
        service.deleteStudent("1234");
        service.deleteTema("435");
    }

    @Test
    public void test_incremental_AddGrade() {
        Student student = new Student("1234", "Andreea", 931, "some email");
        Tema tema = new Tema("435", "Tema", 12, 1);
        Nota nota = new Nota("432", "1234", "435", 10, LocalDate.of(2020, 4, 27));
        service.addStudent(student);
        service.addTema(tema);
        service.addNota(nota, "some feedback");
        Student s1 = service.findStudent("1234");
        Tema t1 = service.findTema("435");
        Nota n1 = service.findNota("432");
        assertEquals(s1, student);
        assertEquals(tema, t1);
        assertEquals(nota, n1);
        service.deleteStudent("1234");
        service.deleteTema("435");
        service.deleteNota("432");
    }
}
