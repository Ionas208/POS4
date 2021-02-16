/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.jaxb.test;

import at.kaindorf.jaxb.pojos.Exam;
import at.kaindorf.jaxb.pojos.Student;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author 10jon
 */
public class JAXBTester {
    public static void main(String[] args) {
        try {
            Student student = new Student(18, "Jonas", "Seidl");
            student.addExam(new Exam(1, "POS"));
            student.addExam(new Exam(1, "NVS"));
            
            //V1
            /*JAXBContext ctx = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(student, System.out);*/
            
            //V2
            JAXB.marshal(student, System.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
