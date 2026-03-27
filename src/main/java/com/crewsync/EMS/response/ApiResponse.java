package com.crewsync.EMS.response;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private boolean status;
    private String message;
    private T data;
}
