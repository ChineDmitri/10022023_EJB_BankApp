package ch.boc.logic;

import java.util.UUID;

import javax.ejb.Remote;

@Remote
public interface IRemoteAccount {
	UUID getCode();

	double deposit(double amount);

	double withdraw(double amount);

	double checkBalance();

	void activate();

	void notactive();

	boolean isActive();
}
