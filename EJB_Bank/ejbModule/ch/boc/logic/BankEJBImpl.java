package ch.boc.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;

@Stateless(name = "bank")
public class BankEJBImpl implements ILocalBank, IRemoteBank {

	private List<AccountEJBImpl> listAccounts;

	public BankEJBImpl() {
		listAccounts = new ArrayList<AccountEJBImpl>();
	}

	@Override
	public void addAccount(AccountEJBImpl account) {
		// TODO Auto-generated method stub
		this.listAccounts.add(account);
	}

	@Override
	public AccountEJBImpl getAccount(UUID code) {
		// TODO Auto-generated method stub
		for (AccountEJBImpl account : listAccounts) {
			if (account.getCode().equals(code)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public boolean removeAccount(UUID code) {
		// TODO Auto-generated method stub
		for (AccountEJBImpl account : listAccounts) {
			if (account.getCode().equals(code)) {
				listAccounts.remove(account);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<AccountEJBImpl> getAllAcount() {
		// TODO Auto-generated method stub
		return listAccounts;
	}

	@Override
	public boolean transaction(double amount, AccountEJBImpl source,
			AccountEJBImpl target) {
		// TODO Auto-generated method stub

		if ((source.checkBalance() - amount) < 0)
			return false;

		target.deposit(amount);
		source.withdraw(amount);

		return true;
	}

	public List<AccountEJBImpl> getListAccounts() {
		return listAccounts;
	}

}
