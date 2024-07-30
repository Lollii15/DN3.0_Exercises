//Weekly assignment 01 (Design and principle)
//Exercise-1

public class Logger {

    // Private static instance of the class
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {
        // Initialization code here
    }

    // Public method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Example method to log messages
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
//Here’s a simple test class:
public class LoggerTest {

    public static void main(String[] args) {
        // Get the singleton instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        // Check if both instances are the same
        if (logger1 == logger2) {
            System.out.println("Both instances are the same.");
        } else {
            System.out.println("Different instances.");
        }

        // Use the logger
        logger1.log("This is a test message.");
        logger2.log("This is another test message.");
    }
}
//Exercise-2
//Creating an interface or an abstract class for Document.
public interface Document {
    void open();
}
//Implementing concrete classes for each document type: WordDocument, PdfDocument, and ExcelDocument.
public class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document.");
    }
}

public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document.");
    }
}

public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document.");
    }
}
//Creating an abstract class DocumentFactory with a method createDocument().
public abstract class DocumentFactory {
    public abstract Document createDocument();
}
//Creating concrete factory classes for each document type that extend DocumentFactory and implement the createDocument() method.
public class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

public class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

public class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
//Creating a test class to demonstrate the creation of different document types using the factory method.
public class FactoryMethodTest {
    public static void main(String[] args) {
        // Create a factory for Word documents
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDocument = wordFactory.createDocument();
        wordDocument.open();

        // Create a factory for PDF documents
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDocument = pdfFactory.createDocument();
        pdfDocument.open();

        // Create a factory for Excel documents
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDocument = excelFactory.createDocument();
        excelDocument.open();
    }
}
//Exercise-3
public class Computer {
    private final String CPU;
    private final int RAM;
    private final int Storage;
    private final String GPU;
    private final boolean isSSD;

    // Private constructor to be used by the Builder class
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GPU = builder.GPU;
        this.isSSD = builder.isSSD;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM=" + RAM +
                ", Storage=" + Storage +
                ", GPU='" + GPU + '\'' +
                ", isSSD=" + isSSD +
                '}';
    }

    // Static nested Builder class
    public static class Builder {
        private String CPU;
        private int RAM;
        private int Storage;
        private String GPU;
        private boolean isSSD;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(int RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(int Storage) {
            this.Storage = Storage;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setSSD(boolean isSSD) {
            this.isSSD = isSSD;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
//Creating a test class to demonstrate the creation of different configurations of Computer using the Builder pattern.
public class BuilderPatternTest {
    public static void main(String[] args) {
        // Build a basic Computer
        Computer basicComputer = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM(8)
                .setStorage(512)
                .build();

        System.out.println("Basic Computer: " + basicComputer);

        // Build a high-end Computer
        Computer gamingComputer = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM(32)
                .setStorage(1024)
                .setGPU("NVIDIA RTX 3080")
                .setSSD(true)
                .build();

        System.out.println("Gaming Computer: " + gamingComputer);
    }
}
//Exercise-4
//Creating an interface PaymentProcessor with a method processPayment().
public interface PaymentProcessor {
    void processPayment(double amount);
}
//Creating classes for different payment gateways with their own methods.
// Adaptee Class 1: StripePaymentGateway
public class StripePaymentGateway {
    public void charge(double amount) {
        System.out.println("Charging " + amount + " using Stripe.");
    }
}

// Adaptee Class 2: PayPalPaymentGateway
public class PayPalPaymentGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of " + amount + " through PayPal.");
    }
}
//Creating an adapter class for each payment gateway that implements PaymentProcessor and translates calls to the gateway-specific methods.
// Adapter for StripePaymentGateway
public class StripeAdapter implements PaymentProcessor {
    private StripePaymentGateway stripePaymentGateway;

    public StripeAdapter(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    @Override
    public void processPayment(double amount) {
        stripePaymentGateway.charge(amount);
    }
}

// Adapter for PayPalPaymentGateway
public class PayPalAdapter implements PaymentProcessor {
    private PayPalPaymentGateway payPalPaymentGateway;

    public PayPalAdapter(PayPalPaymentGateway payPalPaymentGateway) {
        this.payPalPaymentGateway = payPalPaymentGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalPaymentGateway.makePayment(amount);
    }
}
//Creating a test class to demonstrate the use of different payment gateways through the adapter.
public class AdapterPatternTest {
    public static void main(String[] args) {
        // Using Stripe payment gateway through its adapter
        StripePaymentGateway stripePaymentGateway = new StripePaymentGateway();
        PaymentProcessor stripeProcessor = new StripeAdapter(stripePaymentGateway);
        stripeProcessor.processPayment(150.75);

        // Using PayPal payment gateway through its adapter
        PayPalPaymentGateway payPalPaymentGateway = new PayPalPaymentGateway();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPalPaymentGateway);
        payPalProcessor.processPayment(200.50);
    }
}
//Exercise-5
//Creating an interface Notifier with a method send().
public interface Notifier {
    void send(String message);
}
//Create a class EmailNotifier that implements Notifier.
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
//Create an abstract decorator class NotifierDecorator that implements Notifier and holds a reference to a Notifier object.
public abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
    }
}
//Create concrete decorator classes such as SMSNotifierDecorator and SlackNotifierDecorator that extend NotifierDecorator.
// Concrete Decorator for SMS
public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Concrete Decorator for Slack
public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSlackMessage(message);
    }

    private void sendSlackMessage(String message) {
        System.out.println("Sending Slack message: " + message);
    }
}
//Create a test class to demonstrate sending notifications via multiple channels using decorators.
public class DecoratorPatternTest {
    public static void main(String[] args) {
        // Create a basic EmailNotifier
        Notifier emailNotifier = new EmailNotifier();
        
        // Decorate EmailNotifier with SMS functionality
        Notifier smsEmailNotifier = new SMSNotifierDecorator(emailNotifier);
        
        // Decorate EmailNotifier with Slack functionality
        Notifier slackEmailNotifier = new SlackNotifierDecorator(emailNotifier);

        // Decorate EmailNotifier with both SMS and Slack functionalities
        Notifier combinedNotifier = new SlackNotifierDecorator(new SMSNotifierDecorator(emailNotifier));
        
        // Send notifications using different decorators
        emailNotifier.send("Hello via Email");
        smsEmailNotifier.send("Hello via Email and SMS");
        slackEmailNotifier.send("Hello via Email and Slack");
        combinedNotifier.send("Hello via Email, SMS, and Slack");
    }
}
//Exercise-6
//Creating an interface Image with a method display().
public interface Image {
    void display();
}
//Creating a class RealImage that implements Image and simulates loading an image from a remote server.
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromServer();
    }

    private void loadImageFromServer() {
        System.out.println("Loading image: " + fileName + " from the server.");
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
//Creating a class ProxyImage that implements Image and holds a reference to RealImage.
//Implementing lazy initialization and caching in ProxyImage.
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
//Creating a test class to demonstrate the use of ProxyImage to load and display images.

public class ProxyPatternTest {
    public static void main(String[] args) {
        // Create a ProxyImage instance
        Image image1 = new ProxyImage("image1.jpg");
        
        // Display the image (should load from the server and then display)
        image1.display();
        
        // Display the image again (should use the cached version)
        image1.display();

        // Create another ProxyImage instance
        Image image2 = new ProxyImage("image2.jpg");
        
        // Display the second image (should load from the server and then display)
        image2.display();
    }
}
//Exercise-7
//Creating an interface Stock with methods to register, deregister, and notify observers.
public interface Stock {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
//Creating a class StockMarket that implements Stock and maintains a list of observers.
import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private List<Observer> observers;
    private double stockPrice;

    public StockMarket() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}
//Creating an interface Observer with a method update()
public interface Observer {
    void update(double stockPrice);
}
//Creating classes MobileApp and WebApp that implement Observer.
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(appName + " received stock price update: " + stockPrice);
    }
}

public class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(appName + " received stock price update: " + stockPrice);
    }
}
//Creating a test class to demonstrate the registration and notification of observers.
public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("MobileApp1");
        Observer mobileApp2 = new MobileApp("MobileApp2");
        Observer webApp1 = new WebApp("WebApp1");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(mobileApp2);
        stockMarket.registerObserver(webApp1);

        stockMarket.setStockPrice(100.00);
        stockMarket.setStockPrice(105.50);

        stockMarket.removeObserver(mobileApp2);

        stockMarket.setStockPrice(98.75);
    }
}
//Exercise-8
//Creating an interface PaymentStrategy with a method pay().
public interface PaymentStrategy {
    void pay(double amount);
}
//Creating classes CreditCardPayment and PayPalPayment that implement PaymentStrategy
// Concrete Strategy for Credit Card Payment
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expirationDate;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
        // Add actual payment processing logic here
    }
}

// Concrete Strategy for PayPal Payment
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
        // Add actual payment processing logic here
    }
}
//Creating classes CreditCardPayment and PayPalPayment that implement PaymentStrategy.
// Concrete Strategy for Credit Card Payment
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expirationDate;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
        // Add actual payment processing logic here
    }
}

// Concrete Strategy for PayPal Payment
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
        // Add actual payment processing logic here
    }
}
//Creating a class PaymentContext that holds a reference to PaymentStrategy and a method to execute the strategy.
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Setter to change the payment strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Method to execute the payment strategy
    public void pay(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("Payment strategy not set.");
        }
    }
}
//Creating a test class to demonstrate selecting and using different payment strategies.
public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Paying with Credit Card
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234567890123456", "John Doe", "123", "12/25");
        context.setPaymentStrategy(creditCardPayment);
        context.pay(250.75);

        // Paying with PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com", "password123");
        context.setPaymentStrategy(payPalPayment);
        context.pay(89.99);
    }
}
//Exercise-9
//Creating an interface Command with a method execute().
public interface Command {
    void execute();
}
//Creating classes LightOnCommand and LightOffCommand that implement Command.
// Command to turn on the light
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

// Command to turn off the light
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
//Creating a class RemoteControl that holds a reference to a Command and a method to execute the command.
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
//Creating a class Light with methods to turn on and off.
public class Light {
    public void on() {
        System.out.println("The light is on.");
    }

    public void off() {
        System.out.println("The light is off.");
    }
}
//Creating a test class to demonstrate issuing commands using the RemoteControl.
public class CommandPatternTest {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();

        // Create command objects
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Create invoker object
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();

        // Turn off the light
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
//Exercise-10
//Creating a class Student with attributes like name, id, and grade.
public class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
//Creating a class StudentView with a method displayStudentDetails().
public class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("Student:");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}
//Creating a class StudentController that handles the communication between the model and the view.
public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(String id) {
        model.setId(id);
    }

    public String getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}
//Creating a main class to demonstrate creating a Student, updating its details using StudentController, and displaying them using StudentView.
public class MVCPatternTest {
    public static void main(String[] args) {
        // Create a student model
        Student model = new Student("1", "John Doe", "A");

        // Create a student view
        StudentView view = new StudentView();

        // Create a student controller
        StudentController controller = new StudentController(model, view);

        // Display the initial student details
        controller.updateView();

        // Update the student details
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");

        // Display the updated student details
        controller.updateView();
    }
}
//Exercise-11
//Creating an interface CustomerRepository with methods like findCustomerById().
public interface CustomerRepository {
    Customer findCustomerById(String id);
}
//Creating a class CustomerRepositoryImpl that implements CustomerRepository.
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(String id) {
        // Simulating a database lookup
        return new Customer(id, "John Doe");
    }
}
//Creating a class CustomerService that depends on CustomerRepository.
public class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerById(id);
    }
}
//Creating a main class to demonstrate creating a CustomerService with CustomerRepositoryImpl and using it to find a customer.
public class DependencyInjectionTest {
    public static void main(String[] args) {
        // Create repository instance
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Inject repository into service
        CustomerService customerService = new CustomerService(customerRepository);

        // Use service to find customer
        Customer customer = customerService.getCustomerById("1");
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
    }
}
//Creating a simple Customer class to hold customer data.
public class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}





