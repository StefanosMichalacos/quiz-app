package stef.projects;

import java.util.Scanner;

class QuizApp {

    void startUp() {
        System.out.println("Hallo !");
        System.out.println("Welcome to the Java Quiz !");
        System.out.println("Do you already have an account ?");
        String answer;
        do {
            System.out.println("Press y if you do, else press n, then hit return.");
            answer = new Scanner(System.in).next();
        } while (!"y".equals(answer) && !"Y".equals(answer) && !"n".equals(answer) && !"N".equals(answer));
        if ("y".equals(answer) || "Y".equals(answer)){
            userSignUp();
        }else {
            userRegister();
        }
    }

    private void userSignUp() {
        System.out.println("Please enter your first name");
        String firstName = new Scanner(System.in).next();
        System.out.println("Please enter you password");
        String password = new Scanner(System.in).next();
        System.out.println(firstName+ " " +password);

        // we have to cross-reference firstName & password with db
        // if they match the we start the tests
    }

    private void userRegister() {
        String validation ;
        do {
            System.out.println("Please state your first name");
            String firstName = new Scanner(System.in).next();
            System.out.println("Please state your last name");
            String lastName = new Scanner(System.in).next();
            System.out.println("Please enter your email account");
            String email = new Scanner(System.in).next();
            String passwordFirstTry;
            String passwordSecondTry;
            do {
                System.out.println("Please choose a password");
                passwordFirstTry = new Scanner(System.in).next();
                System.out.println("Re-enter the password");
                passwordSecondTry = new Scanner(System.in).next();
            } while (!passwordFirstTry.equals(passwordSecondTry));
            String password = passwordFirstTry;
            System.out.println("These are what you entered \n\tFirst Name: " + firstName + "\n\tLast Name: " + lastName + "\n\tEmail: " + email + "\n\tPassword: " + password); //password is not gonna be shown
            System.out.println("Press n if they are not correct, else press any key and return to continue");
            validation = new Scanner(System.in).next();
        }while ("n".equals(validation) || "N".equals(validation));


        // enter these in the database
    }
}
