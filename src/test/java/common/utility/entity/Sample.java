package common.utility.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

  String id;
  String name;

  @Override
  public boolean equals(Object obj) {
    Sample other = (Sample) obj;
    return this.id.equals(other.id) && this.name.equals(other.name);
  }
}
