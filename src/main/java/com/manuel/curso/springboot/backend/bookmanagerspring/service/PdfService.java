package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PdfService {

    ByteArrayInputStream exportUsersToPdf(List<User> users);

}
