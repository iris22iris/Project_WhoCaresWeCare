package com.web.store.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.service.CustomerService;

@Component
public class CustomerValidator implements Validator {
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,12})";
	private static final String PHONE_PATTERN = "(09)+[\\d]{8}";
	private static final String EMAIL_PATTERN = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
	private Pattern pattern = null;
	private Matcher matcher = null;
	
	@Override
	public boolean supports(Class<?> clazz) {
		boolean b = CustomerBean.class.isAssignableFrom(clazz);
		return b;
	}

	@Override
	public void validate(Object target, Errors errors) {

		CustomerBean cb = (CustomerBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "customer.account.not.empty", "帳號欄位不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "custName", "customer.custName.not.empty", "姓名欄位不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "", "生日欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "customer.email.not.empty", "Email欄位不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "customer.phone.not.empty", "手機欄位不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "customer.password.not.empty", "密碼欄位不能空白");
		if (cb.getAccount().length() < 5 && cb.getAccount().length() > 0) {
			errors.rejectValue("account", "", "帳號欄不能小於五個字元");
		}
		

		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(cb.getPassword());
		if (!matcher.matches() && cb.getPassword().length() > 0) {
			errors.rejectValue("password", "", "密碼至少含各一個大小寫字母、數字與!@#$%!^'\\\"，且長度不能小於八個");
		}
	
		
		
		pattern = Pattern.compile(PHONE_PATTERN);
		matcher = pattern.matcher(cb.getPhone());
		if (!matcher.matches() && cb.getPhone().length() > 0)  {
			errors.rejectValue("phone", "", "手機輸入有誤，請重新輸入");
		}
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(cb.getEmail());
		if (!matcher.matches() && cb.getEmail().length() > 0) {
			errors.rejectValue("email", "", "信箱輸入有誤，請重新輸入");
		}
	}

}
