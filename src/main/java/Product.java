import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="products")
public class Product {
  @Id
  @SequenceGenerator(name = "products_generator", sequenceName = "products_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;



  @Size(min=5, max =20)
  @Column(name="vin", nullable = false, unique = true)
  private String vin;

  @NotBlank
  @Range(min=1980, max=2030)
  @Column(name="year", nullable = false, unique = true)
  private String year;

  @NotBlank
  @Column(name="make", nullable = false, unique = true)
  private String make;


  @Range(min=500, max=50000)
  @Column(name = "price", nullable = false)
  private double price;

  @NotBlank
  @Column(name="model")
  private String model;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
}
