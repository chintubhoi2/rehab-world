package com.digital.rehab.exception;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
  private int statusCode;
  private Date timestamp;
  private String message;
}
