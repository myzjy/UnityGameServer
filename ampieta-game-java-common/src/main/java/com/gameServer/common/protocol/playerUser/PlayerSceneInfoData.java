package com.gameServer.common.protocol.playerUser;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/17 19 52
 */
@Protocol(id = 221)
public class PlayerSceneInfoData implements IPacket {
    /**
     * 场景id
     */
    @Note("场景id")
    private int sceneId;
    /**
     * 场景名
     */
    @Note("场景名")
    private String sceneStr;
    /**
     * 场景中 坐标 x
     */
    @Note(" 场景中 坐标 x")
    private float scenePosX;
    /**
     * 场景中 坐标 y
     */
    @Note("场景中 坐标 y")
    private float scenePosY;
    /**
     * 场景中 坐标 z
     */
    @Note("场景中 坐标 z")
    private float scenePosZ;
    /**
     * 角色在场景中得旋转角度 x
     */
    @Note("角色在场景中得旋转角度 x")
    private float sceneCharacterRotationX;
    /**
     * 角色在场景中得旋转角度 Y
     */
    @Note("角色在场景中得旋转角度 Y")
    private float sceneCharacterRotationY;
    /**
     * 角色在场景中得旋转角度 z
     */
    @Note("角色在场景中得旋转角度 z")
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
