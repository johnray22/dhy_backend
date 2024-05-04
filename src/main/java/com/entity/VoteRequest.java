package com.entity;

import lombok.Data;

/**
 * @author zhongruiR
 * @date 2024/5/4 12:15
 */
@Data
public class VoteRequest {
    Integer voteId;
    String  selectedOption;
}
