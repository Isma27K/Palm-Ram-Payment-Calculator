import java.util.ArrayList;
import java.util.List;

public class Fun {
    private static final double GRADE_A_MULTIPLIER = 1.0;
    private static final double GRADE_B_MULTIPLIER = 0.9;
    private static final double GRADE_C_MULTIPLIER = 0.8;

    private List<User> users;
    private List<Transaction> transactions;

    public Fun() {
        users = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public double calculatePayment(double currentPrice, double initialWeight, double finalWeight, String grade) {
        double netWeight = finalWeight - initialWeight;
        double netWeightInTons = netWeight / 1000.0; // Convert kg to tons
        double gradeMultiplier = getGradeMultiplier(grade);
        return currentPrice * Math.abs(netWeightInTons) * gradeMultiplier;
    }

    private double getGradeMultiplier(String grade) {
        switch (grade.toUpperCase()) {
            case "A":
                return GRADE_A_MULTIPLIER;
            case "B":
                return GRADE_B_MULTIPLIER;
            case "C":
                return GRADE_C_MULTIPLIER;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
    }

    public void addUser(String name, String plateNumber, String phoneNumber) {
        int id = users.size() + 1;
        User newUser = new User(id, name, plateNumber, phoneNumber);
        users.add(newUser);
    }

    public void addTransaction(int userId, double currentPrice, double initialWeight, double finalWeight, String grade) {
        int id = transactions.size() + 1;
        double payment = calculatePayment(currentPrice, initialWeight, finalWeight, grade);
        Transaction newTransaction = new Transaction(id, userId, currentPrice, initialWeight, finalWeight, grade, payment);
        transactions.add(newTransaction);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    // Inner classes for User and Transaction
    public static class User {
        private int id;
        private String name;
        private String plateNumber;
        private String phoneNumber;

        public User(int id, String name, String plateNumber, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.plateNumber = plateNumber;
            this.phoneNumber = phoneNumber;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getPlateNumber() { return plateNumber; }
        public String getPhoneNumber() { return phoneNumber; }
    }

    public static class Transaction {
        private int id;
        private int userId;
        private double currentPrice;
        private double initialWeight;
        private double finalWeight;
        private String grade;
        private double payment;

        public Transaction(int id, int userId, double currentPrice, double initialWeight, double finalWeight, String grade, double payment) {
            this.id = id;
            this.userId = userId;
            this.currentPrice = currentPrice;
            this.initialWeight = initialWeight;
            this.finalWeight = finalWeight;
            this.grade = grade;
            this.payment = payment;
        }

        public int getId() { return id; }
        public int getUserId() { return userId; }
        public double getCurrentPrice() { return currentPrice; }
        public double getInitialWeight() { return initialWeight; }
        public double getFinalWeight() { return finalWeight; }
        public String getGrade() { return grade; }
        public double getPayment() { return payment; }
    }
}
