package org.example;

import domain.Student;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentFileRepository;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

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
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

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


}
