import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class TestSerialisation {
	
	
	/*public static void main(String[] args) {
		
		
		/*
		Compte c=new  CompteCourant("aissa", 10000, 100);
		Compte c1=new  CompteCourant("moussa", 10000, 100);
		Compte c2=new  CompteCourant("ali", 10000, 100);
		c.addTransaction(1, 100);
		c.retirer(100);
		c.addTransaction(0, 200);
		c.déposer(200000);
		c1.addTransaction(1, 100);
		c1.retirer(300);
		c1.addTransaction(0, 200);
		c1.déposer(800000);
		c2.addTransaction(1, 100);
		c2.retirer(200);
		c2.addTransaction(0, 200);
		c2.déposer(100500);
		
		
		
	try{
		FileOutputStream fichier=new FileOutputStream("test.txt");
		
		ObjectOutputStream oos=new ObjectOutputStream(fichier);
		
		oos.writeObject(c);
		oos.writeObject(c1);
		oos.writeObject(c2);
		oos.flush();
		oos.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	
	
	
	try{
		
		FileInputStream fichier=new FileInputStream("test.txt");
		ObjectInputStream ois=new ObjectInputStream(fichier);
		while(fichier.available()!=0){
			Compte c3=(Compte)ois.readObject();
			System.out.println(c3.toString());
		}
		ois.close();
		
		
		ois.close();
		
			}catch(Exception e){
		System.out.println(e.getMessage());
	}
	
	
		

	ArrayList<Client> client=new ArrayList<>();
	
	Client c1=new Client(123456, "aissa");
	c1.addCompte(20000);
	
	
	Client c2=new Client(14789, "moussa");
	c2.addCompte(10000);
	
	
	client.add(c1);
	client.add(c2);
		

	try {
		
		FileOutputStream fichier=new  FileOutputStream("liste.txt");
		
		ObjectOutputStream oos=new ObjectOutputStream(fichier);
		
		oos.writeObject(client);
		
		oos.flush();
		oos.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
	
	
	try {
		FileInputStream fichier=new  FileInputStream("liste.txt");
		
		ObjectInputStream ois=new ObjectInputStream(fichier);
		
		ArrayList<Client> c=(ArrayList<Client>)ois.readObject();
		
		
		ois.close();
		
		Iterator<Client> ite=c.iterator();
		
		while(ite.hasNext()){
		
			System.out.println(ite.next().getComptes());
			
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}*/

}
