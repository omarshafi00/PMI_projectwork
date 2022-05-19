# PMI_projectwork
DU9CPG Omar Alshafi


Hospital Patient Data Management System


Propose: 
In the hospital administration system, this system can be used to handle patient personal data. The application maintains a database of patient information 
in consistent file. It can also manage the patient registration procedure, data update, patient removal, and view patient details.

 Structure:
 
There are four classes in this system. XML file is used for store data The following is a list of classes. 
The DataHandler class has access to the data file (Patient.xml). The HospitalSystem class handles data flows of the application. 
All other classes can communicate with it. Only the HospitalSystem class is communicated with by the Main and DataHandler classes. 
Read all data from the XML file and store it in the DataHandler object when the application starts. The DataHandler instance class receives all data changes. 
When the program is closed, the DataHandler instance's data is saved to an XML file.

Patient Class 

This class represents the Patient entity of the application. This class helps to make patient data handling proceed easier.

⦁	Attributes
⦁	PatientID
⦁	name
⦁	age
⦁	gender (Male or Female)
⦁	Functions
⦁	Getters of all attributes

DataHandler Class

This class is responsible for the application's data storage. This is a singleton class.

⦁	Attributes
⦁	patientDataMap
⦁	FILE_LOCATION: XML file location
⦁	Functions
⦁	Read patient data from the XML file
⦁	Keep patient data in a Map
⦁	put  patient data to the Map
⦁	Remove patient from the Map
⦁	Save application data to the XML file

HospitalSystem Class

This is the class that handles all functions of the Hospital patient data management. This class is responsible for controlling all data flows of the application.

⦁	Functions
⦁	Show patient data to the user
⦁	Process patient registration
⦁	Update patient data
⦁	Remove patients
⦁	Save data when system exits

Main class

The main class handles the runtime facilities of the application.

⦁	Functions
⦁	Provide function Manu to the user
⦁	Take user inputs
⦁	Provide data according to user requests
⦁	Keep application in run state
⦁	Communicate with HospitalSystem class

XML File

Keep Patient data of the application.

⦁	< Hospital >: Root element of the XML file
⦁	< Patient >: Parent tag of every patient entity
⦁	< idNumber >: Tag for patient ID
⦁	<name>: Tag for patient name
⦁	<age Tag for patient age
⦁	<gender>: Tag for patient gender
