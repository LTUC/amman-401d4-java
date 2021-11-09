package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField FIRST_NAME = field("User", "firstName");
  public static final QueryField LAST_NAME = field("User", "lastName");
  public static final QueryField NATIONAL_ID = field("User", "nationalID");
  public static final QueryField POINTS = field("User", "points");
  public static final QueryField EMAIL = field("User", "email");
  public static final QueryField NUMBER = field("User", "number");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String firstName;
  private final @ModelField(targetType="String", isRequired = true) String lastName;
  private final @ModelField(targetType="Float", isRequired = true) Double nationalID;
  private final @ModelField(targetType="Int") Integer points;
  private final @ModelField(targetType="String") String email;
  private final @ModelField(targetType="String") String number;
  private final @ModelField(targetType="Log") @HasMany(associatedWith = "userId", type = Log.class) List<Log> logs = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public Double getNationalId() {
      return nationalID;
  }
  
  public Integer getPoints() {
      return points;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getNumber() {
      return number;
  }
  
  public List<Log> getLogs() {
      return logs;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String firstName, String lastName, Double nationalID, Integer points, String email, String number) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.nationalID = nationalID;
    this.points = points;
    this.email = email;
    this.number = number;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getFirstName(), user.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), user.getLastName()) &&
              ObjectsCompat.equals(getNationalId(), user.getNationalId()) &&
              ObjectsCompat.equals(getPoints(), user.getPoints()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getNumber(), user.getNumber()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFirstName())
      .append(getLastName())
      .append(getNationalId())
      .append(getPoints())
      .append(getEmail())
      .append(getNumber())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("nationalID=" + String.valueOf(getNationalId()) + ", ")
      .append("points=" + String.valueOf(getPoints()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("number=" + String.valueOf(getNumber()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static FirstNameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      firstName,
      lastName,
      nationalID,
      points,
      email,
      number);
  }
  public interface FirstNameStep {
    LastNameStep firstName(String firstName);
  }
  

  public interface LastNameStep {
    NationalIdStep lastName(String lastName);
  }
  

  public interface NationalIdStep {
    BuildStep nationalId(Double nationalId);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep points(Integer points);
    BuildStep email(String email);
    BuildStep number(String number);
  }
  

  public static class Builder implements FirstNameStep, LastNameStep, NationalIdStep, BuildStep {
    private String id;
    private String firstName;
    private String lastName;
    private Double nationalID;
    private Integer points;
    private String email;
    private String number;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          firstName,
          lastName,
          nationalID,
          points,
          email,
          number);
    }
    
    @Override
     public LastNameStep firstName(String firstName) {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
        return this;
    }
    
    @Override
     public NationalIdStep lastName(String lastName) {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
        return this;
    }
    
    @Override
     public BuildStep nationalId(Double nationalId) {
        Objects.requireNonNull(nationalId);
        this.nationalID = nationalId;
        return this;
    }
    
    @Override
     public BuildStep points(Integer points) {
        this.points = points;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep number(String number) {
        this.number = number;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String firstName, String lastName, Double nationalId, Integer points, String email, String number) {
      super.id(id);
      super.firstName(firstName)
        .lastName(lastName)
        .nationalId(nationalId)
        .points(points)
        .email(email)
        .number(number);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder nationalId(Double nationalId) {
      return (CopyOfBuilder) super.nationalId(nationalId);
    }
    
    @Override
     public CopyOfBuilder points(Integer points) {
      return (CopyOfBuilder) super.points(points);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder number(String number) {
      return (CopyOfBuilder) super.number(number);
    }
  }
  
}
