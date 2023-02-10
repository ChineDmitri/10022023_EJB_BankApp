package ch.boc.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.ejb.Stateless;

@Stateless(name = "account")
public class AccountEJBImpl implements ILocalAccount, IRemoteAccount {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	private final UUID code = UUID.randomUUID();
	private double balance;
	private final LocalDateTime dateCreate;
	private boolean isActive;

	public AccountEJBImpl() {
		this.balance = 0;
		this.isActive = false;
		this.dateCreate = LocalDateTime.now();
	}

	@Override
	public double deposit(double amount) {
		// TODO Auto-generated method stub
		this.balance += amount;
		return this.balance;
	}

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub

		if (this.balance < 0) {
			return 0;
		}

		this.balance -= amount;

		return this.balance;
	}

	@Override
	public double checkBalance() {
		// TODO Auto-generated method stub
		return this.balance;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

		this.isActive = true;

	}

	@Override
	public void notactive() {
		// TODO Auto-generated method stub
		this.isActive = false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

	public UUID getCode() {
		return code;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

}
