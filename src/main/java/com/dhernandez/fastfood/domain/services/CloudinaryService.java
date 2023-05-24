package com.dhernandez.fastfood.domain.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.annotation.PostConstruct;

@Service
public class CloudinaryService implements ICloudinaryService {
	Log Logger = LogFactory.getLog(CloudinaryService.class);
	@Value("${cloudynary.cloud_name}")
	private String CLOUD_NAME;

	@Value("${cloudynary.api_key}")
	private String API_KEY;

	@Value("${cloudynary.api_secret}")
	private String API_SECRET;

	private Cloudinary cloudinary;

	private Map<String, String> valuesMap = new HashMap<>();

	@Override
	public Map uploadImagen(MultipartFile multipartFile) throws IOException {
		Logger.info("Accessing the method uploadImagen()");

		File file = this.convert(multipartFile);
		Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		Logger.info("Endingthe method uploadImagen()");
		return result;
	}

	@Override
	public Map delete(String id) throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	@Override
	public File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}

	@PostConstruct
	public void init() {
		valuesMap.put("cloud_name", CLOUD_NAME);
		valuesMap.put("api_key", API_KEY);
		valuesMap.put("api_secret", API_SECRET);
		cloudinary = new Cloudinary(valuesMap);
	}

}
