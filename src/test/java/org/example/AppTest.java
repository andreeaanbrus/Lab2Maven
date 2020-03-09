package org.example;

import domain.Student;
import org.junit.Assert;
import org.junit.Test;
import repository.StudentXMLRepo;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
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
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("fisiere/studentTest.xml");
        Student student = new Student("", "Andreea Anbrus", 931, "aiie2252@scs.ubbcluj.ro");
        assertNull(studentXMLRepo.findOne(""));
    }
}
