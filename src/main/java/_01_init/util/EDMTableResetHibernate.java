package _01_init.util;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _02_customerService.model.PromotionBean;
import _03_rent.model.RentProductBean;
import _04_shop.model.ProductBean;
import _05_customer.model.CustomerBean;
import _06_order.model.OrdBean;
import _07_productType.model.ProductTypeBean;

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {

		String line = "";

		int count = 0;
		System.out.println("==================刪除表格=====================");
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			File file = new File("data/customer.dat");
//			 由"data/customer.dat"逐筆讀入customer表格內的初始資料，然後依序新增到customer表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					CustomerBean cb = new CustomerBean();
//					Blob blob = SystemUtils2018.fileToBlob(token[6].trim());
					cb.setAccount(token[0].trim());// 會員帳號
					cb.setPassword(token[1].trim());// 會員密碼
					cb.setAddress(token[2].trim());// 地址
					cb.setBirthday(Date.valueOf(token[3].toString().trim()));// 出生日期
					cb.setCity(token[4].trim());// 通訊城市
					cb.setCustName(token[5].trim());// 會員姓名
					cb.setCustomerImage(null);// 會員圖片
					cb.setEmail(token[7].trim());// 電子信箱
					cb.setFileName(token[8].trim());// 圖片名稱
					cb.setGender(token[9].trim());// 性別
					cb.setIdNumber(token[10].trim());// 會員身份證字號
					cb.setMimeType(token[11].trim());// 圖片類型
					cb.setNickName(token[12].trim());// 暱稱
					cb.setPhone(token[13].trim());// 連絡電話
					session.save(cb);
					count++;
					System.out.println("新增Customer紀錄成功，共新增" + count + "筆紀錄");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("Customer資料新增成功");
			}

			// 2. Ord表格
			// 由"data/Ord.txt"逐筆讀入Ord表格內的初始資料，
			// 然後依序新增到Ord表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/Ord.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					OrdBean ob = new OrdBean();
					ob.setCategory(token[0]);
					ob.setOrdId(Integer.parseInt(token[1]));
					ob.setDelivery(token[2]);
					ob.setOrdTot(new BigDecimal(token[3]));
					ob.setOrderDate(Timestamp.valueOf(token[4]));
					ob.setOrderMark(null);
					ob.setPayment(token[6]);
					ob.setReciAddress(token[7]);
					ob.setReciCity(token[8]);
					ob.setReciName(token[9]);
					ob.setReciPhone(token[10]);
					ob.setShipDate(Timestamp.valueOf(token[11]));

					session.save(ob);
					count++;
					System.out.println("新增Ord紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("Ord表格資料新增成功");
			}

			// 3. product表格
			// 由"data/product.dat"逐筆讀入product表格內的初始資料，
			// 然後依序新增到product表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/product.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					ProductBean pb = new ProductBean();
					pb.setProdId(Integer.parseInt(token[0]));
					pb.setClassify(token[1]);
					pb.setCoverImage(null);
					pb.setFileName(token[3]);
					pb.setMimeType(token[4]);
					pb.setPrice(new BigDecimal(token[5]));
					pb.setProdName(token[6]);
					pb.setProductTypeBean(null);
					pb.setPromoteId(null);
					pb.setStock(Integer.parseInt(token[9]));
					pb.setPromotionBean(null);

					session.save(pb);
					count++;
					System.out.println("新增product紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("product表格資料新增成功");
			}

			// 4. promotion表格
			// 由"data/promotion.dat"逐筆讀入promotion表格內的初始資料，
			// 然後依序新增到promotion表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/promotion.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					PromotionBean ptb = new PromotionBean();
					ptb.setPromoteId(Integer.parseInt(token[0]));
					ptb.setDiscountCode(token[1]);
					ptb.setPromoContent(token[2]);
					ptb.setPromoEndDate(Timestamp.valueOf(token[3]));
					ptb.setPromoStartDate(Timestamp.valueOf(token[4]));
					ptb.setPromoTag(token[5]);
					ptb.setPromotion(token[6]);

					session.save(ptb);
					count++;
					System.out.println("新增promotion紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("promotion表格資料新增成功");
			}

			// 5. protype表格
			// 由"data/protype.dat"逐筆讀入protype表格內的初始資料，
			// 然後依序新增到protype表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/protype.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					ProductTypeBean ptypeb = new ProductTypeBean();
					ptypeb.setProdType(token[0]);
					ptypeb.setProdName(token[1]);
					session.save(ptypeb);
					count++;
					System.out.println("新增protype紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("protype表格資料新增成功");
			}

			// 6. rentproduct表格
			// 由"data/rentproduct.dat"逐筆讀入rentproduct表格內的初始資料，
			// 然後依序新增到rentproduct表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/rentproduct.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					RentProductBean rpb = new RentProductBean();
					rpb.setProdId(Integer.parseInt(token[0]));
					rpb.setClassify(token[1]);
					rpb.setCoverImage(null);
					rpb.setFileName(token[3]);
					rpb.setMimeType(token[4]);
					rpb.setPrice(new BigDecimal(token[5]));
					rpb.setProdName(token[6]);
					rpb.setProductTypeBean(null);
					rpb.setSerialNumber(token[8]);
					rpb.setStock(Integer.parseInt(token[9]));
					
					
					session.save(rpb);
					count++;
					System.out.println("新增rentproduct紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("rentproduct表格資料新增成功");
		}
			 catch (Exception ex) {
				ex.printStackTrace();
			}
			tx.commit();
		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}
		factory.close();
	}

}