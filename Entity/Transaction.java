package Entity;
public class Transaction{
	
	private String name;
	private String numb;
	private String ids;
	private double price;
	
	
	public Transaction(String name,double price,String numb,String ids){
		
	     this.name = name;
	     this.numb = numb;
	     this.ids= ids;
		
		 this.price = price;
	}
	
	public void setname(String name){this.name = name;}
	public String getname(){return this.name;}

	public void setnumb(String numb){this.numb = name;}
	public String getnumb(){return this.numb;}

	public void setids(String ids){this.ids = ids;}
	public String getids(){return this.ids;}

	public void setprice(double price){this.price = price;}
	public double getprice(){return this.price;}
	
	
	
}