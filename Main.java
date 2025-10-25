public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://bytexldb.com:5051/db_442jha2b9";
        String USER = "user_442jha2b9";
        String PASSWORD = "p442jha2b9";

        try {
            StudentController controller = new StudentController(URL, USER, PASSWORD);
            StudentView view = new StudentView(controller);
            view.menu();
            controller.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
