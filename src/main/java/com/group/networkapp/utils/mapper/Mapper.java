package com.group.networkapp.utils.mapper;

public abstract class Mapper<Entity, Dto> {
    public abstract Dto toDto(Entity entity);
    public abstract Entity toEntity(Dto dto);
}
