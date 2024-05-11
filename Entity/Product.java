package Entity;
public class Product{
	private int id;
	private String name;
	private double price;
	private int units;
	
	public Product(int id,String name,double price, int units){
		this.id = id;
		this.name = name;
		this.price = price;
		this.units = units;
	}
	public void setID(int id){this.id = id;}
	public int getID(){return id;}
	public void setname(String name){this.name = name;}
	public String getname(){return this.name;}
	public void setprice(double price){this.price = price;}
	public double getprice(){return this.price;}
	public void setunits(int units){this.units = units;}
	public int getunits(){return this.units;}
	
	
}