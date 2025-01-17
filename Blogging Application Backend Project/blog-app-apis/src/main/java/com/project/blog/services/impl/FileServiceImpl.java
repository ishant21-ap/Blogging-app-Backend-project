package com.project.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		 //File name
        String name = file.getOriginalFilename();


        //GEnerating random name
        //Generating random ID using a class called UUID and method called randomUUID(), this will give you random string
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
        //Using above line we are getting index of the . means like if a file name is abc.png, so we are getting
        //.png as index starting from .


        //Full path
        String filePath = path + File.separator+ fileName1;


        //create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }


        //copy file
        Files.copy(file.getInputStream(), Paths.get(filePath));


        return name;
	}
	
	

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
	}

}
