package hibernateFirstProject;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateClient {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure();   //automatically looks for hibernate.cfg.xml
		
		SessionFactory sft=cfg.buildSessionFactory();
	
		Session s1=sft.openSession();
		Transaction tx=s1.beginTransaction();
	
		Product p1=new Product();
		System.out.print("Enter the product name: ");
		Scanner scanner=new Scanner(System.in);
		String pName=scanner.nextLine();
		p1.setProductName(pName);
		System.out.print("Enter the product cost: ");
		int pCost=scanner.nextInt();
		p1.setProductCost(pCost);
		System.out.print("Enter the product description: ");
		scanner.nextLine();
		String desc=scanner.nextLine();
		
		p1.setDescription(desc);
		s1.save(p1);

		tx.commit();
		scanner.close();
		sft.close();
	/*	
	  	Product p1=new Product();
		p1.setProductName("Challenger");
		p1.setProductCost(50000);
		p1.setDescription("MuscleCar");
		s1.save(p1);
		
		tx.commit();
		System.out.println("Record Inserted");
		sft.close();    */
	
	}

}
