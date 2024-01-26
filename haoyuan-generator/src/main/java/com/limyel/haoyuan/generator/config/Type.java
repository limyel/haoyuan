package com.limyel.haoyuan.generator.config;

public enum Type {
    CONTROLLER("Controller.java.ftl", "controller", "%sController.java"),
    ENTITY("Entity.java.ftl", "entity", "%sEntity.java"),
    DAO("Dao.java.ftl", "dao", "%sDao.java"),
    MAPPER("Mapper.xml.ftl", "mapper", "%sDao.xml"),
    SERVICE("Service.java.ftl", "service", "%sService.java"),
    SERVICE_IMPL("ServiceImpl.java.ftl", "service/impl", "%sServiceImpl.java")
    ;

    private String path;
    private String packageName;
    private String className;

    Type(String path, String packageName, String className) {
        this.path = path;
        this.packageName = packageName;
        this.className = className;
    }

    public String getPath() {
        return path;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }
}
