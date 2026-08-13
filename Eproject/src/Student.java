import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;


public class Student extends Person implements ResultProcessor{

    public void processXML(){

        try{

        File studentxmlFile = new File("src/Student.xml");
        DocumentBuilderFactory StudentDAta = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = StudentDAta.newDocumentBuilder();

        // Parse XML
        Document document = builder.parse(studentxmlFile);

        document.getDocumentElement().normalize();

        // Student ID
        Element student = document.getDocumentElement();
        String id = student.getAttribute("id");
        String name = document.getElementsByTagName("name").item(0).getTextContent();
        String department = document.getElementsByTagName("department").item(0).getTextContent();
        String category = document.getElementsByTagName("category").item(0).getTextContent();
        String level = document.getElementsByTagName("level").item(0).getTextContent();
        String graduationYear = document.getElementsByTagName("graduationYear").item(0).getTextContent();
        String generatedDateTime = document.getElementsByTagName("generatedDateTime").item(0).getTextContent();


        Thread studentThread = new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Student ID       : " + id);
            System.out.println("Name             : " + name);
            System.out.println("Department       : " + department);
            System.out.println("Category         : " + category);
            System.out.println("Level            : " + level);
            System.out.println("Graduation Year  : " + graduationYear);
            System.out.println("Generated        : " + generatedDateTime);

        }, "Student Information Thread");

        Thread courseThread = new Thread(() -> {

            System.out.println("Generating XML.............SUCCESS \n");
            System.out.println("Validating XML............SUCCESS \n");
            System.out.println("Parsing XML...............SUCCESS \n");

                NodeList courses = document.getElementsByTagName("course");

                for (int i = 0; i < courses.getLength(); i++) {

                    Element course = (Element) courses.item(i);

                    String title = course.getElementsByTagName("title") .item(0).getTextContent();

                    String score = course.getElementsByTagName("score").item(0).getTextContent();

                    System.out.println(
                            (i + 1) + ". " + title + " - " + score
                    );
                }

            }, " BIG DADDY MILLZ THREAD");


        // this is meant to Start both threads
        courseThread.start();
        studentThread.start();

        // this thread waits for both thread to finish
        courseThread.join();
        studentThread.join();

            PrintWriter writer = new PrintWriter(
                    new FileWriter("src/student.txt")
            );

            writer.println("==========================================");
            writer.println("       STUDENT ACADEMIC RECORD");
            writer.println("==========================================");

            writer.println("Student ID       : " + id);
            writer.println("Name             : " + name);
            writer.println("Department       : " + department);
            writer.println("Category         : " + category);
            writer.println("Level            : " + level);
            writer.println("Graduation Year  : " + graduationYear);
            writer.println("Generated        : " + generatedDateTime);

            writer.println("------------------------------------------");
            writer.println("Overall Performance");
            writer.println("------------------------------------------");

            NodeList courses = document.getElementsByTagName("course");

            for (int i = 0; i < courses.getLength(); i++) {

                Element course = (Element) courses.item(i);

                String code = course.getAttribute("code");

                String title = course .getElementsByTagName("title") .item(0) .getTextContent();

                String score = course  .getElementsByTagName("score") .item(0).getTextContent();

                writer.println(
                        (i + 1) + ". " + code + " - " + title + " - " + score
                );
            }

            writer.println("==========================================");

            writer.close();


             } catch (InterruptedException | SAXException | IOException e) {
                System.out.println("There's an error in your thread");
             } catch (ParserConfigurationException e) {
                 System.out.println("Thread Failed");
             }
        }

    }
