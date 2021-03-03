package query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.DeptBean;
import model.hibernate.HibernateUtil;

public class TestLoadAndGet {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		DeptBean dept10 = session.get(DeptBean.class, 10);
		System.out.println("dept10="+dept10);
		
		DeptBean dept20 = session.load(DeptBean.class, 20);
		System.out.println("dept20="+dept20);
		
		//difference1
		DeptBean deptA1 = session.get(DeptBean.class, 100);
		if(deptA1==null) {
			System.out.println("deptA1 is null");
		} else {
			System.out.println("deptA1 is not null");
		}
		
		DeptBean deptA2 = session.load(DeptBean.class, 200);
		if(deptA2==null) {
			System.out.println("deptA2 is null");
		} else {
			System.out.println("deptA2 is not null");
		}

		//difference2	//ObjectNotFoundException
//		System.out.println("deptA1="+deptA1);
//		System.out.println("deptA2="+deptA2);
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
		
		//difference3	//LazyInitializationException
		System.out.println("deptA1="+deptA1);
		System.out.println("deptA2="+deptA2);

	}
}
