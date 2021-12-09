package com.example.where2go.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage<T> {
    private T value;
    private String message;
}
