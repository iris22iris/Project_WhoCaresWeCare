package _ord;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="BuyItem")
public class BuyItemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String  		category;       //訂單類別
	private Integer			prodSerialNum;	//商品項次
	private String      	prodType;		//商品分類代碼
	private Integer     	prodQTY;	//商品數量
	private BigDecimal  	itemSum;		//單項總額
	private BigDecimal  	discount;		//折扣金額
	private BigDecimal		ordTotal;		//訂單總金額
	

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYITEM_ORDID_FK")
	private OrdBean ordBean;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "BUYTITEM_PROMOTEID_FK")
//	private PromotionBean promotionBean;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "BUYTITEM_PRODID_FK")
//	private BuyProductBean buyProductBean;	
		
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_DISCOUNTCODE_FK")
	private PromotionBean promotionBean;
	
	
	
	
	
}
