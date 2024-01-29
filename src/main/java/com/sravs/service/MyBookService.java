package com.sravs.service;

import com.sravs.entity.MyBookList;
import com.sravs.repo.MyBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    @Autowired
    MyBookRepo bookRepo;
    public void  saveMyBooks(MyBookList myBookList){
        bookRepo.save(myBookList);
    }
    public List<MyBookList> getAllBooks() {
        return bookRepo.findAll();
    }
    public void deleteById(int id){
        bookRepo.deleteById(id);
    }
}
