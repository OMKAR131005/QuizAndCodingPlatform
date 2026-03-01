package org.pm.quizapp.QuetionModule.dto;


import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

        private List<T> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
}

