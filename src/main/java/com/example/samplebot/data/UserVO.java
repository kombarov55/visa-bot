package com.example.samplebot.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    @Id
    private UUID id;

    private Long chatId;

    private String currentViewId;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String city;

    private String email;

    private String password;

    private Date minDate;

    private Date maxDate;

    private Integer daysReserve;

}
