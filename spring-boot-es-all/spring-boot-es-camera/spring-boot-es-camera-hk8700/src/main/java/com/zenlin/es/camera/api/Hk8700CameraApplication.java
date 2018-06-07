package com.zenlin.es.camera.api;

import com.zenlin.es.camera.service.Hk8700CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.camera.api
 * @ClassName Hk8700CameraApplication
 * @Author ZENLIN
 * @Date 2018-05-19 21:20
 * @Description TODO
 * @Version
 * @Modified By
 */
@RestControllerAdvice
@RestController
@RequestMapping(value = "/tdp/camera/")
public class Hk8700CameraApplication {
    @Autowired
    private Hk8700CameraService service;

    @GetMapping(value = "syncCamera8700")
    @ResponseBody
    public ResponseEntity syncCamera() {
        return service.syncCamera();
    }
}
