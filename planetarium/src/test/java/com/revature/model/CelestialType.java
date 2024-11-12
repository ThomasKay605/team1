package com.revature.model;

public enum CelestialType {
    

    PLANET("planet"),
    MOON("moon");

    private String type;

    private CelestialType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static CelestialType getCelestialType(String type) {
        if(type.equals("planet")) return CelestialType.PLANET;
        else if(type.equals("moon")) return CelestialType.MOON;
        else return null;
    }
        

}
