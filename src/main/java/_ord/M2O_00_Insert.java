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
			RentItemBean rentItem1 = new RentItemBean(null, "R", "50004", "E", 1, "555", 1, 3, BigDecimal.valueOf(1.0), 
					Timestamp.valueOf("2021-07-01 00:35:31"), Timestamp.valueOf("2021-07-07 21:34:28"), BigDecimal.valueOf(1.0), BigDecimal.valueOf(540.0));
			
			RentItemBean rentItem2 = new RentItemBean(null, "R", "50004", "E", 2, "555", 1, 3, BigDecimal.valueOf(1.0),
					Timestamp.valueOf("2021-07-01 00:35:31"), Timestamp.valueOf("2021-07-07 21:34:28"), BigDecimal.valueOf(1.0), BigDecimal.valueOf(540.0));
			
			ReservationBean reservation1 = new ReservationBean(null, null, null, null, null, null, null, null);
			
			CommentBean commentBean1 = new CommentBean(null, null, null, null, null, null);
			
			Set<RentItemBean> rentItems = new LinkedHashSet<>(Arrays.asList(rentItem1, rentItem2));
			OrdBean ordBean = new OrdBean(null, "B", Timestamp.valueOf("2021-07-01 00:35:31"), "史馬遷", "台北", "台北市新莊區化成路10號",
					"0986547214", BigDecimal.valueOf(13000.0), "\\N", "線上刷卡", "宅配", "\\N", Timestamp.valueOf("2021-07-07 21:34:28"), null, rentItems);
			
			Set<OrdBean> orders = new LinkedHashSet<>(Arrays.asList(ordBean));
			Set<ReservationBean> reservations = new LinkedHashSet<>(Arrays.asList(reservation1));
			Set<CommentBean> comments = new LinkedHashSet<>(Arrays.asList(commentBean1));
			
			CustomerBean customerBean = new CustomerBean(null, "account", null, null, null, null, null, null, null, null, null, null, null, null, null, orders, reservations, comments);
			RentProductBean rentProduct1 = new RentProductBean(null, null, null, null, null, null, null, null, null, null, rentItems, reservations, comments);
			Set<RentProductBean> rentProducts = new LinkedHashSet<>(Arrays.asList(rentProduct1));
			
			PromotionBean promotionBean = new PromotionBean(null, null, null, null, null, null, null, null, rentItems, rentProducts);
			rentItem1.setOrdBean(ordBean);
			rentItem2.setOrdBean(ordBean);
			rentItem1.setPromotionBean(promotionBean);
			rentItem2.setPromotionBean(promotionBean);
			rentItem1.setRentProductBean(rentProduct1);
			rentItem2.setRentProductBean(rentProduct1);
			
			commentBean1.setCustomerBean(customerBean);
			commentBean1.setRentProductBean(rentProduct1);
			rentProduct1.setPromotionBean(promotionBean);
			
			ordBean.setCustomerBean(customerBean);
			reservation1.setRentProductBean(rentProduct1);
			reservation1.setCustomerBean(customerBean);
			
			session.persist(customerBean);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
			}
		}
		System.out.println("test");
		System.out.println("test2");
}
	}
			