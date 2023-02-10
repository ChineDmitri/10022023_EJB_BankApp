package ch.boc.logic;

import java.util.List;
import java.util.UUID;

import javax.ejb.Remote;

@Remote
public interface IRemoteBank {

	public void addAccount(AccountEJBImpl account);

	public AccountEJBImpl getAccount(UUID code);

	public boolean removeAccount(UUID code);

	public List<AccountEJBImpl> getAllAcount();

	boolean transaction(double amount, AccountEJBImpl source, AccountEJBImpl target);

}
