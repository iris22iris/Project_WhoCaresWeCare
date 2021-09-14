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
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._02_customerService.ProblemSelectBean;
import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.FavoriteBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.model._05_customer.CitySelectBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.model._07_productType.ProductTypeBean;

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
					cb.setCustId(Integer.parseInt(token[0].trim()));// 會員編號
					cb.setAccount(token[13].trim());// 會員帳號
					cb.setPassword(token[1].trim());// 會員密碼
					cb.setAddress(token[2].trim());// 地址
					cb.setBirthday(Date.valueOf(token[3].toString().trim()));// 出生日期
					cb.setCity(token[4].trim());// 通訊城市
					cb.setCustName(token[5].trim());// 會員姓名
					Blob blob = SystemUtils2018.fileToBlob("data/member.jpg");
					cb.setCustomerImage(blob);// 會員圖片
					cb.setEmail(token[7].trim());// 電子信箱
					cb.setFileName(token[8].trim());// 圖片名稱
					cb.setGender(token[9].trim());// 性別
					cb.setIdNumber(token[10].trim());// 會員身份證字號
					cb.setMimeType(token[11].trim());// 圖片類型
					cb.setNickName(token[12].trim());// 暱稱
					cb.setPhone(token[14].trim());// 連絡電話
					session.merge(cb);
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
			try (FileInputStream fis = new FileInputStream("data/ord.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					OrdBean ob = new OrdBean();
					ob.setOrdPK(new OrdPK(token[0], Integer.parseInt(token[1])));
					ob.setDelivery(token[2]);
					ob.setOrdTotal(new BigDecimal(token[3]));
					ob.setOrderDate(Timestamp.valueOf(token[4]));
					java.sql.Clob clob = SystemUtils2018.fileToClob("data/orderMark.txt");
					ob.setOrderMark(clob);
					ob.setPayment(token[6]);
					ob.setOrderStatus(token[13]);
					ob.setReciAddress(token[7]);
					ob.setReciCity(token[8]);
					ob.setReciName(token[9]);
					ob.setReciPhone(token[10]);
					ob.setShipDate(Timestamp.valueOf(token[11]));
					ob.setCustomerBean(new CustomerBean(Integer.parseInt(token[12])));

					session.merge(ob);
					count++;
					System.out.println("新增Ord紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("Ord表格資料新增成功");
			}

			// 3. protype表格
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
					session.merge(ptypeb);
					count++;
					System.out.println("新增protype紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("protype表格資料新增成功");
			}

			// 4. product表格
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
					Blob blob1 = SystemUtils2018.fileToBlob("data/product/" + token[3]);
					Blob blob2 = SystemUtils2018.fileToBlob("data/product/forProductPage_2.png");
					Blob blob3 = SystemUtils2018.fileToBlob("data/product/forProductPage_3.png");
					pb.setCoverImage1(blob1);
					pb.setCoverImage2(blob2);
					pb.setCoverImage3(blob3);
					pb.setFileName(token[3]);
					pb.setMimeType(token[4]);
					pb.setPrice(new BigDecimal(token[5]));
					pb.setProdName(token[6]);
					java.sql.Clob clob = SystemUtils2018.fileToClob("data/productDescription.txt");
					pb.setDescription(clob);
//					pb.setPromoteId(null);
					pb.setStock(Integer.parseInt(token[8]));
					pb.setProductTypeBean(new ProductTypeBean(token[9]));

					session.merge(pb);
					count++;
					System.out.println("新增product紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("product表格資料新增成功");
			}

			// 5. promotion表格
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
					ptb.setDiscount(new BigDecimal(token[1]));
					ptb.setDiscountCode(token[2]);
					ptb.setPromoContent(token[3]);
					ptb.setPromoEndDate(Timestamp.valueOf(token[4]));
					ptb.setPromoStartDate(Timestamp.valueOf(token[5]));
					ptb.setPromoTag(token[6]);
					ptb.setPromotion(token[7]);

					session.merge(ptb);
					count++;
					System.out.println("新增promotion紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("promotion表格資料新增成功");
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
					Blob blob1 = SystemUtils2018.fileToBlob("data/product/" + token[3]);
					Blob blob2 = SystemUtils2018.fileToBlob("data/product/forProductPage_2.png");
					Blob blob3 = SystemUtils2018.fileToBlob("data/product/forProductPage_3.png");
					rpb.setCoverImage1(blob1);
					rpb.setCoverImage2(blob2);
					rpb.setCoverImage3(blob3);
					rpb.setFileName(token[3]);
					rpb.setMimeType(token[4]);
					rpb.setPrice(new BigDecimal(token[5]));
					rpb.setProdName(token[6]);
					rpb.setSerialNumber(token[8]);
					rpb.setStock(Integer.parseInt(token[9]));
					java.sql.Clob clob = SystemUtils2018.fileToClob("data/productDescription.txt");
					rpb.setDescription(clob);
					rpb.setProductTypeBean(new ProductTypeBean(token[10]));

					session.merge(rpb);
					count++;
					System.out.println("新增rentproduct紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("rentproduct表格資料新增成功");
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// 7.cityselect表格
			// 由"data/cityselect.dat"逐筆讀入cityselect表格內的初始資料，
			// 然後依序新增到cityselect表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/cityselect.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					String[] token = line.split("\\|");
					CitySelectBean cs = new CitySelectBean();
					cs.setId(Integer.parseInt((token[0])));
					cs.setGroupCity(token[1]);
					cs.setSortCity(token[2]);
					cs.setCity(token[3]);

					session.merge(cs);
					count++;
					System.out.println("新增cityselect紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("cityselect表格資料新增成功");
			}

			// 8. buyitem表格
			// 由"data/buyitem.dat"逐筆讀入buyitem表格內的初始資料，
			// 然後依序新增到buyitem表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/buyitem.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					BuyItemBean bib = new BuyItemBean();
					bib.setBuyItemPK(
							new BuyItemPK(new OrdPK(token[0], Integer.parseInt(token[1])), Integer.parseInt(token[2])));
//					bib.setDiscount(new BigDecimal(token[3]));
//					bib.setDiscountCode(token[4]);
					bib.setItemSum(new BigDecimal(token[3]));
//					bib.setProdId(Integer.parseInt(token[6]));
					bib.setProdQTY(Integer.parseInt(token[4]));
					bib.setProductBean(new ProductBean(Integer.parseInt(token[5])));
					bib.setProductTypeBean(new ProductTypeBean(token[6]));
//					bib.setPromotionBean(new PromotionBean(Integer.parseInt(token[7])));

					session.merge(bib);
					count++;
					System.out.println("新增buyitem紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("buyitem表格資料新增成功");
			}

			// 9. rentitem表格
			// 由"data/rentitem.dat"逐筆讀入rentitem表格內的初始資料，
			// 然後依序新增到rentitem表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/rentitem.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					RentItemBean rib = new RentItemBean();
					rib.setRentItemPK(new RentItemPK(new OrdPK(token[0], Integer.parseInt(token[1])),
							Integer.parseInt(token[2])));
					rib.setDiscount(new BigDecimal(token[3]));
					rib.setDiscountCode(token[4]);
					rib.setProdTotal(new BigDecimal(token[3]));
//					rib.setProdId(Integer.parseInt(token[5]));
					rib.setProdQty(Integer.parseInt(token[5]));
					rib.setRentPeriod(Integer.parseInt(token[6]));
					rib.setReturnDate(Timestamp.valueOf(token[7]));
					rib.setStartDate(Timestamp.valueOf(token[8]));
					rib.setRentProductBean(new RentProductBean(Integer.parseInt(token[12]), token[13]));

					session.merge(rib);
					count++;
					System.out.println("新增rentitem紀錄成功，共新增" + count + "筆記錄");
				}
				session.flush();
				System.out.println("rentitem表格資料新增成功");
			}

			// 10. reservation表格
			// 由"data/reservation.dat"逐筆讀入reservation表格內的初始資料，
			// 然後依序新增到reservation表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/reservation.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					ReservationBean rb = new ReservationBean();
					rb.setReservationId(Integer.parseInt(token[0]));
					rb.setCategory(token[1]);
					rb.setClassify(token[2]);
					rb.setProdId(Integer.parseInt(token[3]));
					rb.setReserveDate(Timestamp.valueOf(token[4]));
					rb.setSerialNumber(token[5]);
					rb.setWaitNum(Integer.parseInt(token[6]));
					rb.setWaitType(null);
//					rb.setCustomerBean(Integer.parseUnsignedInt(new CustomerBean(token[8])));

					session.merge(rb);
					count++;
					System.out.println("新增reservation紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("reservation表格資料新增成功");
			}

			// 11. comment表格
			// 由"data/comment.dat"逐筆讀入comment表格內的初始資料，
			// 然後依序新增到comment表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/comment.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					CommentBean cb = new CommentBean();
					cb.setCommentId(Integer.parseInt(token[0]));
					cb.setClassify(token[1]);
					java.sql.Clob clob = SystemUtils2018.fileToClob("data/comment.txt");
					cb.setComment(clob);
					cb.setCommentDate(Timestamp.valueOf(token[3]));
					cb.setRate(Integer.parseInt(token[4]));
					cb.setVisits(Integer.parseInt(token[5]));
					cb.setCustomerBean(new CustomerBean(Integer.parseInt(token[6]), null, null, null, null, null, null,
							null, null, null, null, null, null, null, null));

					cb.setProductBean(new ProductBean(null, Integer.parseInt(token[7]), null, null, null, null, null,
							null, null, null, null, null, null));

					cb.setRentProductBean(new RentProductBean(Integer.parseInt(token[8]), token[9], null, null, null,
							null, null, null, null, null, null, null, null, null, null, null));

					session.merge(cb);
					count++;
					System.out.println("新增comment紀錄成功，共新增" + count + "筆記錄");
				}
				session.flush();
				System.out.println("comment表格資料新增成功");
			}

			// 12. problem表格
			//由"data/problem.dat"逐筆讀入problem表格內的初始資料，
			//然後依序新增到problem表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/problem.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {

					String[] token = line.split("\\|");
					ProblemBean pb = new ProblemBean();
					pb.setusId(Integer.parseInt(token[0]));
					Blob blob = SystemUtils2018.fileToBlob("data/product/A0001.jpg");
					pb.setAttachFile(blob);
					pb.setContent(token[2]);
					pb.setEmail(token[3]);
					pb.setFormDate(Timestamp.valueOf(token[4]));
					pb.setOrdId(token[5]);
					pb.setPhone(token[6]);
					pb.setProblemType(token[7]);
					pb.setProcessState(token[8]);
					pb.setReplyContent(token[9]);
					pb.setReplyDate(Timestamp.valueOf(token[10]));
					pb.setFileName(token[11]);
					pb.setAccount(token[12]);

					session.merge(pb);
					count++;
					System.out.println("新增problem紀錄成功，共新增" + count + "筆記錄");
				}
				session.flush();
				System.out.println("problem表格資料新增成功");
			}

//			// 13. favorite表格
//			// 由"data/favorite.dat"逐筆讀入favorite表格內的初始資料，
//			// 然後依序新增到favorite表格中
//			try (FileInputStream fis = new FileInputStream("data/favorite.dat");
//					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
//					BufferedReader br = new BufferedReader(isr0);) {
//				while ((line = br.readLine()) != null) {
//
//					String[] token = line.split("\\|");
//					FavoriteBean fb = new FavoriteBean();
//					fb.setFK_Customer_ID(Integer.parseInt(token[1]));
//					fb.setFK_Product_ID(Integer.parseInt(token[2]));
//
//					session.merge(fb);
//					count++;
//				}
//				session.flush();
//				System.out.println("favorite表格資料新增成功");
//			}

			// 14.problemselect表格
			// 由"data/problemselect.dat"逐筆讀入problemselect表格內的初始資料，
			// 然後依序新增到problemselect表格中
			count = 0;
			try (FileInputStream fis = new FileInputStream("data/problemselect.dat");
					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr0);) {
				while ((line = br.readLine()) != null) {
					String[] token = line.split("\\|");
					ProblemSelectBean psb = new ProblemSelectBean();
					psb.setId(Integer.parseInt((token[0])));
					psb.setProblemType(token[1]);
					psb.setQroupPb(token[2]);
					psb.setSortPb(token[3]);
					session.merge(psb);
					count++;
					System.out.println("新增cityselect紀錄成功，共新增" + count + "筆記錄:" + token[1]);
				}
				session.flush();
				System.out.println("problemselect表格資料新增成功");
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
