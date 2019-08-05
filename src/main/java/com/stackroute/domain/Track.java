package com.stackroute.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

// Entity defines that a class can be mapped to a table
@Entity

/*  Data bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter
    and @RequiredArgsConstructor together*/
@Data

// It generates constructor with no parameters
@NoArgsConstructor

// It generates a constructor with 1 parameter for each field
@AllArgsConstructor

/*  It produces complex builder APIs for class
    It automatically produce the code required to have your class be instantiable with code
 */
@Builder
public class Track {
    //    declaration of variables
    @Id
    private int id;
    private String name;
    private String comment;

}