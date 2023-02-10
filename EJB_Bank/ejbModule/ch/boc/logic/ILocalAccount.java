package ch.boc.logic;

import java.util.UUID;

import javax.ejb.Local;

@Local
public interface ILocalAccount {
	UUID getCode();

	double deposit(double amount);

	double withdraw(double amount);

	double checkBalance();

	void activate();

	void notactive();

	boolean isActive();
}
