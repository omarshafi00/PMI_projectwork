import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Define instance of HospitalSystem
        HospitalSystem hospitalSystem = new HospitalSystem();
        Scanner sc = new Scanner(System.in);

        OuterLoop : while(true){
            System.out.println();
            System.out.println("1-Show Patient Details");
            System.out.println("2-Add New Patient");
            System.out.println("3-Update Patient Details");
            System.out.println("4-Remove Patient");
            System.out.println("5-Exit");
            System.out.println("Enter number: ");
            String number = sc.nextLine();

            switch (number) {
                case "1":
                    //Output all patients data
                    hospitalSystem.printAllPatient();
                    break;

                case "2":
                    // Take input from the user and register a student
                    System.out.println("Enter Patient ID number : ");
                    String idNumber=sc.nextLine();
                    if(!checkInt(idNumber)){
                        System.out.println("Patient ID number should be a number");continue;
                    }

                    System.out.println("Enter name : ");
                    String name=sc.nextLine();
                    if(name.equals("")){System.out.println("Name is invalid");continue; }

                    System.out.println("Enter age : ");
                    String age =sc.nextLine();
                    if(!checkInt(age)){
                        System.out.println("Age should be a number");continue;
                    }

                    System.out.println("Enter gender : ");
                    String gender=sc.nextLine();
                    if(gender.equals("male") || gender.equals("Male") ) {
                        hospitalSystem.registerPatient(Integer.parseInt(idNumber),name,Integer.parseInt(age),"Male");
                    }else if(gender.equals("female") || gender.equals("Female")){
                        hospitalSystem.registerPatient(Integer.parseInt(idNumber),name,Integer.parseInt(age),"Female");
                    } else{
                        System.out.println("Gender should be Male or Female");continue;
                    }
                    break;

                case "3":
                    // Take input from the user and update Patient date
                    System.out.println("Enter Patient ID number : ");
                    String idNumber1=sc.nextLine();
                    if(!checkInt(idNumber1)){
                        System.out.println("Patient ID number should be a number");continue;
                    }
                    boolean success = hospitalSystem.printAndCheckPatient(Integer.parseInt(idNumber1));
                    if(success){
                        System.out.println("Enter name : ");
                        String name1=sc.nextLine();
                        if(name1.equals("")){System.out.println("Name is invalid");continue; }

                        System.out.println("Enter age : ");
                        String age1 =sc.nextLine();
                        if(!checkInt(age1)){
                            System.out.println("Age should be a number");continue;
                        }

                        System.out.println("Enter gender : ");
                        String gender1=sc.nextLine();
                        if(gender1.equals("male") || gender1.equals("Male") ) {
                            hospitalSystem.updatePatientData(Integer.parseInt(idNumber1),name1,Integer.parseInt(age1),"Male");
                        }else if(gender1.equals("female") || gender1.equals("Female")){
                            hospitalSystem.updatePatientData(Integer.parseInt(idNumber1),name1,Integer.parseInt(age1),"Female");
                        } else{
                            System.out.println("Gender should be Male or Female");continue;
                        }
                        System.out.println("Updated Successfully");
                    }else {
                        System.out.println("Patient ID number:"+idNumber1+" not available");
                    }
                    break;

                case "4":
                    // Remove student
                    System.out.println("Enter Patient ID number : ");
                    String idNumber2=sc.nextLine();
                    if(!checkInt(idNumber2)){
                        System.out.println("Patient ID number should be a number");continue;
                    }
                    hospitalSystem.removePatient(Integer.parseInt(idNumber2));
                    break;

                case "5":
                    // Patient data will be saved when system exists
                    hospitalSystem.savePatientData();
                    break OuterLoop;

                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    // Check whether string can be parsed into Integer
    public static boolean checkInt(String value){
        try{
            int intValue = Integer.parseInt(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}

