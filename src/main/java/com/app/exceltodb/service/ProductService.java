package com.app.exceltodb.service;


import com.app.exceltodb.dao.ProductRepository;
import com.app.exceltodb.entity.Product;
import com.app.exceltodb.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(MultipartFile file){
        try {
            List<Product> productList= Helper.convertExcelToListOfProducts(file.getInputStream());
            this.productRepository.saveAll(productList);
            /*for(Product product:productList){
                System.out.println(product);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

}
