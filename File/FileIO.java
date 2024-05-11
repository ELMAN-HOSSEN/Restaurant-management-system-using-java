package File;
import java.lang.*;
import java.util.*;
import java.io.*;
import EntityList.*;
import Entity.*;

public class FileIO{
	
	public static int checkUserLogin(String fname,String uname, String upass){
		int status = 0;
		try{
			Scanner sc = new Scanner(new File(fname));
			while(sc.hasNextLine()){
				
				String row = sc.nextLine();
				if(row!=null && row.length()>=2){
				String cols[] = row.split(";");
				
				String name = cols[0];
				String pass = cols[1];
				
				if(uname.equals(name) && upass.equals(pass)){
					status = 1;
					break;
				}
				else if(uname.equals(name) && !upass.equals(pass)){
					status = 2;
				}
				
					}
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot Read From File");
		}
		return status;
	}
	
	
	
	public static boolean registerUser(String fname, String uname, String upass){
		if(checkUserLogin(fname,uname,upass) == 0){			
			try {
				FileWriter writer = new FileWriter(fname,true);
				writer.write(uname+";"+upass+"\n");
				writer.close();
			} catch (Exception e){
				System.out.println("Cannot Write in File");
			}
			return true;
		}
		
		return false;
	}
		public static void registerTransaction(String fname, String uname, double price, String numb,String ids){
				
			try {
				FileWriter writer = new FileWriter(fname,true);
				writer.write(uname+";"+price+";"+numb+";"+ids+"\n");
				writer.close();
			} catch (Exception e){
				System.out.println("Cannot Write in File");
			}
			
	}
	
	
	public static void readFromFile(String fname){
		try{
			Scanner sc = new Scanner(new File(fname));
			while(sc.hasNextLine()){
				String row = sc.nextLine();
				String cols[] = row.split(";");
				
				String name = cols[0];
				int age = Integer.parseInt( cols[2] );
		
				System.out.println("-------------------");
				System.out.println("User Name : "+name);
				System.out.println("User Age : "+age);
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot Read From File");
		}
	}
	public static void readFromEmploysFile(String fname){
		try{
			Scanner sc = new Scanner(new File(fname));
			while(sc.hasNextLine()){
				String row = sc.nextLine();
				String cols[] = row.split(";");
				
				int id = Integer.parseInt( cols[0] );
				String name = cols[1];
				int age = Integer.parseInt( cols[2] );
				String gender = cols[3];
				String joiningDate = cols[4];
				double salary = Double.parseDouble( cols[5] );
		
				System.out.println("-------------------");
				System.out.println("User ID : "+id);
				System.out.println("User Name : "+name);
				System.out.println("User Age : "+age);
				System.out.println("User gender : "+gender);
				System.out.println("User Join Date : "+joiningDate);
				System.out.println("User Salary : "+salary);
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot Read From File");
		}
	}
	
	public static void writeInFile(String line, String fname, boolean append){
		try {
			FileWriter writer = new FileWriter(fname,append);
			writer.write(line+"\n");
			writer.close();
		} catch (Exception e){
			System.out.println("Cannot Write in File");
		}
	}
	
	
	
	public static void loadProductsFromFile(ProductList products, String fname){
		try{
			Scanner sc = new Scanner(new File(fname));
			while(sc.hasNextLine()){
				String row = sc.nextLine();
				String cols[] = row.split(";");
				
				int id = Integer.parseInt( cols[0] );
				String model = cols[1];
				double price = Double.parseDouble( cols[2] );
				int units = Integer.parseInt( cols[3] );
				products.insert(new Product(id,model,price,units));
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot Read From File");
		}
	}
		
	
	public static void loadTransactionFromFile(TransactionList transaction, String fname){
		try{
			Scanner sc = new Scanner(new File(fname));
			while(sc.hasNextLine()){
				String row = sc.nextLine();
				String cols[] = row.split(";");
				
				
				String name = cols[0];
				double price = Double.parseDouble( cols[1] );
				String numb = cols[2];
				String ids = cols[3];
				
				transaction.insert(new Transaction(name,price,numb,ids));
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot Read From File");
		}
	}
	
}