package org.academiadecodigo.javabank.model.account;

public interface AccountInterface {

    /**
     * @see Account#getBalance()
     */
    public double getBalance();

    /**
     * @see Account#getAccountType()
     */
    public abstract AccountType getAccountType();

    /**
     * Credits account if possible
     *
     * @param amount the amount to credit
     * @see Account#credit(double)
     */
    public void credit(double amount);

    /**
     * Debits the account if possible
     *
     * @param amount the amount to debit
     * @see Account#canDebit(double)
     */
    public void debit(double amount);

    /**
     * @see Account#canCredit(double)
     */
    public boolean canCredit(double amount);

    /**
     * @see Account#canDebit(double)
     */
    public boolean canDebit(double amount);

    /**
     * @see Account#canWithdraw()
     */
    public boolean canWithdraw();


}
