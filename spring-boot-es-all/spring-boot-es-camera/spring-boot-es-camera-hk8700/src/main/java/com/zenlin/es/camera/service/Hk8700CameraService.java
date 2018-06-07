package com.zenlin.es.camera.service;

import org.springframework.http.ResponseEntity;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.camera.service
 * @ClassName Hk8700CameraService
 * @Author ZENLIN
 * @Date 2018-05-19 21:19
 * @Description TODO
 * @Version
 * @Modified By
 */
public interface Hk8700CameraService {
    /**
     * 同步监控
     *
     * @return
     */
    ResponseEntity syncCamera();
}
