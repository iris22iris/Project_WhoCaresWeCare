package _01_init.util;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import _05_customer.model.CustomerBean;

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
					System.out.println("新增一筆Customer紀錄成功，共新增" + count + "筆紀錄");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("Customer資料新增成功");
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