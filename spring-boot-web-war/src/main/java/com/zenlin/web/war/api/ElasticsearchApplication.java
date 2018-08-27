package com.zenlin.web.war.api;

import com.zenlin.common.entity.RestMessage;
import com.zenlin.es.common.entity.ElasticsearchEntity;
import com.zenlin.es.common.entity.camera.CameraGroup;
import com.zenlin.es.common.utils.ElasticsearchUtil;
import com.zenlin.web.war.service.impl.ElasticsearchImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.web.war.api
 * @ClassName ElasticsearchApplication
 * @Author ZENLIN
 * @Date 2018-07-05 22:33
 * @Description TODO
 * @Version
 * @Modified By
 */
public class ElasticsearchApplication {

    ElasticsearchUtil elasticsearchUtil = new ElasticsearchUtil();

    @Test
    public void inset() {
        ElasticsearchEntity entity = new ElasticsearchEntity();
        entity.setIndex("camera");
        entity.setType("camera_group");
        entity.setId("N04a2QBy29AiZdQM6aA");
        CameraGroup cameraGroup = new CameraGroup();
        cameraGroup.setGroupNo("100001");
        cameraGroup.setGroupName("大厅客流监控");
        cameraGroup.setParentNo("10000");
        cameraGroup.setParentName("客流监控");
        cameraGroup.setIsParent(0);
        cameraGroup.setParentTypeNo(2);
        cameraGroup.setSeriesNo("HK9000");
        cameraGroup.setCreateTime("2018-07-05 12:12:01");
        cameraGroup.setModifyTime("2018-07-05 12:12:02");
        entity.setData(cameraGroup);
        RestMessage restMessage = new ElasticsearchImpl().insert(entity);
        System.out.println("restMessage = " + restMessage);
    }

    @Test
    public void insetBatch() {
        ElasticsearchEntity entity = new ElasticsearchEntity();
        entity.setIndex("camera");
        entity.setType("camera_group");

        CameraGroup cameraGroup = new CameraGroup();
        cameraGroup.setGroupNo("100002");
        cameraGroup.setGroupName("前门客流监控");
        cameraGroup.setParentNo("10000");
        cameraGroup.setParentName("客流监控");
        cameraGroup.setIsParent(0);
        cameraGroup.setParentTypeNo(2);
        cameraGroup.setSeriesNo("HK9000");
        cameraGroup.setCreateTime("2018-07-05 12:12:01");
        cameraGroup.setModifyTime("2018-07-05 12:12:02");

        CameraGroup cameraGroup2 = new CameraGroup();
        cameraGroup2.setGroupNo("100003");
        cameraGroup2.setGroupName("后门客流监控");
        cameraGroup2.setParentNo("10000");
        cameraGroup2.setParentName("客流监控");
        cameraGroup2.setIsParent(0);
        cameraGroup2.setParentTypeNo(2);
        cameraGroup2.setSeriesNo("HK9000");
        cameraGroup2.setCreateTime("2018-07-05 12:12:01");
        cameraGroup2.setModifyTime("2018-07-05 12:12:02");

        List<CameraGroup> list = new ArrayList<>();
        list.add(cameraGroup);
        list.add(cameraGroup2);

        entity.setData(list);
        RestMessage restMessage = new ElasticsearchImpl().insertBatch(entity);
        System.out.println("restMessage = " + restMessage);
    }

    @Test
    public void test3() {
        RestMessage restMessage = elasticsearchUtil.deleteByIndex(".monitoring-es-6-2018.07.09  ");
        System.out.println("restMessage = " + restMessage);
    }
}
