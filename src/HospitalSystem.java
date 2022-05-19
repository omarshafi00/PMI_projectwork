import java.util.Map;

/**
 *  Data Manipulation class
 */
public class HospitalSystem {

    DataHandler handler;

    public HospitalSystem(){
        handler = DataHandler.getInstance();
    }

    // Add patient Details
    public void registerPatient(int idNumber, String name,int age, String gender){
        Map<Integer, Patient> patientMap = handler.getPatientDataMap();
        boolean registered = false;
        if(!patientMap.isEmpty()) {
            for (Patient patient:patientMap.values()) {
                if(patient.getIdNumber() == idNumber){
                    System.out.println("Patient ID is already in system");
                    registered = true;
                }
            }
        }
        if(!registered){
            handler.addPatient(new Patient(idNumber,name,age,gender));
            System.out.println("Patient added to the system");
        }

    }

    // Update Patient Data
    public void updatePatientData(int idNumber, String name,int age, String gender){
        handler.addPatient(new Patient(idNumber,name,age,gender));
    }


    // Remove patient from the system
    public void removePatient(int idNumber){
        handler.removePatient(idNumber);
    }

    // Read and print Patient Data
    public void printAllPatient(){
        Map<Integer, Patient> patient = handler.getPatientDataMap();
        if(patient.isEmpty()){
            System.out.println("Patients are not available");
        }else{
            for (Patient patient1:patient.values()) {
                System.out.println(patient1);
            }
        }
    }

    // Method for show a patient data to the user
    public boolean printAndCheckPatient(int idNumber){
        Map<Integer, Patient> patient = handler.getPatientDataMap();
        if(patient.isEmpty()){
            return false;
        }
        for (Patient patient1:patient.values()) {
            if(patient1.getIdNumber() == idNumber){
                System.out.println("Patient Details :");
                System.out.println(patient1);
                return true;
            }
        }
        return false;
    }

    // System exit function. Save all current data in XML file
    public void savePatientData(){
        handler.XMLFileWriter();
    }
}
