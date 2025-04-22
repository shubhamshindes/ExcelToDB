package com.app.exceltodb.controller;

import com.app.exceltodb.entity.Product;
import com.app.exceltodb.helper.Helper;
import com.app.exceltodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){

        if(Helper.checkExcelFormat(file)){
            this.productService.save(file);
            return ResponseEntity.ok().body("Data added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload proper excel file");
        }
    }

    @GetMapping("product")
    public List<Product> getAll(){
        return this.productService.getAllProducts();
    }
}
