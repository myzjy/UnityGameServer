package com.gameServer.common.entity.scene;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/21 00 36
 */
public class PlayerSceneInfoEntity implements IEntity<Long> {
    @Id
    private long id;
    /**
     * 场景id
     */
    private int sceneId;
    /**
     * 场景名
     */
    private String sceneStr;
    /**
     * 场景中 坐标 x
     */
    private float scenePosX;
    /**
     * 场景中 坐标 y
     */
    private float scenePosY;
    /**
     * 场景中 坐标 z
     */
    private float scenePosZ;
    /**
     * 角色在场景中得旋转角度 x
     */
    private float sceneCharacterRotationX;
    /**
     * 角色在场景中得旋转角度 Y
     */
    private float sceneCharacterRotationY;
    /**
     * 角色在场景中得旋转角度 z
     */
    private float sceneCharacterRotationZ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
    }

    public float getScenePosX() {
        return scenePosX;
    }

    public void setScenePosX(float scenePosX) {
        this.scenePosX = scenePosX;
    }

    public float getScenePosY() {
        return scenePosY;
    }

    public void setScenePosY(float scenePosY) {
        this.scenePosY = scenePosY;
    }

    public float getScenePosZ() {
        return scenePosZ;
    }

    public void setScenePosZ(float scenePosZ) {
        this.scenePosZ = scenePosZ;
    }

    public float getSceneCharacterRotationX() {
        return sceneCharacterRotationX;
    }

    public void setSceneCharacterRotationX(float sceneCharacterRotationX) {
        this.sceneCharacterRotationX = sceneCharacterRotationX;
    }

    public float getSceneCharacterRotationY() {
        return sceneCharacterRotationY;
    }

    public void setSceneCharacterRotationY(float sceneCharacterRotationY) {
        this.sceneCharacterRotationY = sceneCharacterRotationY;
    }

    public float getSceneCharacterRotationZ() {
        return sceneCharacterRotationZ;
    }

    public void setSceneCharacterRotationZ(float sceneCharacterRotationZ) {
        this.sceneCharacterRotationZ = sceneCharacterRotationZ;
    }

    @Override
    public Long id() {
        return id;
    }
}
