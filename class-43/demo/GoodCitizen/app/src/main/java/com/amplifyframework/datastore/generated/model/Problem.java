package com.amplifyframework.datastore.generated.model;

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

/** This is an auto generated class representing the Problem type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Problems")
@Index(name = "byUser", fields = {"userID"})
@Index(name = "byCategoty", fields = {"categoryID"})
public final class Problem implements Model {
  public static final QueryField ID = field("Problem", "id");
  public static final QueryField TITLE = field("Problem", "title");
  public static final QueryField DESCRIPTION = field("Problem", "description");
  public static final QueryField IMAGE_URL = field("Problem", "imageURL");
  public static final QueryField USER_ID = field("Problem", "userID");
  public static final QueryField CATEGORY_ID = field("Problem", "categoryID");
  public static final QueryField LATITUDE = field("Problem", "latitude");
  public static final QueryField LONGITUDE = field("Problem", "longitude");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="String") String imageURL;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="ID", isRequired = true) String categoryID;
  private final @ModelField(targetType="Float") Double latitude;
  private final @ModelField(targetType="Float") Double longitude;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getImageUrl() {
      return imageURL;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public String getCategoryId() {
      return categoryID;
  }
  
  public Double getLatitude() {
      return latitude;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Problem(String id, String title, String description, String imageURL, String userID, String categoryID, Double latitude, Double longitude) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.imageURL = imageURL;
    this.userID = userID;
    this.categoryID = categoryID;
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Problem problem = (Problem) obj;
      return ObjectsCompat.equals(getId(), problem.getId()) &&
              ObjectsCompat.equals(getTitle(), problem.getTitle()) &&
              ObjectsCompat.equals(getDescription(), problem.getDescription()) &&
              ObjectsCompat.equals(getImageUrl(), problem.getImageUrl()) &&
              ObjectsCompat.equals(getUserId(), problem.getUserId()) &&
              ObjectsCompat.equals(getCategoryId(), problem.getCategoryId()) &&
              ObjectsCompat.equals(getLatitude(), problem.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), problem.getLongitude()) &&
              ObjectsCompat.equals(getCreatedAt(), problem.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), problem.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getDescription())
      .append(getImageUrl())
      .append(getUserId())
      .append(getCategoryId())
      .append(getLatitude())
      .append(getLongitude())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Problem {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("imageURL=" + String.valueOf(getImageUrl()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("categoryID=" + String.valueOf(getCategoryId()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
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
  public static Problem justId(String id) {
    return new Problem(
      id,
      null,
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
      title,
      description,
      imageURL,
      userID,
      categoryID,
      latitude,
      longitude);
  }
  public interface TitleStep {
    UserIdStep title(String title);
  }
  

  public interface UserIdStep {
    CategoryIdStep userId(String userId);
  }
  

  public interface CategoryIdStep {
    BuildStep categoryId(String categoryId);
  }
  

  public interface BuildStep {
    Problem build();
    BuildStep id(String id);
    BuildStep description(String description);
    BuildStep imageUrl(String imageUrl);
    BuildStep latitude(Double latitude);
    BuildStep longitude(Double longitude);
  }
  

  public static class Builder implements TitleStep, UserIdStep, CategoryIdStep, BuildStep {
    private String id;
    private String title;
    private String userID;
    private String categoryID;
    private String description;
    private String imageURL;
    private Double latitude;
    private Double longitude;
    @Override
     public Problem build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Problem(
          id,
          title,
          description,
          imageURL,
          userID,
          categoryID,
          latitude,
          longitude);
    }
    
    @Override
     public UserIdStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public CategoryIdStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep categoryId(String categoryId) {
        Objects.requireNonNull(categoryId);
        this.categoryID = categoryId;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep imageUrl(String imageUrl) {
        this.imageURL = imageUrl;
        return this;
    }
    
    @Override
     public BuildStep latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        this.longitude = longitude;
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
    private CopyOfBuilder(String id, String title, String description, String imageUrl, String userId, String categoryId, Double latitude, Double longitude) {
      super.id(id);
      super.title(title)
        .userId(userId)
        .categoryId(categoryId)
        .description(description)
        .imageUrl(imageUrl)
        .latitude(latitude)
        .longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder categoryId(String categoryId) {
      return (CopyOfBuilder) super.categoryId(categoryId);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder imageUrl(String imageUrl) {
      return (CopyOfBuilder) super.imageUrl(imageUrl);
    }
    
    @Override
     public CopyOfBuilder latitude(Double latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
  }
  
}
