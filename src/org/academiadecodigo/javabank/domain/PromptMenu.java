package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.utils.Color;
import org.academiadecodigo.javabank.utils.Messages;

public class PromptMenu {

    private Prompt prompt;
    private String costumerNumber;
    private Messages message;
    private Account account;
    private AccountManager accountManager;


    public PromptMenu() {
        prompt = new Prompt(System.in, System.out);
        welcome();
        //askCostumerNumber();
    }

    public void welcome() {
        System.out.println("\n" + message.WELCOME + "\n");


    }

    public void askCostumerNumber() {
        StringInputScanner askCostumerNumber = new StringInputScanner();
        askCostumerNumber.setMessage(message.COSTUMER_NUMBER);
        costumerNumber = prompt.getUserInput(askCostumerNumber);
    }

    public void mainMenu() {
        String[] menuOptions = {"VIEW BALANCE", "MAKE DEPOSIT", "MAKE WITHDRAWAL", "OPEN ACCOUNT", "QUIT"};
        int amount;
        String messages = "Heeeeeee";
        StringInputScanner result = new StringInputScanner();
        //MenuInputScanner
        switch (menuMaker(menuOptions, messages)) {
            case 1:
                result.setMessage(message.TOTAL_BALANCE +  account.getBalance());
                break;
            //case 2:
                //1result.setMessage(message.DEPOSIT + prompt.getUserInput(amount));

        }

    }

    private int menuMaker(String[] menuOptions, String message){
        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage(message);
        return prompt.getUserInput(scanner);
    }







}
