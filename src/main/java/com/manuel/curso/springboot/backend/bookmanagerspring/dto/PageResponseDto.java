package com.manuel.curso.springboot.backend.bookmanagerspring.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponseDto<T> {

    private List<T> content;

    private int pageNumber;
    private int pageSize;

    private int totalPages;
    private long totalElements;

    public PageResponseDto(Page<T> page) {

        this.content = page.getContent();

        this.pageNumber = page.getNumber();

        this.pageSize = page.getSize();

        this.totalPages = page.getTotalPages();

        this.totalElements = page.getTotalElements();

    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
