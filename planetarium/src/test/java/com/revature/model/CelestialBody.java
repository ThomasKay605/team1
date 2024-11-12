package com.revature.model;

public class CelestialBody {
    
    private CelestialType type;

    private int id;

    private String name;

    private int ownerId;

    private String imagePath;

    public CelestialBody(CelestialType type, int id, String name, int ownerId, String imagePath) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.imagePath = imagePath;
    }

    public String getCelestialType() {
        return this.type.getType();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public String getImgSrc() {
        return this.imagePath;
    }

}
