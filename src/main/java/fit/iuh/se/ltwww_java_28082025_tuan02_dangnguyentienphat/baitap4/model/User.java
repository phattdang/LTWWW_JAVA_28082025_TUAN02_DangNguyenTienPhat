package fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap4.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    String id;
    String name;
    String password;
    String email;
    Gender gender;
}
