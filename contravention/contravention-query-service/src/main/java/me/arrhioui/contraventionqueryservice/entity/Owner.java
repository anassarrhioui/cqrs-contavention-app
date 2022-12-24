package me.arrhioui.contraventionqueryservice.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Owner {
    @Id
    private String id;
    private String ownerName;
    private String ownerNationalIdCard;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private String ownerAddress;
}
