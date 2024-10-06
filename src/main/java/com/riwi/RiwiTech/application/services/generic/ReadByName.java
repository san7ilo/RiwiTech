package com.riwi.RiwiTech.application.services.generic;

public interface ReadByName<Entity, NAME>{
    public Entity readByName(NAME name);
}
