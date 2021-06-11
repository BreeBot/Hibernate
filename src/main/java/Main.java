import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Main {
  private static String ADD_CAR = "Add a Car";
  private static String LIST_CARS = "List Cars";
  private static String SEARCH_CARS = "Search for a Car";
  private static String EXIT = "Exit";

  private static Scanner inputScanner;

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.launchacademy.carDealership");
    EntityManager em = emf.createEntityManager();
    List<String> options = new ArrayList<String>();
    options.add(ADD_CAR);
    options.add(LIST_CARS);
//    options.add(SEARCH_CARS);
    options.add(EXIT);

    String menuChoice = "";
    while(menuChoice != EXIT) {
      System.out.println("CARS! CARS! CARS!");
      System.out.println("***");
      System.out.println("Coffee is for closers.\n");

      int menuIndex = 1;
      for(String option : options) {
        System.out.println(menuIndex + ". " + option);
        menuIndex++;
      }

      System.out.println("\nWhat would you like to do?");
      inputScanner = new Scanner(System.in);

      int selectedOption = inputScanner.nextInt();


      menuChoice = options.get(selectedOption - 1);
      if(menuChoice == ADD_CAR) {
        //add car logic
        inputScanner.nextLine();


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        System.out.println("Please input the VIN");
        String carVin = inputScanner.nextLine();




        System.out.println("Please input the year between 1980 and 2030");
        String carYear = inputScanner.nextLine();

        System.out.println("Please input the make");
        String carMake = inputScanner.nextLine();

        System.out.println("Please input the price between 500 and 50,000");
        double carPrice = inputScanner.nextDouble();
        inputScanner.nextLine();

        System.out.println("Please input the model");
        String carModel = inputScanner.nextLine();


        Product newProduct = new Product();
        newProduct.setVin(carVin);
        newProduct.setYear(carYear);
        newProduct.setMake(carMake);
        newProduct.setPrice(carPrice);
        newProduct.setModel(carModel);


        Set<ConstraintViolation<Product>> violationSet = validator.validate(newProduct);
        if (violationSet.size() >0){
          for (ConstraintViolation violation : violationSet){
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
          }
          menuChoice = ADD_CAR;
          System.out.println("Please enter valid info");
          inputScanner.nextLine();
        } else {
          em.getTransaction().begin();
          em.persist(newProduct);
          em.getTransaction().commit();
          System.out.println(newProduct.getId());
        }
        factory.close();



      }
      else if(menuChoice == LIST_CARS) {
        //list car logic
        //listCar();
        TypedQuery<Product> query = em.createQuery("select p from Product p order by price DESC", Product.class);
        List<Product> products = query.getResultList();
        for (Product product: products){
          System.out.println(product.getPrice() + ", " + product.getVin() + ", " + product.getYear() + ", " + product.getMake() + ", " + product.getModel());
        }


      }
    }
  }




}
