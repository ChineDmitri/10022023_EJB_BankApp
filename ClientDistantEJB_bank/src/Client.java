import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ch.boc.logic.AccountEJBImpl;
import ch.boc.logic.BankEJBImpl;
import ch.boc.logic.IRemoteAccount;
import ch.boc.logic.IRemoteBank;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IRemoteBank stubBank = lookupBank();
		IRemoteAccount stubAccount = lookupAccount();

		BankEJBImpl bank = new BankEJBImpl();

		AccountEJBImpl acc1 = new AccountEJBImpl();
		AccountEJBImpl acc2 = new AccountEJBImpl();

		bank.addAccount(acc1);
		bank.addAccount(acc2);

		acc1.activate();
		acc2.activate();

		acc1.deposit(25);
		acc2.deposit(50);

		System.out.println("Avant transaction : ");
		for (AccountEJBImpl acc : bank.getAllAcount()) {
			System.out.println("account : " + acc.getCode() + " Balance: "
					+ acc.checkBalance() + "\n");
		}

		bank.transaction(10, acc1, acc2);

		System.out.println("======== \nApres transaction : ");
		for (AccountEJBImpl acc : bank.getAllAcount()) {
			System.out.println("account : " + acc.getCode() + " Balance: "
					+ acc.checkBalance() + "\n");
		}

	}

	private static IRemoteBank lookupBank() {

		try {
			Hashtable<String, String> properiesJNDI = new Hashtable<>();
			properiesJNDI.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.wildfly.naming.client.WildFlyInitialContextFactory");
			properiesJNDI.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

			InitialContext ctx = new InitialContext(properiesJNDI);

			BankEJBImpl stub = (BankEJBImpl) ctx
					.lookup("ejb:/EJB_Bank/bank!ch.boc.logic.IRemoteBank");

			return stub;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static IRemoteAccount lookupAccount() {

		try {
			Hashtable<String, String> properiesJNDI = new Hashtable<>();
			properiesJNDI.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.wildfly.naming.client.WildFlyInitialContextFactory");
			properiesJNDI.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

			Context ctx = new InitialContext(properiesJNDI);

			IRemoteAccount stub = (IRemoteAccount) ctx
					.lookup("ejb:/EJB_Bank/account!ch.boc.logic.IRemoteAccount");

			return stub;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
