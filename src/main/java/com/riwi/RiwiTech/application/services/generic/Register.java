package com.riwi.RiwiTech.application.services.generic;

public interface Register<Entity, EntityRequest> {
    public Entity register(EntityRequest entityRequest);
}
