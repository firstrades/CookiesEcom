package ecom.Interface.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.model.Order;
import ecom.model.OrderedItems;
import ecom.model.User;

public interface OrderInterface {

	public void setFranchiseCommission(User user, Order order);
	public void setDistributorFranchiseCommission(User user, Order order);
	
	public void returnFranchiseCommission(User user, Order order);
	public void returnDistributorFranchiseCommission(User user, Order order);
	
	public void bankTransaction(User user, Order order, HttpServletRequest request, HttpServletResponse response,
			String orderId) throws ServletException, IOException;
	
	public boolean setOrderSuccess(String trnxId);
	
	public boolean setOrderPaid(String trnxId);
	
	public List<OrderedItems> getJustOrderedItems(String trnxId);
	
	
	
	
}
