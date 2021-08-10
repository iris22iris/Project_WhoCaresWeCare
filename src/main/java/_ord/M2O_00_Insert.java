package _ord;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class M2O_00_Insert {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			RentItemBean rentItemBean1 = new RentItemBean(null, "R", "50004", "E", "1", 1, 3, BigDecimal.valueOf(1.0), 
					Timestamp.valueOf("2021-07-01 00:35:31"), Timestamp.valueOf("2021-07-07 21:34:28"), BigDecimal.valueOf(1.0), BigDecimal.valueOf(540.0));
			
			RentItemBean rentItemBean2 = new RentItemBean(null, "R", "50004", "E", "1", 1, 3, BigDecimal.valueOf(1.0),
					Timestamp.valueOf("2021-07-01 00:35:31"), Timestamp.valueOf("2021-07-07 21:34:28"), BigDecimal.valueOf(1.0), BigDecimal.valueOf(540.0));
			
			Set<RentItemBean> rentItems = new LinkedHashSet<>(Arrays.asList(rentItemBean1, rentItemBean2));
			
			OrdBean ordBean = new OrdBean(null, "B", Timestamp.valueOf("2021-07-01 00:35:31"), "103", "史馬遷", "台北", "台北市新莊區化成路10號",
					"0986547214", BigDecimal.valueOf(13000.0), "\\N", "線上刷卡", "宅配", "\\N", Timestamp.valueOf("2021-07-07 21:34:28"), rentItems);
			
			rentItemBean1.setOrdBean(ordBean);
			rentItemBean2.setOrdBean(ordBean);
			
			session.persist(ordBean);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
			}
		}
	}

}