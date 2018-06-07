package com.zenlin.es.camera.service.impl;

import com.zenlin.es.camera.service.Hk8700CameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.camera.service.impl
 * @ClassName Hk8700CameraImpl
 * @Author ZENLIN
 * @Date 2018-05-19 21:19
 * @Description TODO
 * @Version
 * @Modified By
 */
@Service
public class Hk8700CameraImpl implements Hk8700CameraService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity syncCamera() {
        try {
            return new ResponseEntity("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("海康8700监控数据同步失败,error={}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
