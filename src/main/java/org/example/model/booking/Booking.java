package org.example.model.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.model.BaseEntity;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking extends BaseEntity {
    String id;
    @JsonProperty(value = "firstname")
    String firstName;
    @JsonProperty(value = "lastname")
    String lastName;
    @JsonProperty(value = "totalprice")
    Integer totalPrice;
    @JsonProperty(value = "depositpaid")
    Boolean depositPaid;
    @JsonProperty(value = "bookingdates")
    BookingDates bookingDates;
    @JsonProperty(value = "additionalneeds")
    String additionalNeeds;
}
