import java.util.Scanner;

public class DoLogin {

    static boolean credentialCheck;
    static boolean exceptionCheck = true;

    public static void main(String[] args) {

        System.out.println("Для входа в систему просьба ввести Ваш Login, Password и Password confirmation: ");
        System.out.println("Для выхода из программы нажмите \"q\": ");

        do {
            try {
                checkCredentials(getLogin(),getConfirmPassword(getPassword()));
            } catch (WrongLoginException e) {
                System.err.println(e.getMessage());
                exceptionCheck = false;
            } catch (WrongPasswordException e) {
                System.err.println(e.getMessage());
                exceptionCheck = false;
            }

        } while (exceptionCheck == false || credentialCheck != true);
    }

    public static String getLogin () throws WrongLoginException {

        System.out.println("Enter Login: ");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        if (login.equals("q")) {
            System.out.println("Программа завершена.");
            System.exit(0);
        }
        if (!login.matches("\\w{1,20}+")) {
            throw new WrongLoginException("Login может содержать только латинские буквы, цифры и знак подчеркивания.\n" +
                    "Длина Login должна быть меньше 20 символов.");
        }
        return login;
    }

        public static String getPassword () throws WrongPasswordException {

            System.out.println("Enter Password: ");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();

            if (password.equals("q")) {
                System.out.println("Программа завершена.");
                System.exit(0);
            }
            if (!password.matches("\\w{1,20}+")) {
                throw new WrongPasswordException("Password может содержать только латинские буквы, цифры и знак подчеркивания.\n" +
                        "Длина password должна быть меньше 20 символов.");
            }
            return password;
        }

        public static String getConfirmPassword (String password) throws WrongPasswordException {
            System.out.println("Please, confirm Password: ");
            Scanner scanner = new Scanner(System.in);
            String confirmPassword = scanner.nextLine();
            if (confirmPassword.equals("q")) {
                System.out.println("Программа завершена.");
                System.exit(0);
            }
            if (!confirmPassword.equals(password)) {
                throw new WrongPasswordException("Password и confirmPassword не совпадают!");
            }
            return password;
        }

        public static boolean checkCredentials (String login, String password){
            String myLogin = "login";
            String myPass = "password";
            exceptionCheck = true;
            credentialCheck = false;
            if (login.equals(myLogin) && password.equals(myPass)) {
                System.out.println("Вход выполнен успешно. Спасибо!");
                credentialCheck = true;
            } else {
                System.out.println("Вы ввели неверные данные. Повторите попытку");
            }
            return credentialCheck;
        }
    }
