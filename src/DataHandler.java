import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class DataHandler {

    // Static instance of the class
    private static DataHandler instance;
    // XML Data file location
    private static final String FILE_LOCATION = "patient.xml";
    // Store Patient data
    private Map<Integer, Patient> patientDataMap;

    private static String ROOT_ELEMENT = "Hospital";
    private static String PATIENT_TAG = "Patient";
    private static String ID_TAG = "idNumber";
    private static String NAME_TAG = "name";
    private static String AGE_TAG = "age";
    private static String GENDER_TAG = "gender";

    private DataHandler() {
        this.patientDataMap = new HashMap<>();
        xmlFileReader();
    }
    // Get DataHandler instance
    public static DataHandler getInstance(){
        if(instance==null){
            synchronized (DataHandler.class){
                if(instance==null){
                    instance=new DataHandler();
                }
            }
        }
        return instance;
    }

    // Return Patient Data Map
    public Map<Integer, Patient> getPatientDataMap() {
        return patientDataMap;
    }

    // Add patient to the Hashmap
    public void addPatient(Patient patient){
        this.patientDataMap.put(patient.getIdNumber(),patient);
    }

    // Remove patient from Hashmap
    public void removePatient(int id){
        Patient patient = this.patientDataMap.get(id);
        if(patient != null){
            this.patientDataMap.remove(id);
            System.out.println("Patient ID number:"+id+" removed");
        }else{
            System.out.println("Patient ID number:"+id+" not available");
        }
    }

    // Read XML file and store Patient data into HashMap.
    // Primary key is patient ID
    private void xmlFileReader() {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILE_LOCATION));

            doc.getDocumentElement().normalize();

            doc.getDocumentElement().getNodeName();

            // Get Patient List
            NodeList list = doc.getElementsByTagName(PATIENT_TAG);

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // get data of the student
                    String idNumber = element.getElementsByTagName(ID_TAG).item(0).getTextContent();
                    String name = element.getElementsByTagName(NAME_TAG).item(0).getTextContent();
                    String age = element.getElementsByTagName(AGE_TAG).item(0).getTextContent();
                    String gender = element.getElementsByTagName(GENDER_TAG).item(0).getTextContent();
                    // put data in Hashmap
                    this.patientDataMap.put(Integer.parseInt(idNumber),new Patient(Integer.parseInt(idNumber),name,Integer.parseInt(age),gender));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            System.out.println("Patient Log File is not found");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Save application data to XML file
    public void XMLFileWriter(){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(ROOT_ELEMENT);
            doc.appendChild(rootElement);

            for (Patient patientData : patientDataMap.values()) {

                Element patient = doc.createElement(PATIENT_TAG);

                rootElement.appendChild(patient);

                Element idNumber = doc.createElement(ID_TAG);
                idNumber.setTextContent(Integer.toString(patientData.getIdNumber()));
                patient.appendChild(idNumber);

                Element name = doc.createElement(NAME_TAG);
                name.setTextContent(patientData.getName());
                patient.appendChild(name);

                Element age = doc.createElement(AGE_TAG);
                age.setTextContent(Integer.toString(patientData.getAge()));
                patient.appendChild(age);

                Element gender = doc.createElement(GENDER_TAG);
                gender.setTextContent(patientData.getGender());
                patient.appendChild(gender);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_LOCATION));
            transformer.transform(source, result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
