package org.academiadecodigo.javabank.domain;

public class MenuMain {

    public static void main(String[] args) {
        PromptMenu askCostumerNumber = new PromptMenu();
        askCostumerNumber.askCostumerNumber();
        askCostumerNumber.mainMenu();
    }

}
