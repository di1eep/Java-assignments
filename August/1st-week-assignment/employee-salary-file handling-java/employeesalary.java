import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class employeesalary{
 public static String fileName = "output.csv";
 public static String header = "employeeID, employeeName, employeeDepartment, employeeSalary";

//======================write file=========================================

 public static void addEmployeeDetails(String fileName, Scanner sc) {

  String Header = "Employee ID, Employee Name, Department, Salary";
  System.out.println("Enter the ID of the employee");
  int employeeID = sc.nextInt();
  System.out.println("Enter the name of the employee: ");
  String employeeName = sc.next();
  System.out.println("Enter the employee Department: ");
  String employeeDepartment = sc.next();
  System.out.println("Enter the employee salary: ");
  Double employeeSalary = sc.nextDouble();
  System.out.println("\n");
 try {
   BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
   File f = new File(fileName);
   if(f.length() == 0){
     bw.write("employeeID, employeeName, employeeDepartment, employeeSalary");
   }
   
   bw.write(employeeID +", "+ employeeName+", "+ employeeDepartment +", "+ employeeSalary);
   bw.close();
   System.out.println("Employees details updated");
 } catch(IOException e){
   System.out.println(e.getMessage());
 }

 }

//======================write file=========================================

   //=================read file and show output with specified seveporators ==============================

public static void showEmployeeDetails(String fileName, Scanner sc)
{
  try 
    {
      System.out.println("Enter the symbol as a character seveprator");
      String symbol = sc.next();
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String Line;
      System.out.println("=========================================");
      br.readLine();
      while((Line = br.readLine())!=null)
      {
       String[] employeeData = Line.split(", ");
       int EmpID = Integer.parseInt(employeeData[0]); 
       String name = employeeData[1];
       String department = employeeData[2];
       double salary = Double.parseDouble(employeeData[3]);
       System.out.println(EmpID + symbol + name + symbol + department + symbol + salary);
      }
      System.out.println("=========================================");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
}
 
   //=================read file and show output with specified seveporators ==============================


   
   //=================sorting employees based on salaries(High to low ) ==============================

   
    public static void salaysortEmployee(String fileName) {
        try {
            BufferedReader objBufferedReader = new BufferedReader(new FileReader(fileName));
            String employeedata = "";
            String updatedemployeedata = "";

           //[employeecount][columnsCount];
            int employeecount = 0;
            int index = 0;
            while ((employeedata = objBufferedReader.readLine()) != null){
                employeecount++;
            }
            objBufferedReader.close();

            objBufferedReader = new BufferedReader(new FileReader(fileName));

            String arremployeedata[][] = new String[employeecount][4]; 
            while ((employeedata = objBufferedReader.readLine()) != null) {
 

                String EmpID = employeedata.substring(0, employeedata.indexOf(","));
                employeedata = employeedata.substring(employeedata.indexOf(",") + 1); 

                String name = employeedata.substring(0, employeedata.indexOf(",")); 
                employeedata = employeedata.substring(employeedata.indexOf(",") + 1); 

                String department = employeedata.substring(0, employeedata.indexOf(",")); 
                employeedata = employeedata.substring(employeedata.indexOf(",") + 1); 

                String salary = employeedata; 
                
              
                updatedemployeedata += (EmpID + "," + name + "," + department + "," + salary +"\n");

                arremployeedata[index][0] = EmpID;
                arremployeedata[index][1] = name;
                arremployeedata[index][2] = department;
                arremployeedata[index][3] = salary;

                index++;
            }
  
            for (int i = 0; i <= employeecount; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(arremployeedata[i][j] + ": ");
                }
                System.out.println();
            }

  
            
            
            objBufferedReader.close();

            FileWriter objFileWriter = new FileWriter(fileName);
            objFileWriter.write(updatedemployeedata);
            objFileWriter.close();

        } catch (Exception e) {
            System.out.println("Error while writing into a file: " + e.getMessage());
        }

    }

    //=================sorting employees based on salaries(High to low ) ==============================



private static char[] formatTableRow(String[] rowData) {
  return null;
}

public static void main(String[] args) {

  Scanner sc = new Scanner(System.in);
  boolean exit = false;

  while(!exit)
  {
          System.out.println("1. Add new employee details");
          System.out.println("2. Show employee details");
          System.out.println("3. sort employees based on salary");
          System.out.println("4. exit the programme");

          int choice = sc.nextInt();
          sc.nextLine();


          switch(choice)
          {
            case 1:
            addEmployeeDetails(fileName, sc);
            break;

            case 2:
            showEmployeeDetails(fileName, sc);
            break;

            case 3:
            salaysortEmployee(fileName);
            break;

          default:
          System.out.println("Invalid choice. please try again.");
          }
  }
 
 }



  public static void readLine() {
  }
}