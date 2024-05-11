package EntityList;
import Entity.Product;
import java.util.ArrayList;
public class ProductList{
	private ArrayList<Product> Products = new ArrayList<Product>();
	public void insert(Product c){
		Products.add(c);
	}
	public boolean removeByID(int id){
		for(int i=0;i<Products.size();i++){
			if(Products.get(i).getID() == id){
				Products.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Product getByID(int id){
		for(int i=0;i<Products.size();i++){
			if(Products.get(i).getID() == id){
				return Products.get(i);
			}
		}
		return null;
	}
	
	
	public ArrayList<Product> getAll(){
		return Products;
	}
}