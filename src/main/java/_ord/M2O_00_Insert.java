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
					Timestamp.valueOf("2021-07-01 00:35:31"), Timestamp.valueOf("2021-07-07 21:34:28"),
					BigDecimal.valueOf(1.0), BigDecimal.valueOf(540.0));

			RentItemBean rentItem2 = new RentItemBean(null, "R", "50004", "E", 2, "555", 1, 3, BigDecimal.valueOf(1.0),
					Timestamp.valueOf("2021-07-01 00:35:31"), Timestamp.valueOf("2021-07-07 21:34:28"),
					BigDecimal.valueOf(1.0), BigDecimal.valueOf(540.0));

			ReservationBean reservation1 = new ReservationBean(null, null, null, null, null, null, null, null);

			CommentBean commentBean1 = new CommentBean(null, null, null, null, null, null);

			ProblemBean problemBean1 = new ProblemBean(null, 00000000001, "historymylove@yahoo.com.tw", "0996325841",
					"RENT", "請問租借也可以貨到付款嗎?", null, null, null, null, null);

			ProductBean productBean1 = new ProductBean(null, null, "可愛的輪椅", null, null, null, 5, null, null, null);
			Set<ProblemBean> problem = new LinkedHashSet<>(Arrays.asList(problemBean1));
			Set<RentItemBean> rentItems = new LinkedHashSet<>(Arrays.asList(rentItem1, rentItem2));
			Set<ProductBean> product = new LinkedHashSet<>(Arrays.asList(productBean1));
			OrdBean ordBean = new OrdBean(null, "B", Timestamp.valueOf("2021-07-01 00:35:31"), "史馬遷", "台北",
					"台北市新莊區化成路10號", "0986547214", BigDecimal.valueOf(13000.0), "\\N", "線上刷卡", "宅配", "\\N",
					Timestamp.valueOf("2021-07-07 21:34:28"), null, rentItems, problem);

			Set<OrdBean> orders = new LinkedHashSet<>(Arrays.asList(ordBean));
			Set<ReservationBean> reservations = new LinkedHashSet<>(Arrays.asList(reservation1));
			Set<CommentBean> comments = new LinkedHashSet<>(Arrays.asList(commentBean1));

			CustomerBean customerBean = new CustomerBean(null, "account", null, null, null, null, null, null, null,
					null, null, null, null, null, null, orders, reservations, comments, problem);
			RentProductBean rentProduct1 = new RentProductBean(null, null, null, null, null, null, null, null, null,
					null, rentItems, reservations, comments);
			Set<RentProductBean> rentProducts = new LinkedHashSet<>(Arrays.asList(rentProduct1));

			PromotionBean promotionBean = new PromotionBean(null, null, null, null, null, null, null, null, rentItems,
					rentProducts, product);
			rentItem1.setOrdBean(ordBean);
			rentItem2.setOrdBean(ordBean);
			rentItem1.setPromotionBean(promotionBean);
			rentItem2.setPromotionBean(promotionBean);
			rentItem1.setRentProductBean(rentProduct1);
			rentItem2.setRentProductBean(rentProduct1);

			commentBean1.setCustomerBean(customerBean);

			ordBean.setCustomerBean(customerBean);
			reservation1.setRentProductBean(rentProduct1);
			reservation1.setCustomerBean(customerBean);
			problemBean1.setCustomerBean(customerBean);
			problemBean1.setOrdBean(ordBean);
			productBean1.setPromotionBean(promotionBean);
			session.persist(customerBean);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
			}
		}
	}
}
