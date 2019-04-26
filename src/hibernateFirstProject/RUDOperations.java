package hibernateFirstProject;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RUDOperations {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure();
		SessionFactory sft=cfg.buildSessionFactory();
		Session s1=sft.openSession();
		Transaction tx=s1.beginTransaction();
		Product p1=new Product();
		p1=(Product)s1.get(Product.class, 2);
		
		System.out.print("1.Display\n2.Update\n3.Delete\nEnter choice:");				//for Single record 
		Scanner scanner=new Scanner(System.in);
		int choice=scanner.nextInt();
		if(choice==1)
		{			
			System.out.println("------------------------------------------------");
			if(p1!=null)
			{
			System.out.println(p1.getProductId()+"\t"+p1.getProductName()+"\t"+p1.getProductCost()+"\t"+p1.getDescription());	
			}
			else
			{
				System.out.println("Product Not Found !");
			}	
		}
		
		else if(choice==2)
		{
			if(p1!=null)
			{
				p1.setProductName("Mustang");
				p1.setProductCost(45000);
				p1.setDescription("SportsCar");
				System.out.println("Product Updated !");	
			}
			else
			{
				System.out.println("Product Not Found. Nothing updated!");
			}
			//s1.save(p1);
			tx.commit();
		}
		else if(choice==3)
		{
			s1.delete(p1);						//p1 object 
			System.out.println("Record deleted");
			tx.commit();
		}
		
		else
			System.out.println("Wrong choice!");
		scanner.close();
		sft.close();
	}

}
