import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Locale;

public class Person {

    public static void main(String[] args) {
        try {

            System.out.println("\n ==============================================");
            System.out.println("   STUDENT ACADEMIC RECORD PROCESSING SYSTEM  ");
            System.out.println(" ============================================== \n");

            Student student = new Student();
            student.processXML();

            System.out.println("\n Threads Executed Successfully");
            System.out.println("\n Student Report Generated ");
            System.out.println("\n Eproject Completed Successfully ");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getValue(Document document, String tag) {

        return document
                .getElementsByTagName(tag)
                .item(0)
                .getTextContent();
    }

}
