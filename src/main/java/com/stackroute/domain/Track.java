package com.stackroute.domain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Track {

    @Id
    private int id;
    private String name;
    private String comments;


}
