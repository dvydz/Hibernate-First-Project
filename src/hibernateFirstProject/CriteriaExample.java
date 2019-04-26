package hibernateFirstProject;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CriteriaExample {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure();
		SessionFactory sft=cfg.buildSessionFactory();
		Scanner scanner=new Scanner(System.in);

		Session s1=sft.openSession();
		
		Criteria ct=s1.createCriteria(Product.class);	//Criteria has no queries like Queries did using HQL. Criteria is only for Retrieval, no update, no delete
		
		System.out.print("1.DisplayAll(pcost>400)\n2.DisplayAllPName\n3.DisplayPnamePcost\nEnter choice:");
		int choice1=scanner.nextInt();
		if(choice1==1)
		{
			ct.addOrder(Order.desc("productCost"));
			ct.add(Restrictions.gt("productCost", 400f));
			
			List<Product> prdList=ct.list();		//Product because we need the entire thing....from means entire thing(no selection)
			System.out.println("pId\tpName\t  pCost\t  Description");
			System.out.println("------------------------------------------------");
			for(Product p1:prdList)
			{
				System.out.println(p1.getProductId()+"\t"+p1.getProductName()+"\t  "+p1.getProductCost()+"\t  "+p1.getDescription());
			}
		}
		else if(choice1==2) {
			ct.setProjection(Projections.property("productName"));				//Projection for one single property
			List<String> list=ct.list();						//Used String because only one property for list. select from means selection
			System.out.println("pName");
			System.out.println("---------------");
			for(String str:list)
			{
				System.out.println(str);
			}
		}
		
		else if(choice1==3)
		{
			ProjectionList projectionList=Projections.projectionList();			//Projection for multiple properties
			projectionList.add(Projections.property("productName"));
			projectionList.add(Projections.property("productCost"));
			ct.setProjection(projectionList);
			List<Object []> list=ct.list();			////Object[] array bcoz only 2 properties needed from Product, if all needed we would use Product(className)
			System.out.println("pName\tpCost");
			System.out.println("------------------");
			for(Object obj[]:list)
			{
				System.out.println(obj[0]+"\t"+obj[1]);
			}
		}
		else
			System.out.println("Wrong choice!");
			scanner.close();
		sft.close();
	}

}
