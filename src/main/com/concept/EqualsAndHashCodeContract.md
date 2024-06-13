1. If no properties that are used in equals method are changed then the hashcode method should generate same value each
   time it is invoked.
2. If two objects are equal by the equals method then the hashcode should be same.
3. If two objects are not equal by the equals method then the hashcode may or may not be same.

```java
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

class Person {
   private String name;
   private long id;
   private LocalDate dob;
   private String phoneNumber;
   //constructor, getters and setters

   @Override
   public int hashCode() {
      return Objects.hash(id, dob, phoneNumber, name);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof Person)) return false;
      Person person = (Person) obj;
      return this.dob.isEqual(person.dob) && this.name.equals(person.name) && this.id == person.id
              && this.phoneNumber.equals(person.phoneNumber);
   }
}
```