package EntityList;
import Entity.*;
import java.util.ArrayList;
public class TransactionList{
	private ArrayList<Transaction> Transaction = new ArrayList<Transaction>();
	public void insert(Transaction c){
		Transaction.add(c);
	}
	
	
	
	
	
	public ArrayList<Transaction> getAll(){
		return Transaction;
	}
}