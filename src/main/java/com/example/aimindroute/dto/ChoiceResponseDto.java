package com.example.aimindroute.dto;

import com.example.aimindroute.entity.question.Choice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponseDto {
    private Long id;
    private String text;
    private int scoreDelta;
    private Long createId;
    private LocalDateTime createDate;

    public static ChoiceResponseDto fromEntity(Choice choice) {
        return new ChoiceResponseDto(
                choice.getId(),
                choice.getText(),
                choice.getScoreDelta(),
                choice.getCreateId(),
                choice.getCreateDate()
        );
    }
}
