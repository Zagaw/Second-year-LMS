// dto/BulkQuestionsRequest.java
package com.example.lms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class BulkQuestionsRequest {
    @NotEmpty
    private List<CreateQuestionRequest> questions;
}

