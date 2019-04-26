package hibernateFirstProject;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HQLOperations {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure();
		SessionFactory sft=cfg.buildSessionFactory();
		Session s1=sft.openSession();
		Transaction tx=s1.beginTransaction();
		Scanner scanner=new Scanner(System.in);
		System.out.print("1.Display\t2.Update\t3.Delete\nEnter Choice:");
		int choice=scanner.nextInt();
		if(choice==1)				//Multiple records display using HQL
		{
			System.out.print("1.DisplayAll(pcost>300)\n2.DisplayAllPName\n3.DisplayPnamePcost\nEnter choice:");
			int choice1=scanner.nextInt();
			if(choice1==1)
			{
				Query query=s1.createQuery("from Product as P where P.productCost>300 order by P.productCost");
				List<Product> prdList=query.list();		//Product because we need the entire thing....from means entire thing(no selection)
				System.out.println("pId\tpName\tpCost\tDescription");
				System.out.println("------------------------------------------------");
				for(Product p1:prdList)
				{
					System.out.println(p1.getProductId()+"\t"+p1.getProductName()+"\t"+p1.getProductCost()+"\t"+p1.getDescription());
				}
			}
			else if(choice1==2){
				Query query=s1.createQuery("select P.productName from Product as P");
				List<String> prdlist=query.list();	//Used String because only one property for list. select from means selection
				System.out.println("pName");
				System.out.println("--------------");
				for(String prd:prdlist) {
					System.out.println(prd);
				}  
			}
			else if(choice1==3){
				Query query=s1.createQuery("select P.productName,P.productCost from Product as P");
				List<Object[]> prdlist=query.list();			//Object[] array bcoz only 2 properties needed from Product, if all needed we would use Product(className)
				System.out.println("pName\tpCost");
				System.out.println("------------------");
				for(Object prd[]:prdlist) {
					System.out.println(prd[0]+"\t"+prd[1]);
				} 
			}
			else
				System.out.println("Wrong choice!");
				scanner.close();
		}
		
		else if(choice==2)					//Multiple record update using HQL
		{
			Query query=s1.createQuery("update Product as P set P.productCost=? where P.productCost>1000");
			query.setFloat(0, 8888);		// index start at 0,not 1 like in prepared statement
			int result=query.executeUpdate();
			if(result>0)
			{
				System.out.println(result+ "records updated !");
			}
			tx.commit();
		}
		
		else if(choice==3)			//Multiple record delete using HQL
		{
			Query query=s1.createQuery("delete from Product as P where P.productCost<?");
			query.setFloat(0,50);
			int result=query.executeUpdate();
			if(result>0)
			{
				System.out.println(result+ "records Deleted !");
			}
			tx.commit();			
		}
	}
}

