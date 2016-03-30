package com.javatpoint_old;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AccountsClient 
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountsDao accountsDao = context.getBean("accountsDaoBean",AccountsDao.class);
		
		accountsDao.createAccount(15, "Jai Kumar", 41000);
		accountsDao.createAccount(20, "Rishi ", 35000);
		System.out.println("Accounts created");
		
		/*accountsDao.updateBalance(20, 50000);
		System.out.println("Account balance updated");
		*/
		
		/*List<Account> accounts = accountsDao.getAllAccounts();
		for (Account acc:accounts){
			System.out.println(acc.getAccountNumber() + " : " + acc.getOwner() + " (" + acc.getBalance() + ")");
		}*/
		
		//accountsDao.deleteAccount(111);
		//System.out.println("Account deleted");
		
	}
}
