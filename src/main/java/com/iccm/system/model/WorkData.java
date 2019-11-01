package com.iccm.system.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31.
 */
@Data
public class WorkData {

    private RemoteData remoteData;

    private List<Person> persons;

    @Data
    public static class RemoteData{

        private String device1;

        private String device2;

        private String device3;

        private String device4;

        private String device5;

    }

    @Data
    public static class Person{

        private String name;

        private String bloodPress;

        private String heartRate;

        private String skinT;

        private String heightPress;

        private String lowPress;

    }
}
