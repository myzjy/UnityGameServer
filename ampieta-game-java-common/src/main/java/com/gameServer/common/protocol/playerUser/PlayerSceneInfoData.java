package com.gameServer.common.protocol.playerUser;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/17 19 52
 */
public class PlayerSceneInfoData {
    /**
     * 场景id
     */
    private int sceneId;
    /**
     * 场景名
     */
    private String sceneStr;
    private float scenePosX;
    private float scenePosY;
    private float scenePosZ;
    private float sceneCharacterRotationX;
    private float sceneCharacterRotationY;
    private float sceneCharacterRotationZ;

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

    public static PlayerSceneInfoData valueOf() {
        return new PlayerSceneInfoData();
    }
}
